package lbiz;

import java.util.ArrayList;

import bean.EndLine;
import bean.Result;


public interface IBiz {
	Result<ArrayList<EndLine>> getLineTotal(String unit);
	Result<ArrayList<EndLine>> getLineDetail(String unit,int line_id ,int range,int page,int size);
}
