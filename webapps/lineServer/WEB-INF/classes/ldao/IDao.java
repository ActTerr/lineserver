package ldao;

import java.util.ArrayList;

import bean.EndLine;


public interface IDao {
	ArrayList<EndLine> getLineTotal(String unit);
	ArrayList<EndLine> getLineDetail(String unit,int line_id,int range,int page,int size);
}
