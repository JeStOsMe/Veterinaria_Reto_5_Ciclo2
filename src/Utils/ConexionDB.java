package Utils;

/**
 *
 * @author Jeaniel Osorno
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//Para trabajar con JSON
//Enlace descarga JSON Simple - https://code.google.com/archive/p/json-simple/downloads
import org.json.simple.*;
import org.json.simple.parser.*;

public class ConexionDB {
    //Gran parte de este código fue inspirado en la sesión del profe Carlos.
    //Uso JSON para brindarle un plus de seguridad al acceso a la base de datos.
    //El archivo db_vet_credenciales.json tiene interrogantes donde debería ir información real.
    public static Connection getConnection(){
        JSONParser parser = new JSONParser();
        Connection conn = null;
        try{
            String ruta_credenciales = System.getProperty("user.dir") + "/src/Utils/db_vet_credenciales.json";
            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(ruta_credenciales));
            String host = (String)jsonObject.get("db_ip");
            String port = (String)jsonObject.get("db_port");
            String username = (String)jsonObject.get("db_username");
            String password = (String)jsonObject.get("db_password");
            String dbURL = "jdbc:mysql://"+host+":"+port+"/vetstuartpeque";
            
            conn = DriverManager.getConnection(dbURL, username, password);
            
            if (conn != null){
                System.out.println("Conexión exitosa!!!!");
            }
        } 
        catch (SQLException | FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException | ParseException ex){
            ex.printStackTrace();
        }
        
        return conn;
    }
}
