package b;

import java.util.regex.Pattern;

public class Validation {
	public boolean isValidateParkingLot(final String parkingLot) {
		return Pattern.compile("^[0-9]{1,15}$").matcher(parkingLot).matches();
	}
}
