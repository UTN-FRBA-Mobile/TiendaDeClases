package desarrollomobile.tiendadeclases.tiendadeclases.Service;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaDeCategorias implements Parcelable {

    @SerializedName("categorias")
    @Expose
    private List<Categoria> categorias = null;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    //public void setCategorias(List<Categoria> categorias) {this.categorias = categorias;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.categorias);
    }

    public ListaDeCategorias() {
    }

    protected ListaDeCategorias(Parcel in) {
        this.categorias = in.createTypedArrayList(Categoria.CREATOR);
    }

    public static final Parcelable.Creator<ListaDeCategorias> CREATOR = new Parcelable.Creator<ListaDeCategorias>() {
        @Override
        public ListaDeCategorias createFromParcel(Parcel source) {
            return new ListaDeCategorias(source);
        }

        @Override
        public ListaDeCategorias[] newArray(int size) {
            return new ListaDeCategorias[size];
        }
    };
}
