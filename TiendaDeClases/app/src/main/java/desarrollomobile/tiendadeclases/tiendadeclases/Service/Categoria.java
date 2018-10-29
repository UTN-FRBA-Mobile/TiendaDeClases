package desarrollomobile.tiendadeclases.tiendadeclases.Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categoria {

    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("subCats")
    @Expose
    private String subCats;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSubCats() {
        return subCats;
    }

    public void setSubCats(String subCats) {
        this.subCats = subCats;
    }
}
