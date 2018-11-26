package desarrollomobile.tiendadeclases.tiendadeclases.Service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Categoria implements Parcelable {

    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("subCats")
    @Expose
    private String subCats;
    @SerializedName("objSubCats")
    @Expose
    private ArrayList<SubCategorias> listaSubCategorias;


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

    public ArrayList<SubCategorias> getListaSubCategorias() {
        return listaSubCategorias;
    }

    public void setListaSubCategorias(ArrayList<SubCategorias> listaSubCategorias) {
        this.listaSubCategorias = listaSubCategorias;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imagen);
        dest.writeString(this.nombre);
        dest.writeString(this.subCats);
        dest.writeTypedList(this.listaSubCategorias);
    }

    public Categoria() {
    }

    protected Categoria(Parcel in) {
        this.imagen = in.readString();
        this.nombre = in.readString();
        this.subCats = in.readString();
        this.listaSubCategorias = in.createTypedArrayList(SubCategorias.CREATOR);
    }

    public static final Parcelable.Creator<Categoria> CREATOR = new Parcelable.Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel source) {
            return new Categoria(source);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };
}
