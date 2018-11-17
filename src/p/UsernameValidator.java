package p;

import java.util.regex.Pattern;

public class UsernameValidator {
	private Pattern pattern;
    private static final String USERNAME_PATTERN = "^[a-z0-9]{3,15}$";

    public UsernameValidator() {
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    public boolean validate(final String username) {
        return pattern.matcher(username).matches();
    }

	
            
           
}
