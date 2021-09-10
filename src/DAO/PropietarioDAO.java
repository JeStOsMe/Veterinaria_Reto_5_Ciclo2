package DAO;

import Model.Propietario;
import Utils.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Jeaniel Osorno
 */
public class PropietarioDAO implements Ipropietario{
    /*Esta clase implementa la interfaz Ipropietario y sus respectivos métodos,
    quienes, con su desarrollo, formarían el CRUD, sin incluir aún las GUI*/
    
    @Override
    public List<Propietario> getAllPropietarios(){
        Connection conn = null;
        List<Propietario> Propietarios = new ArrayList();
        try{
            conn = ConexionDB.getConnection();
            String sql = "SELECT propUsuario, propApellido, propNombre, propTelefono FROM propietario;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                Propietario propietario = new Propietario(result.getString(1), result.getString(2), result.getString(3), result.getString(4));
                Propietarios.add(propietario);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código" + ex.getErrorCode() 
                        +"\nError: " + ex.getMessage());
        }
        conn = null;
        return Propietarios;
    }
    
    @Override
    public Propietario getOnePropietario (String propUsuario){
        Connection conn = null;
        Propietario propietario = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "SELECT propUsuario, propApellido, propNombre, propTelefono WHERE propUsuario=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, propUsuario);
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                propietario = new Propietario(result.getString(1), result.getString(2), 
                                                result.getString(3), result.getString(4));
                break;
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() 
                    +"\nError: " + ex.getMessage());
        }
        conn = null;
        return propietario;
    }
    
    @Override
    public void actualizarPropietario(Propietario propietario){
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "UPDATE propietario SET propApellido=?, propNombre=?, propTelefono=? WHERE propUsuario=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, propietario.getPropApellido());
            statement.setString(2, propietario.getPropNombre());
            statement.setString(3, propietario.getPropTelefono());
            statement.setString(4, propietario.getPropUsuario());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "El registro fue actualizado exitosamente!!!");
            }
            conn = null;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode()
                                        +"\nError: " + ex.getMessage());
        }
    }
    
    @Override
    public void eliminarPropietario(String propUsuario){
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "DELETE FROM propietario WHERE propUsuario=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, propUsuario);
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "Propietario eliminado de la base de datos!!!");
            }
            conn = null;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
    }
    
    @Override
    public void agregarPropietario(Propietario propietario){
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "INSERT INTO propietario(propUsuario, propApellido, propNombre, propTelefono) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, propietario.getPropUsuario());
            statement.setString(2, propietario.getPropApellido());
            statement.setString(3, propietario.getPropNombre());
            statement.setString(4, propietario.getPropTelefono());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "Propietario agregado exitosamente!!!");
            }
            conn = null;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
    }
}
