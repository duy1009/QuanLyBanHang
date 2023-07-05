import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
    private String hostName = "localhost:3306";
    private String dbName = "banhangonline";
    private String username = "nhom3";
    private String password = "123456";

    private String connectionURL = "jdbc:mysql://"+hostName+"/"+dbName;

    public Connection connect(){

        Connection conn = null;

        try {

            System.out.println(connectionURL);
            conn = DriverManager.getConnection(connectionURL, username, password);
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/banhangonline","root", "");
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}