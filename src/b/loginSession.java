package b;
// class này giống như 1 token để lưu trữ thông tin răng có người đang đăng nhập và biết đấy là ai
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
