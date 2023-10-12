package Utils;

import java.text.DecimalFormat;

public class RoundDouble {
	public static Double roundDouble(Double number) {
		// 创建 DecimalFormat 对象，指定小数位数为两位
		DecimalFormat decimalFormat = new DecimalFormat("#.##");

		// 使用 DecimalFormat 对象格式化 double 值
		String roundedNumber = decimalFormat.format(number);

		// 将格式化后的字符串转换回 double
		double roundedValue = Double.parseDouble(roundedNumber);
		return roundedValue;
	}

}
