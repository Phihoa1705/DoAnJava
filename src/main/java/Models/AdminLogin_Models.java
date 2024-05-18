package Models;

import java.util.Objects;

public class AdminLogin_Models {
    private String adminName;
    private String adminPass;
    private String fullName;

    public AdminLogin_Models(String adminName, String adminPass) {
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    public AdminLogin_Models(String adminName, String adminPass, String fullName) {
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.fullName = fullName;
    }

    public AdminLogin_Models() {
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "AdminLogin{" +
                "adminName='" + adminName + '\'' +
                ", adminPass='" + adminPass + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminLogin_Models that = (AdminLogin_Models) o;
        return Objects.equals(adminName, that.adminName) && Objects.equals(adminPass, that.adminPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminName, adminPass, fullName);
    }
}
