package DAO;

import Model.Cita;
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
public class CitaDAO implements Icita{

    @Override
    public List<Cita> getAllCitas() {
            Connection conn = null;
            List<Cita> citas = new ArrayList();
            try{
                conn = ConexionDB.getConnection();
                String sql = "SELECT * FROM cita;";
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                while (result.next()){
                    Cita cita = new Cita (result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                    citas.add(cita);
                }
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
            }
            conn = null;
            return citas;
    }

    @Override
    public Cita getOneCita(int citaId) {
        Connection conn = null;
        Cita cita = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "SELECT * FROM cita WHERE citaId = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, citaId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                cita = new Cita (result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
                break;
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
        conn = null;
        return cita;
    }

    @Override
    public void actualizarCita(Cita cita) {
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "UPDATE cita SET citaFecha=?, citaDescripcion=? WHERE citaId=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cita.getCitaFecha());
            statement.setString(2, cita.getCitaDescripcion());
            statement.setInt(3, cita.getCitaId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "La cita ha sido actualizada correctamente!!!");
            }
            conn = null;
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarCita(int citaId) {
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "DELETE FROM cita WHERE citaId=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, citaId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "La cita ha sido eliminada del registro exitosamente!!!");
            }
            conn = null;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());

        }
    }

    @Override
    public void agregarCita(Cita cita) {
        Connection conn = null;
        try{
            conn = ConexionDB.getConnection();
            String sql = "INSERT INTO cita(citaId, citaFecha, citaDescripcion, mascotaId) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cita.getCitaId());
            statement.setString(2, cita.getCitaFecha());
            statement.setString(3, cita.getCitaDescripcion());
            statement.setInt(4, cita.getMascotaIdFK());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0){
                JOptionPane.showMessageDialog(null, "La cita fue agregada al registro exitosamente!!!");
            }
            conn = null;
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Código: " + ex.getErrorCode() + "\nError: " + ex.getMessage());
        }
    }
    
}
