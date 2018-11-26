package desarrollomobile.tiendadeclases.tiendadeclases.Adapters;

import java.util.ArrayList;
import java.util.List;

import desarrollomobile.tiendadeclases.tiendadeclases.Service.SubCategorias;

public interface CategoriaListener {
    void onItemClick(ArrayList<SubCategorias> subcat);
}