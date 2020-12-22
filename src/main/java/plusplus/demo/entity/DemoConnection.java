package plusplus.demo.entity;

import java.sql.Connection;
import java.sql.DriverManager;

public class DemoConnection {
    private Connection connection;

    private DemoConnection() {
    }

    public static DemoConnection getInstance() {
        return DemoConnectionHelper.demoConnection;
    }

    private static class DemoConnectionHelper {
        private static final DemoConnection demoConnection = new DemoConnection();
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/springdemodb", "root", "hoangnt1@");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;

    }
}
