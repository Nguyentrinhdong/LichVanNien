package com.bhmedia.lichvannien2016.ui.utility;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.model.HacHoangDaoModel;

/**
 * Created by Van on 1/23/2015.
 */
@SuppressLint({ "DefaultLocale" })
public class CalendarUtil {
	private String[] dsCan;
	private String[] dsChi;
	private double timeZone = 7.0;

	public CalendarUtil() {
		this.dsCan = new String[] { "Jia", "Yi", "Bing", "Ding", "Wu", "Ji",
				"Geng", "Xin", "Ren", "Gui" };
		this.dsChi = new String[] { "Zi (Rat)", "Chou (Ox)", "Yin (Tiger)",
				"Mao (Rabbit)", "Chen (Dragon)", "Si (Snake)", "Wu (Horse)",
				"Wei (Goat)", "Shen (Monkey)", "You (Rooster)", "Xu (Dog)",
				"Hai (Pig)" };
	}

	public static final double PI = Math.PI;

	public int jdFromDate(int dd, int mm, int yy) {
		int a = (14 - mm) / 12;
		int y = yy + 4800 - a;
		int m = mm + 12 * a - 3;
		int jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400
				- 32045;
		if (jd < 2299161) {
			jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - 32083;
		}
		// jd = jd - 1721425;
		return jd;
	}

	public int[] jdToDate(int jd) {
		int a, b, c;
		if (jd > 2299160) { // After 5/10/1582, Gregorian calendar
			a = jd + 32044;
			b = (4 * a + 3) / 146097;
			c = a - (b * 146097) / 4;
		} else {
			b = 0;
			c = jd + 32082;
		}
		int d = (4 * c + 3) / 1461;
		int e = c - (1461 * d) / 4;
		int m = (5 * e + 2) / 153;
		int day = e - (153 * m + 2) / 5 + 1;
		int month = m + 3 - 12 * (m / 10);
		int year = b * 100 + d - 4800 + m / 10;
		return new int[] { day, month, year };
	}

	public double SunLongitude(double jdn) {
		// return CC2K.sunLongitude(jdn);
		return SunLongitudeAA98(jdn);
	}

	public double SunLongitudeAA98(double jdn) {
		double T = (jdn - 2451545.0) / 36525; // Time in Julian centuries from
												// 2000-01-01 12:00:00 GMT
		double T2 = T * T;
		double dr = PI / 180; // degree to radian
		double M = 357.52910 + 35999.05030 * T - 0.0001559 * T2 - 0.00000048
				* T * T2; // mean anomaly, degree
		double L0 = 280.46645 + 36000.76983 * T + 0.0003032 * T2; // mean
																	// longitude,
																	// degree
		double DL = (1.914600 - 0.004817 * T - 0.000014 * T2)
				* Math.sin(dr * M);
		DL = DL + (0.019993 - 0.000101 * T) * Math.sin(dr * 2 * M) + 0.000290
				* Math.sin(dr * 3 * M);
		double L = L0 + DL; // true longitude, degree
		L = L - 360 * (INT(L / 360)); // Normalize to (0, 360)
		return L;
	}

	public double NewMoon(int k) {
		// return CC2K.newMoonTime(k);
		return NewMoonAA98(k);
	}

	public double NewMoonAA98(int k) {
		double T = k / 1236.85; // Time in Julian centuries from 1900 January
								// 0.5
		double T2 = T * T;
		double T3 = T2 * T;
		double dr = PI / 180;
		double Jd1 = 2415020.75933 + 29.53058868 * k + 0.0001178 * T2
				- 0.000000155 * T3;
		Jd1 = Jd1 + 0.00033
				* Math.sin((166.56 + 132.87 * T - 0.009173 * T2) * dr); // Mean
																		// new
																		// moon
		double M = 359.2242 + 29.10535608 * k - 0.0000333 * T2 - 0.00000347
				* T3; // Sun's mean anomaly
		double Mpr = 306.0253 + 385.81691806 * k + 0.0107306 * T2 + 0.00001236
				* T3; // Moon's mean anomaly
		double F = 21.2964 + 390.67050646 * k - 0.0016528 * T2 - 0.00000239
				* T3; // Moon's argument of latitude
		double C1 = (0.1734 - 0.000393 * T) * Math.sin(M * dr) + 0.0021
				* Math.sin(2 * dr * M);
		C1 = C1 - 0.4068 * Math.sin(Mpr * dr) + 0.0161 * Math.sin(dr * 2 * Mpr);
		C1 = C1 - 0.0004 * Math.sin(dr * 3 * Mpr);
		C1 = C1 + 0.0104 * Math.sin(dr * 2 * F) - 0.0051
				* Math.sin(dr * (M + Mpr));
		C1 = C1 - 0.0074 * Math.sin(dr * (M - Mpr)) + 0.0004
				* Math.sin(dr * (2 * F + M));
		C1 = C1 - 0.0004 * Math.sin(dr * (2 * F - M)) - 0.0006
				* Math.sin(dr * (2 * F + Mpr));
		C1 = C1 + 0.0010 * Math.sin(dr * (2 * F - Mpr)) + 0.0005
				* Math.sin(dr * (2 * Mpr + M));
		double deltat;
		if (T < -11) {
			deltat = 0.001 + 0.000839 * T + 0.0002261 * T2 - 0.00000845 * T3
					- 0.000000081 * T * T3;
		} else {
			deltat = -0.000278 + 0.000265 * T + 0.000262 * T2;
		}
		;
		double JdNew = Jd1 + C1 - deltat;
		return JdNew;
	}

	public int INT(double d) {
		return (int) Math.floor(d);
	}

	public double getSunLongitude(int dayNumber, double timeZone) {
		return SunLongitude(dayNumber - 0.5 - timeZone / 24);
	}

	public int getNewMoonDay(int k, double timeZone) {
		double jd = NewMoon(k);
		return INT(jd + 0.5 + timeZone / 24);
	}

	public int getLunarMonth11(int yy, double timeZone) {
		double off = jdFromDate(31, 12, yy) - 2415021.076998695;
		int k = INT(off / 29.530588853);
		int nm = getNewMoonDay(k, timeZone);
		int sunLong = INT(getSunLongitude(nm, timeZone) / 30);
		if (sunLong >= 9) {
			nm = getNewMoonDay(k - 1, timeZone);
		}
		return nm;
	}

	public int getLeapMonthOffset(int a11, double timeZone) {
		int k = INT(0.5 + (a11 - 2415021.076998695) / 29.530588853);
		int last; // Month 11 contains point of sun longutide 3*PI/2 (December
					// solstice)
		int i = 1; // We start with the month following lunar month 11
		int arc = INT(getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone) / 30);
		do {
			last = arc;
			i++;
			arc = INT(getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone) / 30);
		} while (arc != last && i < 14);
		return i - 1;
	}

	public int[] convertSolar2Lunar(int dd, int mm, int yy, double timeZone) {
		int lunarDay, lunarMonth, lunarYear, lunarLeap;
		int dayNumber = jdFromDate(dd, mm, yy);
		int k = INT((dayNumber - 2415021.076998695) / 29.530588853);
		int monthStart = getNewMoonDay(k + 1, timeZone);
		if (monthStart > dayNumber) {
			monthStart = getNewMoonDay(k, timeZone);
		}
		int a11 = getLunarMonth11(yy, timeZone);
		int b11 = a11;
		if (a11 >= monthStart) {
			lunarYear = yy;
			a11 = getLunarMonth11(yy - 1, timeZone);
		} else {
			lunarYear = yy + 1;
			b11 = getLunarMonth11(yy + 1, timeZone);
		}
		lunarDay = dayNumber - monthStart + 1;
		int diff = INT((monthStart - a11) / 29);
		lunarLeap = 0;
		lunarMonth = diff + 11;
		if (b11 - a11 > 365) {
			int leapMonthDiff = getLeapMonthOffset(a11, timeZone);
			if (diff >= leapMonthDiff) {
				lunarMonth = diff + 10;
				if (diff == leapMonthDiff) {
					lunarLeap = 1;
				}
			}
		}
		if (lunarMonth > 12) {
			lunarMonth = lunarMonth - 12;
		}
		if (lunarMonth >= 11 && diff < 4) {
			lunarYear -= 1;
		}
		return new int[] { lunarDay, lunarMonth, lunarYear, lunarLeap };
	}

	// --------------------Cover gioi tinh---------------//
	public int[] convertGioiTinh(String[] gioitinh) {
		int kiemtra = 3;
		if (gioitinh[0].equals("Nam")) {
			kiemtra = 0;
		}
		if (gioitinh[0].equals("Nữ")) {
			kiemtra = 1;
		}
		return new int[] { kiemtra };
	}

	// --------------------Close cover gioi tinh---------------//

	public int[] convertLunar2Solar(int lunarDay, int lunarMonth,
			int lunarYear, int lunarLeap, double timeZone) {
		int a11, b11;
		if (lunarMonth < 11) {
			a11 = getLunarMonth11(lunarYear - 1, timeZone);
			b11 = getLunarMonth11(lunarYear, timeZone);
		} else {
			a11 = getLunarMonth11(lunarYear, timeZone);
			b11 = getLunarMonth11(lunarYear + 1, timeZone);
		}
		int k = INT(0.5 + (a11 - 2415021.076998695) / 29.530588853);
		int off = lunarMonth - 11;
		if (off < 0) {
			off += 12;
		}
		if (b11 - a11 > 365) {
			int leapOff = getLeapMonthOffset(a11, timeZone);
			int leapMonth = leapOff - 2;
			if (leapMonth < 0) {
				leapMonth += 12;
			}
			if (lunarLeap != 0 && lunarMonth != leapMonth) {
				return new int[] { 0, 0, 0 };
			} else if (lunarLeap != 0 || off >= leapOff) {
				off += 1;
			}
		}
		int monthStart = getNewMoonDay(k + off, timeZone);
		return jdToDate(monthStart + lunarDay - 1);
	}

	public String getCanChiOfDay(int paramInt1, int paramInt2, int paramInt3) {
		int i = (int) getJulius(paramInt1, paramInt2, paramInt3);
		int j = (i + 1) % 12;
		int k = (i + 9) % 10;
		return this.dsCan[k] + " " + this.dsChi[j];
	}

	public int[] intGetCanChiOfDay(int paramInt1, int paramInt2, int paramInt3) {
		int i = (int) getJulius(paramInt1, paramInt2, paramInt3);
		int j = (i + 1) % 12;
		int k = (i + 9) % 10;
		// return this.dsCan[k] + " " + this.dsChi[j];
		return new int[] { k, j };
	}

	public String getCanChiOfHour(int hour, int day, int month, int year) {
		int i = (int) getJulius(day, month, year);
		/*
		 * int k = (i + 9) % 10; k = (k * 2 - 1) % 10; String canHour =
		 * this.dsCan[k-1];
		 */
		String str = "";
		if ((hour == 0) || (hour >= 23)) {
			str = this.dsChi[0];
		}
		for (int j = 0;; j++) {
			if (j >= this.dsCan.length) {
				int f = 0;
				for (int m = 3;; m += 2) {
					if (m >= 23) {
						break;
					}
					int n = m - 2;
					f++;
					if ((hour >= n) && (hour < m)) {
						str = this.dsChi[f];
						break;
					}
				}
				break;
			}
		}
		return str;
	}

	public String getCanOfMonth(int mm, int yy) {
		int i = (3 + mm + yy * 12) % 10;
		return this.dsCan[i];
	}

	public String getCanOfYear(int paramInt) {
		int i = (paramInt + 6) % 10;
		return this.dsCan[i];
	}

	public String getChiOfMonth(int paramInt) {
		int position = (paramInt + 1) % 12;
		return this.dsChi[position];
	}

	public String getChiOfYear(int paramInt) {
		int i = (paramInt + 8) % 12;
		return this.dsChi[i];
	}

	public String getGioHoangDao(int dd, int mm, int yy) {
		int i = (int) getJulius(dd, mm, yy);
		int nChi = (i + 1) % 12;
		String gio_hoang_dao = "";
		if (nChi == KChiDan || nChi == KChiThan) {
			gio_hoang_dao = "Zi (Rat)(23-1h), Chou (Ox)(1-3h), Chen (Dragon)(7-9h), Si (Snake)(9-11h), Wei (Goat)(13-15h), Xu (Dog)(19-21h)";
		} else if (nChi == KChiMao || nChi == KChiDau) {
			gio_hoang_dao = "Zi (Rat)(23-1h), Yin (Tiger)(3-5h), Mao (Rabbit)(5-7h), Wu (Horse)(11-13h), Wei (Goat)(13-15h), You (Rooster)(17-19h)";
		} else if (nChi == KChiThin || nChi == KChiTuat) {
			gio_hoang_dao = "Yin (Tiger)(3-5h), Chen (Dragon)(7-9h), Si (Snake)(9-11h), Shen (Monkey)(15-17h), Dậu(17-19h), Hai (Pig)(21-23h)";
		} else if (nChi == KChiTi || nChi == KChiHoi) {
			gio_hoang_dao = "Chou (Ox)(1-3h), Chen (Dragon)(7-9h), Wu (Horse)(11-13h), Wei (Goat)(13-15h), Xu (Dog)(19-21h), Hai (Pig)(21-23h)";
		} else if (nChi == KChiTy || nChi == KChiNgo) {
			gio_hoang_dao = "Zi (Rat)(23-1h), Chou (Ox)(1-3h), Mao (Rabbit)(5-7h), Wu (Horse)(11-13h), Shen (Monkey)(15-17h), You (Rooster)(17-19h)";
		} else if (nChi == KChiSuu || nChi == KChiMui) {
			gio_hoang_dao = "Yin (Tiger)(3-5h, Mao (Rabbit)(5-7h), Si (Snake)(9-11h), Shen (Monkey)(15-17h), Xu (Dog)(19-21h), Hai (Pig)(21-23h)";
		}
		return gio_hoang_dao;
	}

	public int IsBadGoodDay(int dd, int mm, int yy, int mAm) {
		int i = (int) getJulius(dd, mm, yy);
		int nChi = (i + 1) % 12;
		int result = NGAYBINHTHUONG;
		int tmpMonth = (mAm + 1) % 12;
		int tmp = (nChi + tmpMonth * 2) % 12;
		if (tmp == KChiThan || tmp == KChiDau || tmp == KChiSuu
				|| tmp == KChiMao)
			result = NGAYHOANGDAO;
		else if (tmp == KChiHoi || tmp == KChiDan || tmp == KChiTy
				|| tmp == KChiMui)
			result = NGAYHACDAO;
		return result;
	}

	// Kiem tra nam nhuan
	public boolean checkLeap(int yy) {
		if ((yy % 4 == 0 && yy % 100 != 0) || yy % 400 == 0) {
			return true;
		}
		return false;
	}

	public static final int NGAYHOANGDAO = 999;
	public static final int NGAYHACDAO = 000;
	public static final int NGAYBINHTHUONG = 111;

	public static final String STR_NGAYHOANGDAO = "Auspicious";
	public static final String STR_NGAYHACDAO = "Inauspicious";
	public static final String STR_NGAYBINHTHUONG = "";

	public long getJulius(int paramInt1, int paramInt2, int paramInt3) {
		int i = (14 - paramInt2) / 12;
		int j = paramInt3 + 4800 - i;
		int k = -3 + (paramInt2 + i * 12);
		int m = -32045
				+ (paramInt1 + (2 + k * 153) / 5 + j * 365 + j / 4 - j / 100 + j / 400);
		if (m < 2299161) {
			m = -32083 + (paramInt1 + (2 + k * 153) / 5 + j * 365 + j / 4);
		}
		return m;
	}

	// Tiết khí tính theo lịch dương
	public String getTietKhi(int dd, int mm, int yy) {
		int jd = jdFromDate(dd, mm, yy);
		jd = jd + 12 / 24 + 59 / 1440 + 590 / 86400;
		// tinh theo GMT 7
		jd = jd - 7 / 24;
		double t = (jd - 2451545.0) / 36525;
		double lo = (280.46645 + 36000.76983 * t + 0.0003032 * t * t);
		double m = (357.52910 + 35999.05030 * t - 0.0001559 * t * t - 0.00000048
				* t * t * t);
		double c = (1.914600 - 0.004817 * t - 0.000014 * t * t) * Math.sin(m)
				+ (0.01993 - 0.000101 * t) * Math.sin(2 * m) + 0.000290
				* Math.sin(m * m * m);
		double theta = lo + c;
		double lambda = theta - 0.00569 - 0.00478
				* Math.sin(125.04 - 1934.136 * t);
		lambda = lambda - 360 * INT(lambda / 360);

		String tietkhi = "";
		if (lambda > 314.4 && lambda < 315.5) {
			tietkhi = "Lichun  (start of spring)";
		} else if (lambda >= 315.5 && lambda < 330) {
			tietkhi = "Middle Lichun  (start of spring) - Yushui (rain water)";
		} else if (lambda >= 329.4 && lambda <= 330.5) {
			tietkhi = "Yushui (rain water)";
		} else if (lambda > 330.5 && lambda < 345.5) {
			tietkhi = "Middle Yushui (rain water) - Jingzhe (awakening of insects)";
		} else if (lambda > 344.5 && lambda <= 346) {
			tietkhi = "Jingzhe (awakening of insects)";
		} else if (lambda > 0 && lambda <= 1) {
			tietkhi = "Chunfen (vernal equinox)";
		} else if (lambda >= 0 && lambda <= 14) {
			tietkhi = "Middle Chunfen (vernal equinox) - Qingming (clear and bright)";
		} else if (lambda >= 14 && lambda <= 16) {
			tietkhi = "Qingming (clear and bright)";
		} else if (lambda > 16 && lambda <= 29) {
			tietkhi = "Middle Qingming (clear and bright) - Guyu (grain rain)";
		} else if (lambda > 29 && lambda < 31) {
			tietkhi = "Guyu (grain rain)";
		} else if (lambda >= 31 && lambda <= 44) {
			tietkhi = "Middle Guyu (grain rain) - Lixia (start of summer)";
		} else if (lambda > 44 && lambda <= 46) {
			tietkhi = "Lixia (start of summer)";
		} else if (lambda > 46 && lambda <= 59) {
			tietkhi = "Middle Lixia (start of summer) - Xiaoman (grain full)";
		} else if (lambda > 59 && lambda <= 61) {
			tietkhi = "Xiaoman (grain full)";
		} else if (lambda > 61 && lambda <= 74) {
			tietkhi = "Middle Xiaoman (grain full) - Mangzhong (grain in ear)";
		} else if (lambda > 74 && lambda <= 76) {
			tietkhi = "Mangzhong (grain in ear)";
		} else if (lambda > 76 && lambda <= 89) {
			tietkhi = "Mangzhong (grain in ear)";
		} else if (lambda > 89 && lambda < 91) {
			tietkhi = "Xiazhi (summer solstice)";
		} else if (lambda >= 91 && lambda <= 104) {
			tietkhi = "Middle Mangzhong (grain in ear) - Xiaoshu (minor heat)";
		} else if (lambda > 104 && lambda < 106) {
			tietkhi = "Xiaoshu (minor heat)";
		} else if (lambda >= 106 && lambda <= 119) {
			tietkhi = "Middle Xiaoshu (minor heat) - Dashu (major heat)";
		} else if (lambda > 119 && lambda < 121) {
			tietkhi = "Dashu (major heat)";
		} else if (lambda >= 121 && lambda <= 134) {
			tietkhi = "Middle Dashu (major heat) - Liqiu (start of autumn)";
		} else if (lambda > 134 && lambda < 136) {
			tietkhi = "Liqiu (start of autumn)";
		} else if (lambda >= 136 && lambda <= 149) {
			tietkhi = "Middle Liqiu (start of autumn) - Bailu (white dew)";
		} else if (lambda > 149 && lambda < 151) {
			tietkhi = "Chushu (limit of heat)";
		} else if (lambda >= 151 && lambda <= 164) {
			tietkhi = "Middle Chushu (limit of heat) - Bailu (white dew)";
		} else if (lambda > 164 && lambda < 166) {
			tietkhi = "Bailu (white dew)";
		} else if (lambda >= 166 && lambda <= 179) {
			tietkhi = "Middle Bailu (white dew) - Qiufen (autumnal equinox)";
		} else if (lambda > 179 && lambda < 181) {
			tietkhi = "Qiufen (autumnal equinox)";
		} else if (lambda >= 181 && lambda <= 194) {
			tietkhi = "Middle Qiufen (autumnal equinox) - Hanlu (cold dew)";
		} else if (lambda > 194 && lambda < 196) {
			tietkhi = "Hanlu (cold dew)";
		} else if (lambda >= 196 && lambda <= 209) {
			tietkhi = "Middle Hanlu (cold dew) - Shuangjiang (frost descent)";
		} else if (lambda > 209 && lambda <= 211) {
			tietkhi = "Shuangjiang (frost descent)";
		} else if (lambda > 211 && lambda <= 224) {
			tietkhi = "Middle Shuangjiang (frost descent) - Lidong (start of winter)";
		} else if (lambda > 224 && lambda < 226) {
			tietkhi = "Lidong (start of winter)";
		} else if (lambda >= 226 && lambda <= 239) {
			tietkhi = "Middle Lidong (start of winter) - Xiaoxue (minor snow)";
		} else if (lambda > 239 && lambda < 241) {
			tietkhi = "Xiaoxue (minor snow)";
		} else if (lambda >= 241 && lambda <= 254) {
			tietkhi = "Middle Xiaoxue (minor snow) - Daxue (major snow)";
		} else if (lambda > 254 && lambda < 256) {
			tietkhi = "Daxue (major snow)";
		} else if (lambda >= 256 && lambda <= 269) {
			tietkhi = "Middle Daxue (major snow) - Dongzhi (winter solstice)";
		} else if (lambda > 269 && lambda < 271) {
			tietkhi = "Dongzhi (winter solstice)";
		} else if (lambda >= 271 && lambda <= 284) {
			tietkhi = "Middle Dongzhi (winter solstice) - Xiaohan (minor cold)";
		} else if (lambda > 284 && lambda < 286) {
			tietkhi = "Xiaohan (minor cold)";
		} else if (lambda >= 286 && lambda <= 299) {
			tietkhi = "Middle Xiaohan (minor cold) - Dahan (major cold)";
		} else if (lambda > 299 && lambda < 301) {
			tietkhi = "Dahan (major cold)";
		} else if (lambda >= 301 && lambda <= 314) {
			tietkhi = "Middle Dahan (major cold) - Lichun  (start of spring)";
		}
		return tietkhi;
	}

	// Can
	int KCanGiap = 0;
	int KCanAt = 1;
	int KCanBinh = 2;
	int KCanDinh = 3;
	int KCanMau = 4;
	int KCanKy = 5;
	int KCanCanh = 6;
	int KCanTan = 7;
	int KCanNham = 8;
	int KCanQuy = 9;

	// Chi
	int KChiTi = 0;// Ti'
	int KChiSuu = 1;
	int KChiDan = 2;
	int KChiMao = 3;
	int KChiThin = 4;
	int KChiTy = 5;// Ty.
	int KChiNgo = 6;
	int KChiMui = 7;
	int KChiThan = 8;
	int KChiDau = 9;
	int KChiTuat = 10;
	int KChiHoi = 11;

	public String getTuoiXungKhac(int dd, int mm, int yy) {
		int i = (int) getJulius(dd, mm, yy);
		int nChi = (i + 1) % 12;
		int nCan = (i + 9) % 10;
		String xung_ky = "";
		if (nCan == KCanGiap && nChi == KChiTi) // Mau Ngo - Nham Ngo - Geng Dan
												// - Geng Than
			xung_ky = "Wu Wu (Horse) - Ren Wu (Horse) - Geng Yin (Tiger) - Geng Shen (Monkey)";
		else if (nCan == KCanAt && nChi == KChiSuu) // Ky Mui - Quy Mui - Tan
													// Mao - Tan Dau
			xung_ky = "Ji Wei (Goat) - Gui Wei (Goat) - Xin Mao (Rabbit) - Xin You (Rooster)";
		else if (nCan == KCanBinh && nCan == KChiDan) // Giap Than - Nham Than -
														// Nham Tuat - Nham Thin
			xung_ky = "Jia Shen (Monkey) - Ren Shen (Monkey) - Ren TuYi - Ren Chen (Dragon)";
		else if (nCan == KCanDinh && nChi == KChiMao) // Yi You (Rooster) - Gui
														// You (Rooster) - Gui
														// Si (Snake) - Gui Hai
														// (Pig)
			xung_ky = "Yi You (Rooster) - Gui You (Rooster) - Gui Si (Snake) - Gui Hai (Pig)";
		else if (nCan == KCanMau && nChi == KChiThin) // Geng TuYi - Bing TuYi
			xung_ky = "Geng TuYi - Bing TuYi";
		else if (nCan == KCanKy && nChi == KChiTy) // Xin Hai (Pig) - Ding Hai
													// (Pig)
			xung_ky = "Xin Hai (Pig) - Ding Hai (Pig)";
		else if (nCan == KCanCanh && nChi == KChiNgo) // Ren Zi (Rat) - Bing Zi
														// (Rat) - Jia Shen
														// (Monkey) - Jia Yin
														// (Tiger)
			xung_ky = "Ren Zi (Rat) - Bing Zi (Rat) - Jia Shen (Monkey) - Jia Yin (Tiger)";
		else if (nCan == KCanTan && nChi == KChiMui) // Gui Chou (Ox) - Ding
														// Chou (Ox) - Yi You
														// (Rooster) - Yi Mao
														// (Rabbit)
			xung_ky = "Gui Chou (Ox) - Ding Chou (Ox) - Yi You (Rooster) - Yi Mao (Rabbit)";
		else if (nCan == KCanNham && nChi == KChiThan) // Bing Yin (Tiger) -
														// Geng Yin (Tiger) -
														// Bing Shen (Monkey)
			xung_ky = "Bing Yin (Tiger) - Geng Yin (Tiger) - Bing Shen (Monkey)";
		else if (nCan == KCanQuy && nChi == KChiDau) // Ding Mao (Rabbit) - Xin
														// Mao (Rabbit) - Ding
														// You (Rooster)
			xung_ky = "Ding Mao (Rabbit) - Xin Mao (Rabbit) - Ding You (Rooster)";
		else if (nCan == KCanGiap && nChi == KChiTuat) // Ren Chen (Dragon) -
														// Geng Chen (Dragon) -
														// Geng TuYi
			xung_ky = "Ren Chen (Dragon) - Geng Chen (Dragon) - Geng TuYi";
		else if (nCan == KCanAt && nChi == KChiHoi) // Gui Si (Snake) - Xin Si
													// (Snake) - Xin Hai (Pig)
			xung_ky = "Gui Si (Snake) - Xin Si (Snake) - Xin Hai (Pig)";
		else if (nCan == KCanBinh && nChi == KChiTi) // Geng Wu (Horse) - Wu Wu
														// (Horse)
			xung_ky = "Geng Wu (Horse) - Wu Wu (Horse)";
		else if (nCan == KCanDinh && nChi == KChiSuu) // Xin Wei (Goat) - Ji Wei
														// (Goat)
			xung_ky = "Xin Wei (Goat) - Ji Wei (Goat)";
		else if (nCan == KCanMau && nChi == KChiDan) // Geng Shen (Monkey) - Jia
														// Shen (Monkey)
			xung_ky = "Geng Shen (Monkey) - Jia Shen (Monkey)";
		else if (nCan == KCanKy && nChi == KChiMao) // Xin You (Rooster) - Yi
													// You (Rooster)
			xung_ky = "Xin You (Rooster) - Yi You (Rooster)";
		else if (nCan == KCanCanh && nChi == KChiThin) // Jia TuYi - Wu TuYi -
														// Jia Chen (Dragon)
			xung_ky = "Jia TuYi - Wu TuYi - Jia Chen (Dragon)";
		else if (nCan == KCanTan && nChi == KChiTy) // Yi Hai (Pig) - Ji Hai
													// (Pig) - Yi Si (Snake)
			xung_ky = "Yi Hai (Pig) - Ji Hai (Pig) - Yi Si (Snake)";
		else if (nCan == KCanNham && nChi == KChiNgo) // Jia Zi (Rat) - Geng Zi
														// (Rat) - Bing TuYi -
														// Bing Chen (Dragon)
			xung_ky = "Jia Zi (Rat) - Geng Zi (Rat) - Bing TuYi - Bing Chen (Dragon)";
		else if (nCan == KCanQuy && nChi == KChiMui) // Yi Chou (Ox) - Xin Chou
														// (Ox) - Ding Hai (Pig)
														// - Ding Si (Snake)
			xung_ky = "Yi Chou (Ox) - Xin Chou (Ox) - Ding Hai (Pig) - Ding Si (Snake)";
		else if (nCan == KCanGiap && nChi == KChiThan) // Wu Yin (Tiger) - Bing
														// Yin (Tiger) - Geng Wu
														// (Horse) - Geng Zi
														// (Rat)
			xung_ky = "Wu Yin (Tiger) - Bing Yin (Tiger) - Geng Wu (Horse) - Geng Zi (Rat)";
		else if (nCan == KCanAt && nChi == KChiDau) // Ji Mao (Rabbit) - Ding
													// Mao (Rabbit) - Xin Wei
													// (Goat) - Xin Chou (Ox)
			xung_ky = "Ji Mao (Rabbit) - Ding Mao (Rabbit) - Xin Wei (Goat) - Xin Chou (Ox)";
		else if (nCan == KCanBinh && nChi == KChiTuat) // Wu Chen (Dragon) - Ren
														// Chen (Dragon) - Ren
														// Wu (Horse) - Ren Zi
														// (Rat)
			xung_ky = "Wu Chen (Dragon) - Ren Chen (Dragon) - Ren Wu (Horse) - Ren Zi (Rat)";
		else if (nCan == KCanDinh && nChi == KChiHoi)// Ji Si (Snake) - Gui Si
														// (Snake) - Gui Wei
														// (Goat) - Gui Chou
														// (Ox)
			xung_ky = "Ji Si (Snake) - Gui Si (Snake) - Gui Wei (Goat) - Gui Chou (Ox)";
		else if (nCan == KCanMau && nChi == KChiTi) // Bing Wu (Horse) - Jia Wu
													// (Horse)
			xung_ky = "Bing Wu (Horse) - Jia Wu (Horse)";
		else if (nCan == KCanKy && nChi == KChiSuu) // Ding Wei (Goat) - Yi Wei
													// (Goat)
			xung_ky = "Ding Wei (Goat) - Yi Wei (Goat)";
		else if (nCan == KCanCanh && nChi == KChiDan) // Ren Shen (Monkey) - Wu
														// Shen (Monkey) - Jia
														// Zi (Rat) - Jia Wu
														// (Horse)
			xung_ky = "Ren Shen (Monkey) - Wu Shen (Monkey) - Jia Zi (Rat) - Jia Wu (Horse)";
		else if (nCan == KCanTan && nChi == KChiMao)// Gui You (Rooster) - Ji
													// You (Rooster) - Yi Chou
													// (Ox) - Yi Wei (Goat)
			xung_ky = "Gui You (Rooster) - Ji You (Rooster) - Yi Chou (Ox) - Yi Wei (Goat)";
		else if (nCan == KCanNham && nChi == KChiThin) // Bing TuYi - Jia TuYi -
														// Bing Yin (Tiger)
			xung_ky = "Bing TuYi - Jia TuYi - Bing Yin (Tiger)";
		else if (nCan == KCanQuy && nChi == KChiTy) // Ding Hai (Pig) - Yi Hai
													// (Pig) - Ding Mao (Rabbit)
			xung_ky = "Ding Hai (Pig) - Yi Hai (Pig) - Ding Mao (Rabbit)";
		else if (nCan == KCanGiap && nChi == KChiNgo) // Wu Zi (Rat) - Ren Zi
														// (Rat) - Geng Yin
														// (Tiger) - Ren Yin
														// (Tiger)
			xung_ky = "Wu Zi (Rat) - Ren Zi (Rat) - Geng Yin (Tiger) - Ren Yin (Tiger)";
		else if (nCan == KCanAt && nChi == KChiMui) // Ji Chou (Ox) - Gui Chou
													// (Ox) - Xin Mao (Rabbit) -
													// Xin You (Rooster)
			xung_ky = "Ji Chou (Ox) - Gui Chou (Ox) - Xin Mao (Rabbit) - Xin You (Rooster)";
		else if (nCan == KCanBinh && nChi == KChiThan) // Jia Yin (Tiger) - Ren
														// Shen (Monkey) - Ren
														// TuYi - Ren Chen
														// (Dragon)
			xung_ky = "Jia Yin (Tiger) - Ren Shen (Monkey) - Ren TuYi - Ren Chen (Dragon)";
		else if (nCan == KCanDinh && nChi == KChiDau) // Yi Mao (Rabbit) - Gui
														// Mao (Rabbit) - Gui Si
														// (Snake) - Gui Hai
														// (Pig)
			xung_ky = "Yi Mao (Rabbit) - Gui Mao (Rabbit) - Gui Si (Snake) - Gui Hai (Pig)";
		else if (nCan == KCanMau && nChi == KChiTuat) // Geng Chen (Dragon) -
														// Bing Chen (Dragon)
			xung_ky = "Geng Chen (Dragon) - Bing Chen (Dragon)";
		else if (nCan == KCanKy && nChi == KChiHoi) // Xin Si (Snake) - Ding Si
													// (Snake)
			xung_ky = "Xin Si (Snake) - Ding Si (Snake)";
		else if (nCan == KCanCanh && nChi == KChiTi) // Ren Wu (Horse) - Bing Wu
														// (Horse) - Jia Shen
														// (Monkey) - Jia Yin
														// (Tiger)
			xung_ky = "Ren Wu (Horse) - Bing Wu (Horse) - Jia Shen (Monkey) - Jia Yin (Tiger)";
		else if (nCan == KCanTan && nChi == KChiSuu) // Gui Wei (Goat) - Ding
														// Wei (Goat) - Yi You
														// (Rooster) - Yi Mao
														// (Rabbit)
			xung_ky = "Gui Wei (Goat) - Ding Wei (Goat) - Yi You (Rooster) - Yi Mao (Rabbit)";
		else if (nCan == KCanNham && nChi == KChiDan)// Geng Shen (Monkey) -
														// Bing Shen (Monkey) -
														// Bing Yin (Tiger)
			xung_ky = "Geng Shen (Monkey) - Bing Shen (Monkey) - Bing Yin (Tiger)";
		else if (nCan == KCanQuy && nChi == KChiMao) // Xin You (Rooster) - Ding
														// You (Rooster) - Ding
														// Mao (Rabbit)
			xung_ky = "Xin You (Rooster) - Ding You (Rooster) - Ding Mao (Rabbit)";
		else if (nCan == KCanGiap && nChi == KChiThin) // Ren TuYi - Geng TuYi -
														// Geng Chen (Dragon)
			xung_ky = "Ren TuYi - Geng TuYi - Geng Chen (Dragon)";
		else if (nCan == KCanAt && nChi == KChiTy) // Gui Hai (Pig) - Xin Hai
													// (Pig) - Xin Si (Snake)
			xung_ky = "Gui Hai (Pig) - Xin Hai (Pig) - Xin Si (Snake)";
		else if (nCan == KCanBinh && nChi == KChiNgo) // Wu Zi (Rat) - Geng Zi
														// (Rat)
			xung_ky = "Wu Zi (Rat) - Geng Zi (Rat)";
		else if (nCan == KCanDinh && nChi == KChiMui) // Ji Chou (Ox) - Xin Chou
														// (Ox)
			xung_ky = "Ji Chou (Ox) - Xin Chou (Ox)";
		else if (nCan == KCanMau && nChi == KChiThan) // Geng Yin (Tiger) - Jia
														// Yin (Tiger)
			xung_ky = "Geng Yin (Tiger) - Jia Yin (Tiger)";
		else if (nCan == KCanKy && nChi == KChiDau) // Xin Mao (Rabbit) - Yi Mao
													// (Rabbit)
			xung_ky = "Xin Mao (Rabbit) - Yi Mao (Rabbit)";
		else if (nCan == KCanCanh && nChi == KChiTuat) // Jia Chen (Dragon) - Wu
														// Chen (Dragon) - Jia
														// TuYi
			xung_ky = "Jia Chen (Dragon) - Wu Chen (Dragon) - Jia TuYi";
		else if (nCan == KCanTan && nChi == KChiHoi) // Yi Si (Snake) - Ji Si
														// (Snake) - Yi Hai
														// (Pig)
			xung_ky = "Yi Si (Snake) - Ji Si (Snake) - Yi Hai (Pig)";
		else if (nCan == KCanNham && nChi == KChiTi) // Jia Wu (Horse) - Geng Wu
														// (Horse) - Bing TuYi -
														// Bing Chen (Dragon)
			xung_ky = "Jia Wu (Horse) - Geng Wu (Horse) - Bing TuYi - Bing Chen (Dragon)";
		else if (nCan == KCanQuy && nChi == KChiSuu)// Yi Wei (Goat) - Xin Wei
													// (Goat) - Ding Hai (Pig) -
													// Ding Si (Snake)
			xung_ky = "Yi Wei (Goat) - Xin Wei (Goat) - Ding Hai (Pig) - Ding Si (Snake)";
		else if (nCan == KCanGiap && nChi == KChiDan) // Wu Shen (Monkey) - Bing
														// Shen (Monkey) - Geng
														// Wu (Horse) - Geng Zi
														// (Rat)
			xung_ky = "Wu Shen (Monkey) - Bing Shen (Monkey) - Geng Wu (Horse) - Geng Zi (Rat)";
		else if (nCan == KCanAt && nChi == KChiMao) // Ji You (Rooster) - Ding
													// You (Rooster) - Xin Wei
													// (Goat) - Xin Chou (Ox)
			xung_ky = "Ji You (Rooster) - Ding You (Rooster) - Xin Wei (Goat) - Xin Chou (Ox)";
		else if (nCan == KCanBinh && nChi == KChiThin) // Wu TuYi - Ren TuYi -
														// Ren Wu (Horse) - Ren
														// Zi (Rat)
			xung_ky = "Wu TuYi - Ren TuYi - Ren Wu (Horse) - Ren Zi (Rat)";
		else if (nCan == KCanDinh && nChi == KChiTy) // Ji Hai (Pig) - Gui Hai
														// (Pig) - Gui Chou (Ox)
														// - Gui Wei (Goat)
			xung_ky = "Ji Hai (Pig) - Gui Hai (Pig) - Gui Chou (Ox) - Gui Wei (Goat)";
		else if (nCan == KCanMau && nChi == KChiNgo) // Bing Zi (Rat) - Jia Zi
														// (Rat)
			xung_ky = "Bing Zi (Rat) - Jia Zi (Rat)";
		else if (nCan == KCanKy && nChi == KChiMui) // Ding Chou (Ox) - Yi Chou
													// (Ox)
			xung_ky = "Ding Chou (Ox) - Yi Chou (Ox)";
		else if (nCan == KCanCanh && nChi == KChiThan) // Ren Yin (Tiger) - Wu
														// Yin (Tiger) - Jia Zi
														// (Rat) - Jia Wu
														// (Horse)
			xung_ky = "Ren Yin (Tiger) - Wu Yin (Tiger) - Jia Zi (Rat) - Jia Wu (Horse)";
		else if (nCan == KCanTan && nChi == KChiDau) // Gui Mao (Rabbit) - Ji
														// Mao (Rabbit) - Yi
														// Chou (Ox) - Yi Wei
														// (Goat)
			xung_ky = "Gui Mao (Rabbit) - Ji Mao (Rabbit) - Yi Chou (Ox) - Yi Wei (Goat)";
		else if (nCan == KCanNham && nChi == KChiTuat) // Bing Chen (Dragon) -
														// Jia Chen (Dragon) -
														// Bing Shen (Monkey) -
														// Bing Yin (Tiger)
			xung_ky = "Bing Chen (Dragon) - Jia Chen (Dragon) - Bing Shen (Monkey) - Bing Yin (Tiger)";
		else if (nCan == KCanQuy && nChi == KChiHoi) // Ding Si (Snake) - Yi Si
														// (Snake) - Ding Mao
														// (Rabbit) - Ding You
														// (Rooster)
			xung_ky = "Ding Si (Snake) - Yi Si (Snake) - Ding Mao (Rabbit) - Ding You (Rooster)";
		return xung_ky;
	}

	public String getHuongXuatHanh(int dd, int mm, int yy) {
		int i = (int) getJulius(dd, mm, yy);
		int nCan = (i + 9) % 10;
		String huong_xuat_hanh = "";
		String sBuf1 = "";
		if (nCan == KCanGiap || nCan == KCanKy)
			sBuf1 = "East North";
		else if (nCan == KCanAt || nCan == KCanCanh)
			sBuf1 = "West North";
		else if (nCan == KCanBinh || nCan == KCanTan)
			sBuf1 = "West South";
		else if (nCan == KCanDinh || nCan == KCanNham)
			sBuf1 = "chính Nam";
		else if (nCan == KCanMau || nCan == KCanQuy)
			sBuf1 = "East South";

		String sBuf2 = "";
		if (nCan == KCanGiap || nCan == KCanAt)
			sBuf2 = "East South"; // Dong South
		else if (nCan == KCanBinh || nCan == KCanDinh)
			sBuf2 = "East"; // Dong
		else if (nCan == KCanMau)
			sBuf2 = "North"; // Bac
		else if (nCan == KCanKy)
			sBuf2 = "South"; // South
		else if (nCan == KCanCanh || nCan == KCanTan)
			sBuf2 = "West South"; // Tay South
		else if (nCan == KCanNham)
			sBuf2 = "West"; // Tay
		else if (nCan == KCanQuy)
			sBuf2 = "West North"; // tay Bac
		huong_xuat_hanh = "God of joy: " + sBuf1 + " - " + "God of wealth: "
				+ sBuf2;
		return huong_xuat_hanh;
	}

	public String getSaoTotXau(int nDayD, int nMonthD, int nYearD, int nDayAm,
			int nMonthAm) {
		int i = (int) getJulius(nDayD, nMonthD, nYearD);
		int nChi = (i + 1) % 12;
		int nCan = (i + 9) % 10;
		String sOut = "";

		if ((nDayD == 20 && (nMonthD == 3 || nMonthD == 6))
				|| (nDayD == 22 && nMonthD == 9)
				|| (nDayD == 21 && nMonthD == 12))
			sOut += "<strong><font color = red>Ngày Tứ li:</font></strong><font color=black> Những ngày này khí vận suy kiệt, không nên dùng vào việc gì</font><br>";
		else if ((nDayD == 3 && nMonthD == 2) || (nDayD == 5 && nMonthD == 5)
				|| (nDayD == 8 && nMonthD == 8)
				|| (nDayD == 7 && nMonthD == 11))
			sOut += "<strong><font color = red>Ngày Tứ Tuyệt:</font></strong> <font color=black>Dùng việc gì cũng không lợi</font><br>";

		if (nDayAm == 5 || nDayAm == 14 || nDayAm == 23) {
			sOut += "<strong><font color = red>Nguyệt Kỵ:</font></strong> <font color=black>không nên khởi hành làm việc gì cả.</font>";
			if (nMonthAm == 1 || nMonthAm == 4 || nMonthAm == 7
					|| nMonthAm == 10)
				sOut += "<font color=black>Đại kỵ ngày mùng 5<br></font>";
			else if (nMonthAm == 2 || nMonthAm == 5 || nMonthAm == 8
					|| nMonthAm == 11)
				sOut += "<font color=black>Đại kỵ ngày mùng 14<br></font>";
			else if (nMonthAm == 3 || nMonthAm == 6 || nMonthAm == 9
					|| nMonthAm == 12)
				sOut += "<font color=black>Đại kỵ ngày mùng 23<br></font>";
		}
		if (nDayAm == 3 || nDayAm == 7 || nDayAm == 13 || nDayAm == 18
				|| nDayAm == 22 || nDayAm == 27)
			sOut += "<strong><font color = red>Tam nương:</font></strong> <font color=black>Trăm sự đều kỵ, chánh kỵ xuất hành<br></font>";

		if ((nDayAm == 13 && nMonthAm == 1) || (nDayAm == 11 && nMonthAm == 2)
				|| (nDayAm == 9 && nMonthAm == 3)
				|| (nDayAm == 7 && nMonthAm == 4)
				|| (nDayAm == 5 && nMonthAm == 5)
				|| (nDayAm == 3 && nMonthAm == 6)
				|| (nDayAm == 8 && nMonthAm == 7)
				|| (nDayAm == 29 && nMonthAm == 7)
				|| (nDayAm == 27 && nMonthAm == 8)
				|| (nDayAm == 25 && nMonthAm == 9)
				|| (nDayAm == 23 && nMonthAm == 10)
				|| (nDayAm == 21 && nMonthAm == 11)
				|| (nDayAm == 19 && nMonthAm == 12)) {
			sOut += "<strong><font color = red>Dương công kỵ nhật:</font></strong><font color=black> ngày xấu nhất trong năm</font><br>";
		}

		// Tinh Ngày Vãng vong
		if ((nMonthAm == 1 && nChi == KChiDan)
				|| (nMonthAm == 2 && nChi == KChiTy)
				|| (nMonthAm == 3 && nChi == KChiThan)
				|| (nMonthAm == 4 && nChi == KChiHoi)
				|| (nMonthAm == 5 && nChi == KChiMao)
				|| (nMonthAm == 6 && nChi == KChiNgo)
				|| (nMonthAm == 7 && nChi == KChiDau)
				|| (nMonthAm == 8 && nChi == KChiTi)
				|| (nMonthAm == 9 && nChi == KChiThin)
				|| (nMonthAm == 10 && nChi == KChiMui)
				|| (nMonthAm == 11 && nChi == KChiTuat)
				|| (nMonthAm == 12 && nChi == KChiSuu)) {
			sOut += "<strong><font color = red>Ngày Vãng vong:</font></strong><font color=black> Trăm sự đều kỵ, chánh kỵ xuất hành</font><br>";
		}

		// Tinh ngay Sat chu
		if ((nMonthAm == 1 && nChi == KChiTy)
				|| (nMonthAm == 2 && nChi == KChiTi)
				|| (nMonthAm == 3 && nChi == KChiMui)
				|| (nMonthAm == 4 && nChi == KChiMao)
				|| (nMonthAm == 5 && nChi == KChiThan)
				|| (nMonthAm == 6 && nChi == KChiTuat)
				|| (nMonthAm == 7 && nChi == KChiSuu)
				|| (nMonthAm == 8 && nChi == KChiHoi)
				|| (nMonthAm == 9 && nChi == KChiNgo)
				|| (nMonthAm == 10 && nChi == KChiDau)
				|| (nMonthAm == 11 && nChi == KChiDan)
				|| (nMonthAm == 12 && nChi == KChiThin)) {
			sOut += "<strong><font color = red>Ngày sát chủ:</font></strong><font color=black> Kỵ xây cất, cưới gả</font><br>";
		}

		List<String> arrSao = new ArrayList<String>();

		// Lay cac sao tot
		sOut += "<strong><font color = red>Lucky Star:</font></strong>";
		if (nMonthAm == 1) {
			if (nCan == KCanQuy)
				arrSao.add("<font color=black>Tiande");
			else if (nCan == KCanAt)
				arrSao.add("<font color=black>Tianfu");
			else if (nCan == KCanBinh)
				arrSao.add("<font color=black>Yuekong");
			else if (nCan == KCanDinh)
				arrSao.add("<font color=black>Yuedehe");
			else if (nCan == KCanMau) {
				arrSao.add("<font color=black>Tiandehe");
				if (nChi == KChiThan) {
					arrSao.add("<font color=black>Tianshe (good for sacrifice ceremonies, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
				}
			} else if (nCan == KCanCanh || nCan == KCanTan)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanNham) {
				arrSao.add("<font color=black>Yuede, Yue'en");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Shengqi, Yihou, Mushang, Dahongsha, Qinglong");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Suihe, Xushi, Dahongsha, Mingtang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Mandexing, Yao'an (Tiangui), Fuhou");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tianfu,Tiancai, Luku");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Dicai, Huoyao, Jintang");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Shengqi (good for building houses, repairing houses, ground breaking, planting trees), Tianma (good for starting a journey, transaction, praying for wealth), Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Dahongsha");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tiancheng, Suihe, Dahongsha, Yutang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Mingxing, Mandexing, Fuhou (good for praying for wealth, grand opening)");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Yinde");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tianfu, Tianguan, Luku (good for praying for wealth, grand opening, transaction), Jingxin (good for burial rites)");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Yuejie, Huoyao, Puhu (good for donation, wedding, starting a journey)");
			}
		} else if (nMonthAm == 2) {
			if (nCan == KCanGiap) {
				arrSao.add("<font color=black>Yuede, Tiangui");
			} else if (nCan == KCanKy)
				arrSao.add("<font color=black>Yuedehe");
			else if (nCan == KCanAt)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanMau) {
				arrSao.add("<font color=black>Tianfu");
				if (nChi == KChiDan)
					arrSao.add("<font color=black>Tianshe (good for sacrifice ceremonies, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
			} else if (nCan == KCanCanh) {
				arrSao.add("<font color=black>Yuekong (good for building houses, making beds)");
			} else if (nCan == KCanDinh) {
				arrSao.add("<font color=black>Yue'en");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tiandehe, Tianguan, Suihe, Mushang (good for praying for wealth, grand opening), Dahongsha");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Jingxin (good for burial rites), Dahongsha, Huang'en");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Wufu, Jiqing, Puhu(good for donation, wedding, starting a journey), Fuhou (good for praying for wealth, grand opening), Qinglong");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Fusheng, Mingtang, Guanri");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Youweixing");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Shengxin (good for everything, especially praying for Good Luck, sacrifice), Luku (good for praying for wealth, grand opening, transaction), Yima");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tiancai (good for grand opening, praying for wealth), Yihou, Minri, Shide");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Dicai (good for grand opening, praying for wealth), Yinde, Mandexing, Xushi, Sanhe, Jintang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tiande, Tianma (good for starting a journey, transaction, praying for wealth), Yuejie, Jieshen (good for sacrifice,  litigation, requiem ceremonies to untie the knots of injustice), Yao'an");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tiancheng, Yutang");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Mingxing, Huoyao (bad if it meets Shousi), Liuhe");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Sanhe, Mushang (good for praying for wealth, grand opening)");
			}
		} else if (nMonthAm == 3) {
			if (nCan == KCanNham) {
				arrSao.add("<font color=black>Yuede, Tiande");
			} else if (nCan == KCanDinh)
				arrSao.add("<font color=black>Tiandehe, Yuedehe");
			else if (nCan == KCanAt)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanGiap)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanMau) {
				if (nChi == KChiDan)
					arrSao.add("<font color=black>Tianshe (good for sacrifice ceremonies, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
			}
			//
			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Mingxing, Shengxin (good for everything, especially praying for Good Luck, sacrifice), Sanhe, Mushang (good for praying for wealth, grand opening), Dahongsha");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Youweixing, Yihou, Dahongsha");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Yao'an");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Tianguan, Xushi, Yima (good for starting a journey), Fuhou (good for praying for wealth, grand opening), Huang'en");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Mandexing, Qinglong");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Wufu, Yinde, Mingtang");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Luku (good for praying for wealth, grand opening, transaction), Minri, Shide");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Huoyao (bad if it meets Shousi)");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tiancai (good for praying for wealth, grand opening), Jingxin(good for burial rites), Sanhe");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Fusheng, Dicai (good for praying for wealth, grand opening), Yuejie, Puhu(donation, wedding, starting a journey), Liuhe, Jintang");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tianma (good for starting a journey, transaction, praying for wealth), Fusheng, Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice)");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tiancheng, Jiqing, Suihe, Mushang (good for praying for wealth, grand opening), Yutang");
			}
		} else if (nMonthAm == 4) {
			if (nCan == KCanTan) {
				arrSao.add("<font color=black>Tiande, Tianfu");
			} else if (nCan == KCanQuy) {
				arrSao.add("<font color=black>Tianfu");
			} else if (nCan == KCanBinh) {
				arrSao.add("<font color=black>Tiandehe, Tiangui");
			} else if (nCan == KCanCanh) {
				arrSao.add("<font color=black>Yuede");
			} else if (nCan == KCanAt) {
				arrSao.add("<font color=black>Yuedehe (avoid litigation)");
			} else if (nCan == KCanDinh)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanGiap && nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianshe (good for sacrifice ceremonies, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianma (good for starting a journey, transaction, praying for wealth), Huoyao (bad if it meets Shousi)");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Tiancheng, Sanhe, Yutang");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Yinde, Puhu (good for donation, wedding, starting a journey), Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Mingxing, Jingxin (good for burial rites), Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Fusheng, Tianguan, Jiqing, Dahongsha");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Fuhou (good for praying for wealth, grand opening), Dahongsha, Huang'en");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Shengxin (good for everything, especially praying for Good Luck, sacrifice), Youweixing, Qinglong");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Luku (good for praying for wealth, grand opening, transaction), Yihou, Mingtang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Wufu, Xushi, Liuhe");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Mandexing, Yuejie, Yao'an, Sanhe, Minri, Shide");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tiancai (good for praying for wealth, grand opening), Suihe, Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice)");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Dicai (good for praying for wealth, grand opening), Yima (good for starting a journey), Jintang");
			}
		} else if (nMonthAm == 5) {
			if (nCan == KCanBinh) {
				arrSao.add("<font color=black>Yuede, Tiangui");
			} else if (nCan == KCanTan) {
				arrSao.add("<font color=black>Tianfu, Yuedehe (avoid litigation)");
			} else if (nCan == KCanDinh)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanNham) {
				arrSao.add("<font color=black>Tianfu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tiancai (good for praying for wealth, grand opening), Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice)");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Dicai (good for praying for wealth, grand opening), Shengxin (good for everything, especially praying for Good Luck, sacrifice), Jiqing, Yinde, Jintang");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tiancheng, Youweixing, Xushi, Mushang (good for praying for wealth, grand opening), Yutang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tiandehe, Tianxi (good for everything, especially wedding), Tianma (good for starting a journey, transaction, praying for wealth), Yihou, Sanhe, Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Mingxing, Yao'an, Dahongsha");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Fuhou (good for praying for wealth, grand opening), Dahongsha");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianguan, Mandexing, Guanri");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Liuhe");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Luku (good for praying for wealth, grand opening, transaction), Yima (good for starting a journey), Qinglong");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Jingxin (good for burial rites), Suihe, Huoyao (bad if it meets Shousi), Minri, Shide, Huang'en, Mingtang");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Yuejie, Puhu (good for donation, wedding, starting a journey), Sanhe");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Fusheng, Wufu, Tiande");
			}
		} else if (nMonthAm == 6) {
			if (nCan == KCanGiap) {
				arrSao.add("<font color=black>Tiande, Yuede");
				if (nChi == KChiNgo) {
					arrSao.add("<font color=black>Tianshe (good for sacrifice, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
				}
			} else if (nCan == KCanKy) {
				arrSao.add("<font color=black>Tiandehe, Yuedehe (avoid litigation)");
			} else if (nCan == KCanBinh)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanDinh)
				arrSao.add("<font color=black>Tiangui");

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice)");
			} else if (nChi == KChiSuu) {
				arrSao.add("");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tiancai (good for praying for wealth, grand opening), Wufu, Huoyao (bad if it meets Shousi), Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Dicai (good for praying for wealth, grand opening), Jingxin (good for burial rites), Sanhe, Mushang (good for praying for wealth, grand opening), Huang'en, Jintang");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tianma (good for starting a journey, transaction, praying for wealth), Puhu(donation, wedding, starting a journey), Dahongsha");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Tiancheng, Fusheng, Yima (good for starting a journey), Fuhou (good for praying for wealth, grand opening), Dahongsha, Yutang");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Mingxing, Jiqing, Jingxin (good for burial rites), Liuhe");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Shengxin (good for everything, especially praying for Good Luck, sacrifice ceremonies)");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tianguan, Youweixing, Suihe, Yihou");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Luku (good for praying for wealth, grand opening, transaction), Xushi, Minri, Shide");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Yuejie, Yao'an, Qinglong");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Yinde, Mandexing, Sanhe, Mingtang");
			}
		} else if (nMonthAm == 7) {
			if (nCan == KCanQuy)
				arrSao.add("<font color=black>Tiande");
			else if (nCan == KCanAt)
				arrSao.add("<font color=black>Tianfu");
			else if (nCan == KCanBinh)
				arrSao.add("<font color=black>Yuekong");
			else if (nCan == KCanDinh)
				arrSao.add("<font color=black>Yuedehe");
			else if (nCan == KCanMau) {
				arrSao.add("<font color=black>Tiandehe");
				if (nChi == KChiThan) {
					arrSao.add("<font color=black>Tianshe (good for sacrifice ceremonies, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
				}
			} else if (nCan == KCanCanh || nCan == KCanTan)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanNham) {
				arrSao.add("<font color=black>Yuede, Yue'en");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Fusheng, Sanhe, Minri Shide, Huang'en, Qinglong");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Mushang (good for praying for wealth, grand opening), Mingtang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Shengxin (good for praying for Good Luck, sacrifice), Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice), Yima");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Jiqing, Yihou");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tianxi, Tiancai (good for praying for wealth, grand opening), Xushi, Sanhe, Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Dicai (good for praying for wealth, grand opening), Wufu, Youweixing, Yao'an, Liuhe, Jintang");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Shengqi (good for building houses, repairing houses, ground breaking, planting trees), Tianma (good for starting a journey, transaction, praying for wealth), Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Dahongsha");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tiancheng, Suihe, Dahongsha, Yutang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Mingxing, Mandexing, Fuhou (good for praying for wealth, grand opening)");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Yinde");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tianfu, Tianguan, Luku (good for praying for wealth, grand opening, transaction), Jingxin (good for burial rites)");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Yuejie, Huoyao, Puhu (good for donation, wedding, starting a journey)");
			}
			arrSao.clear();
		} else if (nMonthAm == 8) {
			if (nCan == KCanCanh) {
				arrSao.add("<font color=black>Yuede, Tiangui");
			} else if (nCan == KCanAt) {
				arrSao.add("<font color=black>Yuedehe (avoid litigation)");
			} else if (nCan == KCanTan)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanMau && nChi == KChiThan) {
				arrSao.add("<font color=black>Tianshe (good for sacrifice, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
			} else if (nCan == KCanGiap) {
				arrSao.add("<font color=black>Tianfu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianguan, Minri, Shide");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Mandexing, Sanhe, Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tiande, Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice), Qinglong");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Jiqing, Yihou, Mingtang");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Jingxin (good for burial rites), Huoyao (bad if it meets Shousi), Liuhe, Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiTy) {

				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Puhu (good for donation, wedding, starting a journey), Sanhe");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Fusheng, Tiancai (good for praying for wealth, grand opening), Suihe, Dahongsha, Huang'en");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Dicai (good for praying for wealth, grand opening), Yinde, Dahongsha, Jintang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tianma (good for starting a journey, transaction, praying for wealth), Shengxin (good for everything, especially praying for Good Luck, sacrifice), Wufu, Jiqing, Fuhou (good for praying for wealth, grand opening)");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tiancheng, Guanri, Yihou, Yutang");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Mingxing, Youweixing, Xushi");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tiandehe, Tianfu (good for everything, especially building houses, grand opening and burial rites), Luku (good for praying for wealth, grand opening, transaction), Yuejie, Yao'an, Yima (good for starting a journey), Tiandehe");

			}
		} else if (nMonthAm == 9) {

			if (nCan == KCanBinh) {
				arrSao.add("<font color=black>Tiande, Yuede");
			} else if (nCan == KCanTan) {
				arrSao.add("<font color=black>Tiandehe, Yuedehe (avoid litigation), Tiangui");
			} else if (nCan == KCanCanh)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanMau && nChi == KChiThan) {
				arrSao.add("<font color=black>Tianshe (good for sacrifice, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Mingxing, Luku (good for praying for wealth, grand opening, transaction), Puhu (good for donation, wedding, starting a journey), Minri, Shide");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Fusheng, Huoyao (bad if it meets Shousi), Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tianguan, Sanhe");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Shengxin (good for everything, especially praying for Good Luck, sacrifice), Liuhe");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice), Yihou, Mushang (good for praying for wealth, grand opening), Qinglong");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Jiqing, Yinde, Suihe, Xushi, Mingtang");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Yuejie, Yao'an,Sanhe, Dahongsha");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Youweixing, Dahongsha");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Tiancai (good for praying for wealth, grand opening), Yima (good for starting a journey), Fuhou (good for praying for wealth, grand opening)");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Dicai (good for praying for wealth, grand opening), Jintang");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tianma (good for starting a journey, transaction, praying for wealth), Mandexing");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tiancheng, Wufu, Jingxin (good for burial rites), Huang'en, Yutang");
			}
		} else if (nMonthAm == 10) {
			if (nCan == KCanAt) {
				arrSao.add("<font color=black>Tiande");
			} else if (nCan == KCanCanh) {
				arrSao.add("<font color=black>Tiandehe");
			} else if (nCan == KCanGiap) {
				arrSao.add("<font color=black>Yuede");
				if (nChi == KChiTi) {
					arrSao.add("<font color=black>Tianshe (good for sacrifice, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
				}
			} else if (nCan == KCanKy) {
				arrSao.add("<font color=black>Yuedehe (avoid litigation)");
			} else if (nCan == KCanNham || nCan == KCanQuy)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanDinh) {
				arrSao.add("<font color=black>Tianfu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianma (good for starting a journey, transaction, praying for wealth), Youweixing, Puhu (good for donation, wedding, starting a journey), Yao'an");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Tiancheng, Luku (good for praying for wealth, grand opening, transaction), Yutang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Mingxing, Wufu, Liuhe");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Yinde, Mandexing, Sanhe, Minri, Shide");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tianguan, Suihe, Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice), Huang'en");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Jingxin (good for burial rites), Yima (good for starting a journey)");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Yuejie, Huoyao (bad if it meets Shousi), Qinglong");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Fusheng, Sanhe, Mingtang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Mushang (good for praying for wealth, grand opening), Dahongsha");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Shengxin (good for everything, especially praying for Good Luck, sacrifice ceremonies), Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tiancai (good for praying for wealth, grand opening), Jiqing, Yihou, Dahongsha");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Dicai (good for praying for wealth, grand opening), Xushi, Fuhou (good for praying for wealth, grand opening), Jintang");
			}
		} else if (nMonthAm == 11) {
			if (nCan == KCanNham) {
				arrSao.add("<font color=black>Yuede, Tiangui");
			} else if (nCan == KCanDinh) {
				arrSao.add("<font color=black>Yuedehe (avoid litigation)");
			} else if (nCan == KCanQuy)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanGiap && nChi == KChiTi) {
				arrSao.add("<font color=black>Tianshe (good for sacrifice, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
			} else if (nCan == KCanBinh) {
				arrSao.add("<font color=black>Tianfu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tiancai (good for praying for wealth, grand opening), Mandexing, Jingxin(good for burial rites), Guanri");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Dicai (good for praying for wealth, grand opening), Yinde, Puhu(donation, wedding, starting a journey), Liuhe, Jintang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tianfu(good for everything, especially building houses, grand opening and burial rites), Tianma (good for starting a journey, transaction, praying for wealth), Luku (good for praying for wealth, grand opening, transaction), Fusheng, Yima (good for starting a journey)");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tiancheng, Suihe, Huoyao (bad if it meets Shousi), Minri, Shide, Yutang");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Mingxing, Shengxin (good for everything, especially praying for Good Luck, sacrifice), Sanhe");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Tiande, Wufu, Yihou, Yutang");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianguan, Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice), Xushi");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Jiqing, Yuejie, Yao'an");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tiandehe, Tianxi (good for everything, especially wedding), Sanhe, Mushang (good for praying for wealth, grand opening), Dahongsha, Huang'en, Qinglong");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Youweixing, Mushang (good for praying for wealth, grand opening), Mingtang");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Dahongsha");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Fuhou (good for praying for wealth, grand opening)");
			}
		} else if (nMonthAm == 12) {
			if (nCan == KCanCanh) {
				arrSao.add("<font color=black>Tiande, Yuede");
			} else if (nCan == KCanAt) {
				arrSao.add("<font color=black>Tiandehe, Yuedehe (avoid litigation)");
			} else if (nCan == KCanNham || nCan == KCanQuy)
				arrSao.add("<font color=black>Tiangui");
			else if (nCan == KCanGiap && nChi == KChiTi) {
				arrSao.add("<font color=black>Tianshe (good for sacrifice, requiem ceremonies to untie the knots of injustice, can expel unlucky stars, only avoid ground breaking. If the zhiwei is Kai, which means Tianshe meets Shengqi, it will be a very good day)");
			}
			//
			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Jiqing,Xushi, Liuhe");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Yao'an");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tiancai (good for praying for wealth, grand opening), Youweixing, Suihe");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tianfu (good for everything, especially building houses, grand opening and burial rites), Dicai (good for praying for wealth, grand opening), Luku (good for praying for wealth, grand opening, transaction), Minri, Shide, Jintang");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tianma (good for starting a journey, transaction, praying for wealth)");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Tiancheng, Mandexing, Sanhe");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Mingxing, Jingxin (good for burial rites), Jieshen (good for sacrifice, litigation, requiem ceremonies to untie the knots of injustice)");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Yuejie, Puhu (good for donation, wedding, starting a journey), Huang'en");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Fusheng, Tianguan, Wufu, Huoyao (bad if it meets Shousi), Mushang (good for praying for wealth, grand opening), Dahongsha");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianxi (good for everything, especially wedding), Sanhe, Mushang (good for praying for wealth, grand opening)");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Shengxin (good for everything, especially praying for Good Luck, sacrifice), Dahongsha, Qinglong");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Shengqi (good for everything, especially building houses, repairing houses, ground breaking, planting trees), Yuecai (good for praying for wealth, grand opening, starting a journey, moving, transaction), Yinde, Yihou, Yima (good for starting a journey), Fuhou (good for praying for wealth, grand opening), Mingtang");
			}
		}

		for (int j = 0; j < arrSao.size(); j++) {
			sOut += arrSao.get(j);
			if (j != arrSao.size() - 1)
				sOut += ", ";
		}
		arrSao.clear();
		// Lay cac sao xau
		sOut += "<br><strong><font color = black>Unlucky Star:</font></strong>";

		if (nMonthAm == 1) {
			if (nCan == KCanGiap) {
				arrSao.add("<font color=black>Chongsang");
				if (nChi == KChiDan)
					arrSao.add("<font color=black>Yangtuo");
			} else if (nCan == KCanAt) {
				if (nChi == KChiMao) {
					arrSao.add("<font color=black>Tiandizhuansha");
				} else if (nChi == KChiMui) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanCanh) {
				arrSao.add("<font color=black>Chongfu");
				if (nChi == KChiTuat)
					arrSao.add("<font color=black>Yintuo");
			} else if (nCan == KCanQuy && nChi == KChiMao) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Feimosha, Lubansha");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Huangwu, Dizei, Huozai, Yuexu (Yuesha), Sishigugua");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tufu, Liubucheng, Wangwang (Tuji), Leigong");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Zhuqueheidao, Yuejianzhuansha");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Tianzei, Jiukong, Guoxiu, Futou, Sansang, Kongfang");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Tiangang , Xiaohongsha, Xiaohao, Huangwu, Yuehuo, Duhuo, Shenge, Bingxiaowaxian, Shazhu, Yuexing, Wuxu");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu), Huangsha, Wugui, Baihu , Zuizhi");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tianwen");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Yuepo");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianli, Huangwu, Renge, Xuanwu, Lichao");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Shousi, Yueyandahuo, Gushen, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Jiesha, Dipo, Hekui, Goujiao, Gouzhen, Tujin");
			}
		} else if (nMonthAm == 2) {
			if (nCan == KCanAt) {
				arrSao.add("<font color=black>Chongsang");
				if (nChi == KChiMao) {
					arrSao.add("<font color=black>Tiandizhuansha, Yangtuo");
				} else if (nChi == KChiMui) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanTan) {
				arrSao.add("<font color=black>Chongfu");
				if (nChi == KChiDau)
					arrSao.add("<font color=black>Yintuo");
			} else if (nCan == KCanQuy && nChi == KChiMao) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tiangang , Dipo, Dizei, Bingxiaowaxian, Shazhu, Yuexing, Zuizhi, Lubansha");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Huangwu, Jiukong, Sishigugua");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Huangsha, Wugui");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Tufu, Shenge, Yuejianzhuansha");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Shousi, Yuehuo, Duhuo, Futou, Sansang");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Huangwu, Wangwang (Tuji), Zhuqueheidao, Gouzhen, Guoxiu, Kongfang");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianli, Xiaohao, Liubucheng, Hekui, Goujiao");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu), Huozai, Renge");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Jiesha, Baihu");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Xiaohongsha, Yuepo, Huangwu, Tianzei, Yueyandahuo, Feimosha (Zaisha), Wuxu, Lichao");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Yuepo, Tianwen, Yuexu (Yuesha), Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Xuanwu, Leigong, Gushen, Tujin");
			}
		} else if (nMonthAm == 3) {
			if (nCan == KCanKy) {
				arrSao.add("<font color=black>Chongsang, Chongfu");
			} else if (nCan == KCanAt) {
				if (nChi == KChiMao) {
					arrSao.add("<font color=black>Tiandizhuansha");
				} else if (nChi == KChiMui) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanCanh && nChi == KChiThan) {
				arrSao.add("<font color=black>Yintuo");
			} else if (nCan == KCanQuy && nChi == KChiMao) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			} else if (nCan == KCanGiap && nChi == KChiThin) {
				arrSao.add("<font color=black>Yangtuo");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Huangsha, Gushen, Lubansha, Kongfang");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Xiaohongsha, Dipo, Huangwu, Shenge, Bingxiaowaxian, Hekui, Goujiao, Wuxu, Sishigugua");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tianzei, Huozai");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tianli, Yuehuo, Duhuo, Gouzhen, Yuejianzhuansha");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tufu, Tianwen, Wugui, Yuexing, Futou, Sansang");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Jiesha, Huangwu, Renge, Xuanwu, Leigong");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Tuwen (Tiangou), Feimosha (Zaisha), Guoxiu");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tiangang , Xiaohao, Yuexu (Yuesha), Zhuqueheidao, Shazhu, Zuizhi");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu), Yueyandahuo, Wangwang (Tuji)");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Huangwu, Lichao");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Yuepo, Liubucheng, Jiukong, Baihu, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Shousi, Dizei, Tujin");
			}
		} else if (nMonthAm == 4) {
			if (nCan == KCanBinh) {
				arrSao.add("<font color=black>Chongsang");
				if (nChi == KChiNgo) {
					arrSao.add("<font color=black>Tiandizhengzhuan, Tiandizhuansha");
				} else if (nChi == KChiTuat) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanDinh) {
				if (nChi == KChiMui)
					arrSao.add("<font color=black>Yintuo");
				else if (nChi == KChiTy) {
					arrSao.add("<font color=black>Yangtuo");
				}
			} else if (nCan == KCanNham) {
				arrSao.add("<font color=black>Chongfu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianli, Huangwu, Baihu");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Gouzhen, Gushen, Zuizhi");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tiangang , Jiesha, Dipo, Tianwen, Yuehuo, Duhuo, Bingxiaowaxian, Tujin, Lichao");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Renge, Feimosha (Zaisha), Xuanwu, Shazhu, Lubansha");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Huangwu, Yuexu (Yuesha), Sishigugua");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Xiaohongsha, Tufu, Shousi, Liubucheng");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Huangsha, Yuejianzhuansha, Lichao");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Tianzei, Yueyandahuo, Jiukong, Guoxiu, Futou, Sansang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Xiaohao, Huangwu, Huozai, Hekui, Goujiao, Leigong, Yuexing, Wuxu");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Dahao (Siqi, Guanfu), Wugui, Zhuqueheidao");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Dizei, Kongfang, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Yuepo, Shenge, Wangwang (Tuji)");
			}
		} else if (nMonthAm == 5) {
			if (nCan == KCanBinh) {
				if (nChi == KChiNgo) {
					arrSao.add("<font color=black>Tiandizhengzhuan, Tiandizhuansha, Yintuo, Yangtuo");
				} else if (nChi == KChiTuat) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanDinh) {
				arrSao.add("<font color=black>Chongsang");
			} else if (nCan == KCanQuy) {
				arrSao.add("<font color=black>Chongfu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Yuepo, Shousi, Huangwu, Tianzei, Feimosha (Zaisha), Wuxu");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Yuepo, Yuehuo Duhuo, Yuexu (Yuesha), Renge, Xuanwu");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Huangsha, Baihu, Leigong, Gushen, Tujin, Lichao");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Dipo, Huozai, Wugui, Bingxiaowaxian, Hekui Goujiao, Wangwang (Tuji), Jiukong, Lubansha");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Hoang vu, Tứ thời cô quả");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Huangwu, Sishigugua");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tufu, Tianwen, Yueyandahuo, Yuexing, Yuejianzhuansha, Lichao");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Gouzhen, Futou, Sansang");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Huangwu, Guoxiu, Shazhu, Zuizhi");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tiangang , Tianli, Xiaohongsha, Xiaohao, Dizei, Liubucheng, Shenge");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu), Guiku");
			}
		} else if (nMonthAm == 6) {
			if (nCan == KCanBinh) {
				//
				if (nChi == KChiNgo) {
					arrSao.add("<font color=black>Tiandizhengzhuan, Tiandizhuansha");
				} else if (nChi == KChiTuat) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanDinh) {
				if (nChi == KChiTy)
					arrSao.add("<font color=black>Yintuo");
				else if (nChi == KChiMui) {
					arrSao.add("<font color=black>Yangtuo");
				}
			} else if (nCan == KCanMau) {
				arrSao.add("<font color=black>Chongfu");
			} else if (nCan == KCanKy) {
				arrSao.add("<font color=black>Chongsang");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianwen, Huangwu, Yuehuo , Duhuo, Huangsha, Jiukong");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Xiaohongsha, Yuepo, Liubucheng, Zhuqueheidao, Yuexing");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Zuizhi, Tujin, Lichao");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Gushen, Lubansha");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Tiangang , Dipo, Huangwu, Baihu (if that day is Tianjie, it'll be a lucky star), Wuxu, Sishigugua");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Tianzei, Yueyandahuo");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianli, Shousi, Wangwang (Tuji), Yuejianzhuansha, Lichao");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Tufu, Shenge, Xuanwu, Futou, Sansang, Kongfang");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Jiesha, Huangwu, Dizei, Wugui");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Huozai, Feimosha (Zaisha), Gouzhen, Guoxiu");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Xiaohao, Yuexu (Yuesha), Bingxiaowaxian, Hekui, Goujiao, Shazhu, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu), Renge, Leigong");
			}
		} else if (nMonthAm == 7) {
			if (nCan == KCanCanh) {
				arrSao.add("<font color=black>Chongsang");
				if (nChi == KChiThan)
					arrSao.add("<font color=black>Yangtuo");
			} else if (nCan == KCanGiap) {
				arrSao.add("<font color=black>Chongfu");
				if (nChi == KChiThin)
					arrSao.add("<font color=black>Yintuo");
			} else if (nCan == KCanTan) {
				if (nChi == KChiDau)
					arrSao.add("<font color=black>Tiandizhuansha");
				else if (nChi == KChiSuu)
					arrSao.add("<font color=black>Sishidamu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu)");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Shousi, Wugui, Shazhu");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Yuepo, Yuexing, Kongfang");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Huangwu, Zhuqueheidao");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Huozai, Yueyandahuo, Gushen");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Xiaohongsha, Jiesha, Dipo, Shenge, Hekui Goujiao, Leigong, Tujin");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Huangsha, Feimosha (Zaisha), Baihu, Lubansha");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Huangwu, Dizei, Yuexu (Yuesha), Sishigugua");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tufu, Liubucheng");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianwen, Renge, Wangwang (Tuji), Jiukong, Xuanwu, Zuizhi, Yuejianzhuansha, Futou");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Tianzei, Guoxiu, Sansang, Lichao, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tiangang , Xiaohao, Huangwu, Yuehuo, Duhuo, Bingxiaowaxian, Gouzhen, Wuxu");
			}
		} else if (nMonthAm == 8) {
			if (nCan == KCanAt) {
				arrSao.add("<font color=black>Chongfu");
				//
				if (nChi == KChiMao) {
					arrSao.add("<font color=black>Yintuo");
				}
			} else if (nCan == KCanTan) {
				arrSao.add("<font color=black>Chongsang");
				if (nChi == KChiDau)
					arrSao.add("<font color=black>Tiandizhuansha, Yangtuo");
				else if (nChi == KChiSuu)
					arrSao.add("<font color=black>Sishidamu");
			} else if (nCan == KCanDinh && nChi == KChiDau) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianli, Xiaohao, Liubucheng, Hekui, Goujiao, Wangwang (Tuji)");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu)");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Jiesha, Huangsha");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Yuepo, Huangwu, Tianzei, Yueyandahuo, Shenge, Feimosha (Zaisha), Zuizhi, Wuxu, Kongfang");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Yuepo, Yuexu (Yuesha)");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Wugui, Zhuqueheidao, Gouzhen, Gushen, Tujin");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tiangang , Dipo, Dizei, Bingxiaowaxian, Jiukong, Lubansha");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Shousi, Huangwu, Renge, Sishigugua");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tianwen, Baihu, Leigong");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Xiaohongsha, Tufu, Yuexing, Yuejianzhuansha, Futou");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Huozai, Yuehuo, Duhuo, Sansang, Lichao, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Huangwu, Xuanwu, Guoxiu, Shazhu");
			}
		} else if (nMonthAm == 9) {
			if (nCan == KCanGiap && nChi == KChiDan) {
				arrSao.add("<font color=black>Yintuo");
			} else if (nCan == KCanDinh && nChi == KChiDau) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			} else if (nCan == KCanTan) {
				// arrSao.push_back("Trùng phục");
				if (nChi == KChiDau)
					arrSao.add("<font color=black>Tiandizhuansha");
				else if (nChi == KChiSuu)
					arrSao.add("<font color=black>Sishidamu");
			} else if (nCan == KCanCanh && nChi == KChiTuat) {
				arrSao.add("<font color=black>Yangtuo");
			} else if (nCan == KCanKy) {
				arrSao.add("<font color=black>Chongfu, Chongsang");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Tuwen (Tiangou), Huangsha, Feimosha (Zaisha), Wugui, Guoxiu");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Tiangang , Xiaohongsha, Xiaohao, Yuexu (Yuesha), Shenge");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu), Shousi, Yueyandahuo, Jiukong, Leigong");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Huangwu, Gouzhen");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Yuepo, Liubucheng, Wangwang (Tuji)");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Tianwen, Dizei, Huozai, Renge, Xuanwu, Tujin");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Gushen, Shazhu, Lubansha, Kongfang");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Dipo, Huangwu, Bingxiaowaxian, Hekui, Goujiao, Zhuqueheidao, Yuexing, Wuxu, Sishigugua");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tianzei");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianli, Yuehuo, Duhuo, Yuejianzhuansha, Futou");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tufu, Baihu, Zuizhi, Sansang, Lichao, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Jiesha, Huangwu");
			}
		} else if (nMonthAm == 10) {
			if (nCan == KCanCanh && nChi == KChiTi) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			} else if (nCan == KCanNham) {
				arrSao.add("<font color=black>Chongsang, Chongfu");
				if (nChi == KChiTi)
					arrSao.add("<font color=black>Tiandizhuansha");
				else if (nChi == KChiThin) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanQuy) {
				if (nChi == KChiSuu)
					arrSao.add("<font color=black>Yintuo");
				else if (nChi == KChiHoi)
					arrSao.add("<font color=black>Yangtuo");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Baihu, Yuejianzhuansha, Futou");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Tianzei, Yueyandahuo, Gouzhen, Guoxiu, Sansang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Xiaohao, Huangwu, Hekui, Goujiao, Wuxu");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Dahao (Siqi, Guanfu), Renge, Xuanwu");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Dizei, Zuizhi");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Xiaohongsha, Yuepo, Lichao");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianli, Huangwu, Huangsha");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Wangwang (Tuji), Gushen");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Tiangang , Jiesha, Dipo, Shousi, Yuehuo, Duhuo, Bingxiaowaxian, Tujin, Kongfang");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Feimosha (Zaisha), Zhuqueheidao, Shazhu, Lubansha");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Huangwu, Yuexu (Yuesha), Sishigugua, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tufu, Tianwen, Huozai, Liubucheng, Shenge, Wugui, Jiukong, Leigong, Yuexing");
			}
		} else if (nMonthAm == 11) {
			if (nCan == KCanCanh && nChi == KChiTi) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			} else if (nCan == KCanNham) {
				//
				if (nChi == KChiTi)
					arrSao.add("<font color=black>Tiandizhuansha, Yintuo, Yangtuo");
				else if (nChi == KChiThin) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanQuy) {
				arrSao.add("<font color=black>Chongsang, Chongfu");
			}

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tufu, Tianwen, Yueyandahuo, Yuejianzhuansha, Futou");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Renge, Xuanwu, Sansang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Huangwu, Huangsha, Baihu , Guoxiu, Shazhu");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tiangang , Tianli, Xiaohao, Shousi, Dizei, Liubucheng, Yuexing");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu)");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Jiesha, Leigong, Lichao");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Yuepo, Huangwu, Tianzei, Huozai, Feimosha (Zaisha), Wuxu");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Yuepo, Yuehuo, Duhuo, Yuexu (Yuesha), Wugui, Gouzhen");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Jiukong, Gushen, Tujin");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Xiaohongsha, Dipo, Shenge, Bingxiaowaxian, Hekui, Goujiao, Lubansha, Kongfang");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Huangwu, Wangwang (Tuji), Sishigugua, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Zhuqueheidao, Zuizhi");
			}
		} else if (nMonthAm == 12) {
			if (nCan == KCanCanh && nChi == KChiTi) {
				arrSao.add("<font color=black>Tiandizhengzhuan");
			} else if (nCan == KCanNham) {
				//
				if (nChi == KChiTi)
					arrSao.add("<font color=black>Tiandizhuanshat");
				else if (nChi == KChiThin) {
					arrSao.add("<font color=black>Sishidamu");
				}
			} else if (nCan == KCanQuy) {
				if (nChi == KChiHoi)
					arrSao.add("<font color=black>Yintuo");
				else if (nChi == KChiSuu)
					arrSao.add("<font color=black>Yangtuo");
			} else if (nCan == KCanKy)
				arrSao.add("<font color=black>Chongsang, Chongfu");

			if (nChi == KChiTi) {
				arrSao.add("<font color=black>Tianli, Huozai, Huangsha, Yuejianzhuansha, Futou");
			} else if (nChi == KChiSuu) {
				arrSao.add("<font color=black>Xiaohongsha, Tufu, Wangwang (Tuji), Zhuqueheidao, Sansang, Kongfang");
			} else if (nChi == KChiDan) {
				arrSao.add("<font color=black>Jiesha, Huangwu, Dizei");
			} else if (nChi == KChiMao) {
				arrSao.add("<font color=black>Tuwen (Tiangou), Tianwen, Feimosha (Zaisha), Guoxiu");
			} else if (nChi == KChiThin) {
				arrSao.add("<font color=black>Xiaohao, Yuexu (Yuesha), Bingxiaowaxian, Hekui, Goujiao, Baihu");
			} else if (nChi == KChiTy) {
				arrSao.add("<font color=black>Dahao (Siqi, Guanfu), Jiukong, Zuizhi, Lichao");
			} else if (nChi == KChiNgo) {
				arrSao.add("<font color=black>Huangwu, Yuehuo, Duhuo");
			} else if (nChi == KChiMui) {
				arrSao.add("<font color=black>Yuepo, Liubucheng, Shenge, Xuanwu");
			} else if (nChi == KChiThan) {
				arrSao.add("<font color=black>Leigong, Tujin");
			} else if (nChi == KChiDau) {
				arrSao.add("<font color=black>Tianyu, Tianhuo, Shousi, Gouzhen, Gushen, Lubansha");
			} else if (nChi == KChiTuat) {
				arrSao.add("<font color=black>Tiangang , Dipo, Huangwu, Wugui, Yuexing, Wuxu, Sishigugua, Guiku");
			} else if (nChi == KChiHoi) {
				arrSao.add("<font color=black>Tianzei, Yueyandahuo, Renge");
			}
		}
		for (int j = 0; j < arrSao.size(); j++) {
			sOut += arrSao.get(j);
			if (j != arrSao.size() - 1)
				sOut += ", ";
		}

		return sOut;
	}
}
