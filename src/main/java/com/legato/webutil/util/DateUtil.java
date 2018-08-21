package com.legato.webutil.util;

public class DateUtil {
	public static int getDiffMonth(String startdate, String enddate) {
		String startyear = startdate.substring(0, startdate.length() - 2);
		String endyear = enddate.substring(0, enddate.length() - 2);
		String smm = startdate.length() > 2 ? startdate.substring(startdate.length() - 2) : startdate;
		String emm = enddate.length() > 2 ? enddate.substring(enddate.length() - 2) : enddate;
		int dyr = Integer.parseInt(endyear) - Integer.parseInt(startyear);
		int tot = 0;
		tot = Integer.parseInt(emm);
		if (dyr > 0) {
			int mm = 0;
			mm += 13 - Integer.parseInt(smm);
			tot += mm;
			tot += (dyr - 1) * 12;

		} else if (dyr == 0) {
			tot = tot - Integer.parseInt(smm);
			tot++;
		}
		return tot;
	}
	
	public static void main(String[] args) {
		System.out.println(getDiffMonth("20-Apr-2018", "21-Apr-2018"));
	}
}