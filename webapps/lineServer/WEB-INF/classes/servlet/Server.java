package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EndLine;
import bean.Result;
import lbiz.Biz;
import lbiz.IBiz;
import util.JsonUtil;

@WebServlet("/Server")
public class Server extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	IBiz biz;
	
	public Server(){
		biz=new Biz();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String strReq = req.getParameter("request");
		if(strReq==null){
			return;
		}
		switch (strReq) {
		case I.REQUEST.GET_LINE_TOTAL:
			getLineTotal(req,resp);
			break;
		case I.REQUEST.GET_LINE_DETAIL:
			getLineDetail(req,resp);
			break;
			
		default:
			break;
		}
	}

	private void getLineDetail(HttpServletRequest req, HttpServletResponse resp) {
		String unit=req.getParameter(I.PARAMETER.UNIT);
		int range=Integer.parseInt(req.getParameter(I.PARAMETER.RANGE));
		int page=Integer.parseInt(req.getParameter(I.PARAMETER.PAGE));
		int size=Integer.parseInt(req.getParameter(I.PARAMETER.SIZE));
		int line_id=Integer.parseInt(req.getParameter(I.END_LINE.LINE_ID));
		Result<ArrayList<EndLine>> result=biz.getLineDetail(unit,line_id,range,page,size);
		JsonUtil.writeJsonToClient(result, resp);
	}
	private void getLineTotal(HttpServletRequest req, HttpServletResponse resp) {
		String unit=req.getParameter(I.PARAMETER.UNIT);
		Result<ArrayList<EndLine>> result=biz.getLineTotal(unit);
		JsonUtil.writeJsonToClient(result, resp);
	}
}
