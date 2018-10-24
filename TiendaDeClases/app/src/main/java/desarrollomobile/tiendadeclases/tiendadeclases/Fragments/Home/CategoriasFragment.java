package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import desarrollomobile.tiendadeclases.tiendadeclases.ApiClient;
import desarrollomobile.tiendadeclases.tiendadeclases.R;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.Categoria;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.CategoriasClient;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.ListaDeCategorias;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment {

    private ImageView imagenCat;
    private TextView nombreUsuario, subCat1;

    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);
        inicializarVistas(view);
        obtenerCategorias();
        return view;
    }

    private void inicializarVistas(View view) {
        imagenCat = view.findViewById(R.id.ivCategoria);
        nombreUsuario = view.findViewById(R.id.tvCategoria);
        subCat1 = view.findViewById(R.id.tvSubCategoria1);
    }

    private void obtenerCategorias() {
        CategoriasClient client = ApiClient.getInstance().getClient().create(CategoriasClient.class);
        Call<ListaDeCategorias> call = client.getCategorias();
        call.enqueue(new Callback<ListaDeCategorias>() {
            @Override
            public void onResponse(Call<ListaDeCategorias> call, Response<ListaDeCategorias> response) {
                if (response.isSuccessful()) {
                    ListaDeCategorias listaDeCategorias = response.body();
                    mostrarCategorias(listaDeCategorias.getCategorias());
                } else {
                    Toast.makeText(getActivity(), "Fallo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListaDeCategorias> call, Throwable t) {

            }
        });
    }

    private void mostrarCategorias(List<Categoria> lista) {
        nombreUsuario.setText(lista.get(0).getNombre());
        subCat1.setText(lista.get(0).getSubCats());
        Picasso.get()
                .load("http://www.tiendadeclases.com/wp-content/uploads/2018/08/bannertdc8.jpg")
                .resize(150, 150)
                .into(imagenCat);

    }

}
