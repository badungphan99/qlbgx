package p;

import da.ConnectionUtil;
import da.UserDA;
import da.VehicleTypePriceDA;
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
	public static void main(String[] args) {
		FirstFrame firstFrame = new FirstFrame();
		firstFrame.setVisible(true);

	}
//	public static void main(String[] args) throws Exception{
//		VehicleTypePriceDA vehicleTypePriceDA = new VehicleTypePriceDA();
//		vehicleTypePriceDA.insertVehicleSetting(1,771,1,1,4,5);
//	}



//	public static boolean validate(final String username) {
//		return Pattern.compile("^[a-z09]{3,15}$").matcher(username).matches();
//	}
//
//	public static void main(String[] args) {
//		//Scanner scan = new Scanner(System.in);
//		//String s= scan.next();
//		//true
//		//System.out.println(runner.validate(s));
//
//		System.out.println(validate("abc "));
//	}






}
