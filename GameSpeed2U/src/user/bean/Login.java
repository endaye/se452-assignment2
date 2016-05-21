package user.bean;

public class Login {
    private String username = "";
    private String password = "";
    private String usertype = "";
    private boolean error = false;
    private String loginMsg = "";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getLoginMsg() {
        return loginMsg;
    }

    public void setLoginMsg(String loginMsg) {
        this.loginMsg = loginMsg;
    }
}
