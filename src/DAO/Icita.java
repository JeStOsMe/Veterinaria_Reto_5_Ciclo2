package DAO;

import Model.Cita;
import java.util.List;

/**
 *
 * @author Jeaniel Osorno
 */
public interface Icita {
    
    public List<Cita> getAllCitas();
    
    public Cita getOneCita(int citaId);
    
    public void actualizarCita (Cita cita);
    
    public void eliminarCita (int citaId);
    
    public void agregarCita (Cita cita);
    
}
