package DAO;

import Model.Propietario;
import java.util.List;

/**
 *
 * @author Jeaniel Osorno
 */
public interface Ipropietario {
    
    //Métodos que implementará la clase PropietarioDAO, cumpliendo la función de CRUD.
    public List<Propietario> getAllPropietarios();
    
    public Propietario getOnePropietario(String propUsuario);
    
    public void actualizarPropietario (Propietario propietario);
    
    public void eliminarPropietario (String propUsuario);
    
    public void agregarPropietario (Propietario propietario);
    
}
