package Test;

import Database.JDBCUtil;

import java.sql.Connection;

public class Test1 {
    public static void main(String[] args) {
        Connection connection = JDBCUtil.getConnection();

        JDBCUtil.printInfo(connection);

        JDBCUtil.closeConnection(connection);
    }
}
