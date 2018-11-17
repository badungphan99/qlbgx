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
//		vehicleTypePriceDA.insertVehicleSetting(2,7771,1,5,4,5);
//	}

}
