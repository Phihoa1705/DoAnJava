package Models;

import java.util.Objects;

public class UserLogin_Models {
        private String userName;
        private String passWord;
        private String fullName;

        public UserLogin_Models(String userName, String passWord) {
                this.userName = userName;
                this.passWord = passWord;
        }

        public UserLogin_Models() {

        }

        public UserLogin_Models(String userName, String passWord, String fullName) {
                this.userName = userName;
                this.passWord = passWord;
                this.fullName = fullName;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassWord() {
                return passWord;
        }

        public void setPassWord(String passWord) {
                this.passWord = passWord;
        }

        public String getFullName() {
                return fullName;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        @Override
        public String toString() {
                return "UserLogin{" +
                        "userName='" + userName + '\'' +
                        ", passWord='" + passWord + '\'' +
                        ", fullName='" + fullName + '\'' +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                UserLogin_Models userlogin = (UserLogin_Models) o;
                return Objects.equals(userName, userlogin.userName) && Objects.equals(passWord, userlogin.passWord);
        }

        @Override
        public int hashCode() {
                return Objects.hash(userName, passWord, fullName);
        }
}
