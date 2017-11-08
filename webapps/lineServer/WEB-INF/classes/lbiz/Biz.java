package lbiz;

import java.util.ArrayList;

import bean.EndLine;
import bean.Result;
import lbiz.IBiz;
import ldao.Dao;
import ldao.IDao;
import servlet.I;

public class Biz implements IBiz{
	IDao dao;
	
	public Biz(){
		dao=new Dao();
	}
	
	public Result<ArrayList<EndLine>> getLineTotal(String unit) {
		Result<ArrayList<EndLine>> result=new Result<>();
		ArrayList<EndLine> list=dao.getLineTotal(unit);
		if(list.size()!=0){
			result.setRetCode(I.RESULT.SUCCESS);
			result.setRetMsg(I.RESULT.SUC);
			result.setRetData(list);
		} else {
			result.setRetMsg(I.RESULT.DEF);
			result.setRetCode(I.RESULT.DEFEAT);
		}
		return result;
	}

	@Override
	public Result<ArrayList<EndLine>> getLineDetail(String unit,int line_id ,int range, int page, int size) {
		Result<ArrayList<EndLine>> result=new Result<>();
		ArrayList<EndLine> list=dao.getLineDetail(unit,line_id, range, page, size);
	
			result.setRetCode(I.RESULT.SUCCESS);
			result.setRetMsg(I.RESULT.SUC);
			result.setRetData(list);
		
		return result;
	}
}
