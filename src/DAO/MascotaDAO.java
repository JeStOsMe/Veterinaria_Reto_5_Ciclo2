package DAO;

import Model.Mascota;
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
 *
 * @author Jeaniel Osorno
 */
public class MascotaDAO implements Imascota {

    @Override
    public List<Mascota> getAllMascotas() {
        Connection conn = null;
        List<Mascota> mascotas = new ArrayList();
        try{
            conn = ConexionDB.getConnection();
            String sql = "SELECT mascotaId, mascotaNombre, propUsuario FROM mascota;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Mascota mascota = new Mascota(result.getInt(1), result.getString(2), result.getString(3));
                mascotas.add(mascota);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
        conn = null;
        return mascotas;
    }

    @Override
    public Mascota getOneMascota(int mascotaId) {
        Connection conn = null;
        Mascota mascota = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM mascota WHERE mascotaId=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, mascotaId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                mascota = new Mascota(result.getInt(1), result.getString(2), result.getString(3));
                break;
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
        conn = null;
        return mascota;
    }

    @Override
    public void actualizarMascota(Mascota mascota) {
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "UPDATE mascota SET mascotaNombre=? WHERE (mascotaId=?) AND (propUsuario=?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, mascota.getMascotaNombre());
            statement.setInt(2, mascota.getMascotaId());
            statement.setString(3, mascota.getPropUsuarioFK());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "El nombre de la mascota ha sido actualizado!!!");
            }
            conn = null;
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarMascota(int mascotaId) {
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "DELETE FROM mascota WHERE mascotaId=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, mascotaId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "Mascota eliminada del registro exitosamente!!!");
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
    }

    @Override
    public void agregarMascota(Mascota mascota) {
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "INSERT INTO mascota(mascotaId, mascotaNombre, propUsuario) VALUES (?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, mascota.getMascotaId());
            statement.setString(2, mascota.getMascotaNombre());
            statement.setString(3, mascota.getPropUsuarioFK());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "Mascota agrega al registro exitosamente!!!");
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
    }
    
}
