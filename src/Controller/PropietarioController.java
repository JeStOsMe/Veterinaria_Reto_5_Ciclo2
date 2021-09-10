package Controller;

import DAO.Ipropietario;
import Model.Propietario;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author Jeaniel Osorno
 */
public class PropietarioController {
    //Clase centrada en ser el puente entre el DAO y la GUI.
    private Ipropietario PropietarioDAO;
    
    public PropietarioController(Ipropietario PropietarioDAO){
        this.PropietarioDAO = PropietarioDAO;
    }
    
    //Retorna un modelo de tabla conformado por todos los propietarios registros.
    //Esto a modo de ventana inicial, donde podremos realizar las operaciones
    public TableModel consultarPropietarios(){
        String[] Titulos ={"Usuario", "Apellido", "Nombre", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(null, Titulos);
        List<Propietario> propietarios = PropietarioDAO.getAllPropietarios();
        for (Propietario propietario: propietarios){
            String[] registro = new String[4];
            registro[0] = propietario.getPropUsuario()+"";
            registro[1] = propietario.getPropApellido()+"";
            registro[2] = propietario.getPropNombre()+"";
            registro[3] = propietario.getPropTelefono()+"";
            
            modelo.addRow(registro);
        }
        return modelo;
    }
    //Invocación de los métodos CRUD desde la GUI.
    public void actualizarPropietario (Propietario propietario){
        PropietarioDAO.actualizarPropietario(propietario);
    }
    
    public void eliminarPropietario (String propUsuario){
        PropietarioDAO.eliminarPropietario(propUsuario);
    }
    
    public void agregarPropietario (Propietario propietario){
        PropietarioDAO.agregarPropietario(propietario);
    }
}
