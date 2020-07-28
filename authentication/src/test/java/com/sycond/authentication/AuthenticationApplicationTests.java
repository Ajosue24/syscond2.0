package com.sycond.authentication;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


class AuthenticationApplicationTests {

	@Test
	void contextLoads() {
	    String stringVar = "10.00011";
	    BigDecimal bdVar = BigDecimal.valueOf(10.0001);

        if (0 != new BigDecimal(stringVar)
                .compareTo(bdVar)) {
           System.out.println("Invalid amount request ".concat(new BigDecimal(stringVar).toString()).concat(" vs response ").concat(bdVar.toString()));
    }}

        @Test
	void anotherTest() {
	   String stringAlgo="5";
	  Short v= Short.valueOf(stringAlgo);
	   v.byteValue();

    }

	@Test
    void testUtils(){
		//TestClass tc = new TestClass("arn:aws:sns:us-east-1:345938303531:SNS-Migracion-Dev");
		//String[] something = fromStringReturnArray(tc.getAlgo()).orElse(null);
		//something.toString();
	}

	@Test
	void testUtils2() {
        TestClass obj = new TestClass();
        obj.setId(1L);
        obj.setAlgo("asd");
        List<TestClass> testClassList = new ArrayList<>(Arrays.asList(new TestClass() {{
            setId(1L);
            setAlgo("1");
        }}, new TestClass() {{
            setId(2L);
            setAlgo("2");
        }}, new TestClass() {{
            setId(3L);
            setAlgo("3");
        }}));

        Map<Long, String> stringObj = testClassList.stream().collect(Collectors.toMap(TestClass::getId, TestClass::getAlgo));
        List<TestClass2> stringObj2 = testClassList.stream().map(temp->{
            TestClass2 tc2 = new TestClass2();
            tc2.setId(temp.getId());
            tc2.setAlgo(temp.getAlgo());
            return tc2;
        }).collect(Collectors.toList());

        stringObj2.size();


    }


	/*public static String fecha() {
		LocalDateTime date = LocalDateTime.now();
		return date.getYear() + ""
				+ (date.getMonth().getValue() < 10 ? "0" : "" + date.getMonth().getValue()) + ""
				+ (date.getDayOfMonth() < 10 ? "0" : "" + date.getDayOfMonth());
	}


	public static boolean isEmptyOrNull(String value) {
		return value == null || value.trim().isEmpty();
	}

	public static Optional<String[]> fromStringReturnArray(String element){
		if(!isEmptyOrNull(element)){
			return Optional.ofNullable(element.split(","));
		}
		return Optional.empty();
	}

	public static String getFormattedDate(String dateWithoutFormat) {
		SimpleDateFormat inputFormat = new SimpleDateFormat(DateFormat.YYYYMMDD.format);
		SimpleDateFormat outputFormat = new SimpleDateFormat(DateFormat.DDSLASHMMSLASHYYYY.format);

		try {
			Date date = inputFormat.parse(dateWithoutFormat);
			return outputFormat.format(date);
		} catch (java.text.ParseException e) {
			"".isEmpty();
		}

		return null;
	}

	public static String getFormattedHour(String hourWithoutFormat) {
		SimpleDateFormat inputFormat = new SimpleDateFormat(DateFormat.HHMMSS.format);
		SimpleDateFormat outputFormat = new SimpleDateFormat(DateFormat.HHMM.format);

		try {
			Date date = inputFormat.parse(hourWithoutFormat);
			return outputFormat.format(date);
		} catch (java.text.ParseException e) {
			"".isEmpty();
		}

		return null;
	}

	public enum DateFormat {
		YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
		YYYYMMDDHHMMSSSS("yyyyMMddHHmmssSS"),
		DDSLASHMMSLASHYYYY("dd/MM/yyyy"),
		YYYMMDD("yyyMMdd"),
		MMSLASHYY("MM/yy"),
		YYYYMM("yyyyMM"),
		HHMMSS("HHmmss"),
		HHMM("HH:mm"),
		YYYYMMDD("yyyyMMdd"),
		DAVIPLATAFORMAT("dd/MM/yyyy - HH:mm"),
		DDSLASHMMSLASHYY("dd/MM/yy"),
		YYYYSLASHMMSLASHDD("yyyy/MM/dd"),
		YYMMDD("yyMMdd"),
		DDMMYYYY("ddMMyyyy");

		private final String format;

		private DateFormat(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}
	}
*/


}
