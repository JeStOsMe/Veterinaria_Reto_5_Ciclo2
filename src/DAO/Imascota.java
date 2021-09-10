
package DAO;

import Model.Mascota;
import java.util.List;

/**
 *
 * @author Jeaniel Osorno
 */
public interface Imascota {
    
    public List<Mascota> getAllMascotas();
    
    public Mascota getOneMascota(int mascotaId);
    
    public void actualizarMascota(Mascota mascota);
    
    public void eliminarMascota(int mascotaId);
    
    public void agregarMascota(Mascota mascota);
    
}
