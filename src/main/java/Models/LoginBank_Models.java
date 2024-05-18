package Models;

public class LoginBank_Models {
    private String username;
    private String password;
    private String fullName;
    private String role;

    public LoginBank_Models() {
        this.username = "";
        this.password = "";
        this.fullName = "";
        this.role = "";
    }

    public LoginBank_Models(String username, String password, String fullName, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    // Getters và setters cho các thuộc tính

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
