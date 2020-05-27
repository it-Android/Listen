package com.admin.listen.analysis;

public abstract class AnalysisBase<E> {
	
	
	public abstract E analysis(String html);
	
	
	public int strToInt(String number) {
		try {
			return Integer.valueOf(number);
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

}
