package b;

import e.User;

public class loginSession{
    private static User user ;

    public static void setUser(User user) {
        loginSession.user = user;
    }
    public static User getUser(){
        return user;
    }
}
