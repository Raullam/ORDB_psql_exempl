package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDades {

    private static final String URL = "jdbc:postgresql://localhost/Institut";
    private static Connection connection;

    public static Connection getConnection() {
        try (FileInputStream arxiu = new FileInputStream("src/util/conexio.conf")){

            Properties propietats = new Properties();
            propietats.load(arxiu);
            connection = DriverManager.getConnection(URL,propietats);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);

        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return connection;
    }

}
