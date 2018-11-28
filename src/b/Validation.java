package b;

import java.util.regex.Pattern;

public class Validation {
	public boolean isValidateInt(final String txtInt) {
		return Pattern.compile("^[0-9]{1,15}$").matcher(txtInt).matches();
	}
}
