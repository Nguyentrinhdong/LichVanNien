package com.bhmedia.lichvannien2016.ui.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ActionDate {
	public static String getMonthEnglish(int month) {
		String result = "";
		switch (month) {
		case 1:
			result = "January";
			break;
		case 2:
			result = ("February");
			break;
		case 3:
			result = ("March");
			break;
		case 4:
			result = ("April");
			break;
		case 5:
			result = ("May");
			break;
		case 6:
			result = ("June");
			break;
		case 7:
			result = ("July");
			break;
		case 8:
			result = ("August");
			break;
		case 9:
			result = ("September");
			break;
		case 10:
			result = ("October");
			break;
		case 11:
			result = ("November");
			break;
		case 12:
			result = ("December");
			break;

		default:
			break;
		}
		return result;
	}
	
	public static String getHienTai(){
		Calendar ca=Calendar.getInstance();
		String ngay=String.valueOf(ca.get(Calendar.DAY_OF_MONTH));
		String thang=String.valueOf(ca.get(Calendar.MONTH)+1);
		String nam=String.valueOf(ca.get(Calendar.YEAR));
		return ngay+"/"+thang+"/"+nam;
	}
	
	public static Calendar congngay(Calendar ngayduoccong, int songaycong) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		ngayduoccong.add(Calendar.DATE, songaycong);
		String test = dateFormat.format(ngayduoccong.getTime());

		String[] mangtam = test.split("/");
		int ngay = Integer.parseInt(mangtam[0]);
		int thang = Integer.parseInt(mangtam[1]) - 1;
		int nam = Integer.parseInt(mangtam[2]);
		Calendar trave = Calendar.getInstance();
		trave.set(Calendar.DAY_OF_MONTH, ngay);
		trave.set(Calendar.MONTH, thang);
		trave.set(Calendar.YEAR, nam);
		return trave;
	}
}
