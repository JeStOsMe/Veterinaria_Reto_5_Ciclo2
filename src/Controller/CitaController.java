package Controller;

import DAO.Icita;
import Model.Cita;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jeaniel Osorno
 */
public class CitaController {
    //Clase centrada en ser el medio de comunicación entre el DAO de Cita y su GUI.
    private Icita CitaDAO;
    
    public CitaController(Icita CitaDAO){
        this.CitaDAO = CitaDAO;
    }
    
    public TableModel consultarCitas(){
        String[] Titulos ={"ID Cita", "Fecha", "Descripción", "ID Mascota"};
        DefaultTableModel modelo = new DefaultTableModel(null, Titulos);
        List<Cita> citas = CitaDAO.getAllCitas();
        for (Cita cita: citas){
            String[] registro = new String[4];
            registro[0] = cita.getCitaId()+"";
            registro[1] = cita.getCitaFecha()+"";
            registro[2] = cita.getCitaDescripcion()+"";
            registro[3] = cita.getMascotaIdFK()+"";
            
            modelo.addRow(registro);
        }
        return modelo;
    }
    
    public void actualizarCita (Cita cita){
        CitaDAO.actualizarCita(cita);
    }
    
    public void eliminarCita (int citaId){
        CitaDAO.eliminarCita(citaId);
    }
    
    public void agregarCita (Cita cita){
        CitaDAO.agregarCita(cita);
    }
    
}
