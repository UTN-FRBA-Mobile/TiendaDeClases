package desarrollomobile.tiendadeclases.tiendadeclases.Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaDeCategorias {

    @SerializedName("categorias")
    @Expose
    private List<Categoria> categorias = null;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    //public void setCategorias(List<Categoria> categorias) {this.categorias = categorias;}

}
