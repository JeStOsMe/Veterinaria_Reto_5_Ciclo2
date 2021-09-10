package Model;

/**
 * @author Jeaniel Stiwar Osorno Mejia
 */
public class Cita {
    //Representación de la tabla Cita de la base de datos en Java
    private int citaId;
    private String citaFecha;
    private String citaDescripcion;
    private int mascotaIdFK;
    
    public Cita (int citaId, String citaFecha, String citaDescripcion, int mascotaIdFK){
        this.citaId = citaId;
        this.citaFecha = citaFecha;
        this.citaDescripcion = citaDescripcion;
        this.mascotaIdFK = mascotaIdFK;
    }

    public int getCitaId() {
        return citaId;
    }

    public String getCitaFecha() {
        return citaFecha;
    }

    public String getCitaDescripcion() {
        return citaDescripcion;
    }

    public int getMascotaIdFK() {
        return mascotaIdFK;
    }

    public void setCitaId(int citaId) {
        this.citaId = citaId;
    }

    public void setCitaFecha(String citaFecha) {
        this.citaFecha = citaFecha;
    }

    public void setCitaDescripcion(String citaDescripcion) {
        this.citaDescripcion = citaDescripcion;
    }

    public void setMascotaIdFK(int mascotaIdFK) {
        this.mascotaIdFK = mascotaIdFK;
    }
    
    
}
