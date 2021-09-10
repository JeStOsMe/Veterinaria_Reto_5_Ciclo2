package Model;

/**
 * @author Jeaniel Osorno
 */

/*Clase que hace de representación, a nivel Cödigo Java, de la tabla propietario en la base de datos*/
public class Propietario {
    private String propUsuario;
    private String propApellido;
    private String propNombre;
    private String propTelefono;
    
    //Contructor que inicializa los atributos de la clase
    public Propietario(String propUsuario, String propApellido, String propNombre, String propTelefono){
        this.propUsuario = propUsuario;
        this.propApellido = propApellido;
        this.propNombre = propNombre;
        this.propTelefono = propTelefono;
    }
    
    //Getters de los atributos
    public String getPropUsuario() {
        return propUsuario;
    }

    public String getPropApellido() {
        return propApellido;
    }

    public String getPropNombre() {
        return propNombre;
    }

    public String getPropTelefono() {
        return propTelefono;
    }
    
    //Setters de los atributos
    public void setPropUsuario(String propUsuario) {
        this.propUsuario = propUsuario;
    }

    public void setPropApellido(String propApellido) {
        this.propApellido = propApellido;
    }

    public void setPropNombre(String propNombre) {
        this.propNombre = propNombre;
    }

    public void setPropTelefono(String propTelefono) {
        this.propTelefono = propTelefono;
    }
    
}
