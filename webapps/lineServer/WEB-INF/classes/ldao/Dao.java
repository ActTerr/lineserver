package ldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.EndLine;
import ldao.IDao;
import servlet.I;
import util.DBUtils;

public class Dao implements IDao{

	@Override
	public ArrayList<EndLine> getLineTotal(String unit) {
		String sql2="select * from "+I.END_LINE.TABLE_NAME+" where unit=? and  "+
				I.END_LINE.LINE_ID+"=? and  "+
				I.END_LINE.TIME+"=(select max(TIME)from "+I.END_LINE.TABLE_NAME+" where line_id=?)";
		String sql="select max("+I.END_LINE.LINE_ID+") from "+I.END_LINE.TABLE_NAME;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		ArrayList<EndLine> lines=new ArrayList<>();
			try {
				conn=DBUtils.getConnection();
				ps=conn.prepareStatement(sql);
				System.out.println(sql);
				rs=ps.executeQuery();
				int count=0;
				if(rs.next()){
					count=rs.getInt(1);
					System.out.println(count);
				}
				ps.close();
				
				for(int i=0;i<count;i++){
				ps=conn.prepareStatement(sql2);
				System.out.println(sql2);
				ps.setString(1, unit);
				ps.setInt(2,i+1);
				ps.setInt(3,i+1);
				rs=ps.executeQuery();
				
				if(rs.next()){
					EndLine line=new EndLine(
							Integer.parseInt(unit),
							i, 
							rs.getInt(I.END_LINE.SENSOR1),
							rs.getInt(I.END_LINE.SENSOR2),
							rs.getInt(I.END_LINE.RADIO_STATION),
							rs.getInt(I.END_LINE.TEMPERATURE), 
							rs.getFloat(I.END_LINE.BATTERY),
							rs.getFloat(I.END_LINE.POWER),
							rs.getTimestamp(I.END_LINE.TIME).getTime());
					lines.add(line);
					System.out.println(line);
				}
			
				rs.close();
				ps.close();
				}
			}catch (SQLException e) {
					e.printStackTrace();
		}finally{
			DBUtils.closeAll(conn, ps, rs);
		}
		return lines;
	}

	@Override
	public ArrayList<EndLine> getLineDetail(String unit, int line_id,int range, int page, int size) {
		String sql="select * from "+I.END_LINE.TABLE_NAME+" where unit=? and line_id=? "
				+ "and time>DATE_SUB(CURDATE(), INTERVAL ? DAY) ORDER BY `time` DESC limit ?,?";
		ArrayList<EndLine> lines=new ArrayList<>();
		Connection conn=DBUtils.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(unit));
	
			ps.setInt(2, line_id);
			ps.setInt(3,range);
			ps.setInt(4,page*size);
			ps.setInt(5,(page+1)*size);
			rs=ps.executeQuery();
			while(rs.next()){
				EndLine line=new EndLine(
						Integer.parseInt(unit),
				line_id, 
				rs.getInt(I.END_LINE.SENSOR1),
				rs.getInt(I.END_LINE.SENSOR2),
				rs.getInt(I.END_LINE.RADIO_STATION),
				rs.getInt(I.END_LINE.TEMPERATURE), 
				rs.getFloat(I.END_LINE.BATTERY),
				rs.getFloat(I.END_LINE.POWER),
				rs.getTimestamp(I.END_LINE.TIME).getTime());
				lines.add(line);
			}
			System.out.println(size);
			System.out.println(lines.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(conn, ps, rs);
		}
		
		return lines;
	}
}
