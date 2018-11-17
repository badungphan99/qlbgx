package p;

import da.ConnectionUtil;
import da.UserDA;
import e.Infor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import b.loginSession;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Runner {
//	public static void main(String[] args) {
//		FirstFrame firstFrame = new FirstFrame();
//		firstFrame.setVisible(true);
//
//	}
//public static void main(String[] args) throws Exception{
//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//	Date date = new Date();
//	String datetime = dateFormat.format(date);
//	System.out.println(datetime);
//	}
private Pattern pattern;
	private static final String USERNAME_PATTERN = "^[az09]{3,15}$";

	public Runner() {
		pattern = Pattern.compile(USERNAME_PATTERN);
	}

	public boolean validate(final String username) {
		return pattern.matcher(username).matches();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s= scan.next();
		Runner validator = new Runner();
		//true
		System.out.println(validator.validate(s));

		//System.out.println(validator.validate("abdulrahmansherzad"));
	}


}
