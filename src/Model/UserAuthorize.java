package Model;

public class UserAuthorize {
    private String loginOrEmail;

    private String password;

    public UserAuthorize(String loginOrEmail, String password) {
        this.loginOrEmail = loginOrEmail;
        this.password = password;
    }

    public String getLoginOrEmail() {
        return loginOrEmail;
    }

    public void setLoginOrEmail(String loginOrEmail) {
        this.loginOrEmail = loginOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
