package desarrollomobile.tiendadeclases.tiendadeclases.Service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategorias implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imagen);
        dest.writeString(this.nombre);
        dest.writeString(this.subCats);
    }

    public SubCategorias() {
    }

    protected SubCategorias(Parcel in) {
        this.imagen = in.readString();
        this.nombre = in.readString();
        this.subCats = in.readString();
    }

    public static final Parcelable.Creator<SubCategorias> CREATOR = new Parcelable.Creator<SubCategorias>() {
        @Override
        public SubCategorias createFromParcel(Parcel source) {
            return new SubCategorias(source);
        }

        @Override
        public SubCategorias[] newArray(int size) {
            return new SubCategorias[size];
        }
    };
}
