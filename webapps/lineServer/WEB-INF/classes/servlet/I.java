package servlet;

public interface I {
	interface REQUEST {
		String GET_LINE_TOTAL = "getLineTotal";
		String GET_LINE_DETAIL = "getLineDetail";
	}

	interface END_LINE {
		String TABLE_NAME = "end_line_endline";
		String UNIT = "UNIT";
		String LINE_ID = "LINE_ID";
		String TEMPERATURE = "TEMPERATURE";
		String SENSOR1 = "SENSOR1";
		String SENSOR2 = "SENSOR2";
		String RADIO_STATION = "RADIO_STATION";
		String TIME = "TIME";
		String BATTERY = "BATTERY";
		String POWER="POWER";
	}
	
	interface PARAMETER{
		String UNIT="unit";
		String PAGE="page";
		String SIZE="size";
		String RANGE="range";
	}
	
	

	
	interface RESULT {
		int SUCCESS = 1;
		int DEFEAT = 0;
		String SUC = "成功";
		String DEF = "失败";
	}
}
