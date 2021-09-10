package Model;

/**
 * @author Jeaniel Osorno
 */
public class Mascota {
    //Representación de a tabla Mascota de la base de datos en Java
    private int mascotaId;
    private String mascotaNombre;
    private String propUsuarioFK;
    
    public Mascota(int mascotaId, String mascotaNombre, String propUsuarioFK){
        this.mascotaId = mascotaId;
        this.mascotaNombre = mascotaNombre;
        this.propUsuarioFK = propUsuarioFK;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public String getPropUsuarioFK() {
        return propUsuarioFK;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public void setPropUsuarioFK(String propUsuarioFK) {
        this.propUsuarioFK = propUsuarioFK;
    }
    
    
}
