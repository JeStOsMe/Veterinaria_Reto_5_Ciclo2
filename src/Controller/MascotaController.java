package Controller;

import DAO.Imascota;
import Model.Mascota;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jeaniel Osorno
 */
public class MascotaController {
    
    private Imascota MascotaDAO;
    
    public MascotaController(Imascota MascotaDAO){
        this.MascotaDAO = MascotaDAO;
    }
    
    public TableModel consultarMascotas(){
        String[] titulos = {"ID", "Nombre", "Usuario propietario"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        List<Mascota> mascotas = MascotaDAO.getAllMascotas();
        for (Mascota mascota: mascotas){
            String[] registro = new String[3];
            registro[0] = String.valueOf(mascota.getMascotaId());
            registro[1] = mascota.getMascotaNombre();
            registro[2] = mascota.getPropUsuarioFK();
            
            modelo.addRow(registro);
        }
        return modelo;
    }
    
    public void actualizarMascota (Mascota mascota){
        MascotaDAO.actualizarMascota(mascota);
    }
    
    public void eliminarMascota (int mascotaId){
        MascotaDAO.eliminarMascota(mascotaId);
    }
    
    public void agregarMascota (Mascota mascota){
        MascotaDAO.agregarMascota(mascota);
    }
    
    
}
