package cn.com.payment.v2.web.utils;

public class MathUtils {

	public static boolean isOdd(int a) {
		if ((a & 1) != 1) { // 是偶数
			return true;
		}
		return false;
	}
}
