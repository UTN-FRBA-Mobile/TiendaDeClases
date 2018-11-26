package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import desarrollomobile.tiendadeclases.tiendadeclases.Adapters.CategoriaListener;
import desarrollomobile.tiendadeclases.tiendadeclases.Adapters.CategoriasAdapter;
import desarrollomobile.tiendadeclases.tiendadeclases.ApiClient;
import desarrollomobile.tiendadeclases.tiendadeclases.R;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.Categoria;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.CategoriasClient;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.ListaDeCategorias;

import java.util.ArrayList;
import java.util.List;

import desarrollomobile.tiendadeclases.tiendadeclases.Service.SubCategorias;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment implements CategoriaListener {

    /*
    Declarar instancias globales
    */
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ImageView imagenCat;
    private TextView nombreUsuario, subCat1;

    public CategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);
        //inicializarVistas(view);
        // Obtener el Recycler
        recycler = (RecyclerView) view.findViewById(R.id.CatsRecycler);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
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
                    mostrarCategorias(response.body().getCategorias());
                } else {
                    Toast.makeText(getActivity(), "Falló la conexión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListaDeCategorias> call, Throwable t) {

            }
        });
    }


    private void mostrarCategorias(List<Categoria> lista) {
        // Crear un nuevo adaptador
        adapter = new CategoriasAdapter(lista, CategoriasFragment.this);
        recycler.setAdapter(adapter);


        /*nombreUsuario.setText(lista.get(0).getNombre());
        subCat1.setText(lista.get(0).getSubCats());
        Picasso.get()
                .load("http://www.tiendadeclases.com/wp-content/uploads/2018/08/bannertdc8.jpg")
                .resize(150, 150)
                .into(imagenCat);
*/
    }

    @Override
    public void onItemClick(ArrayList<SubCategorias> subcat) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("subCategorias", subcat);
        SubCategoriasFragment SubCats = new SubCategoriasFragment ();
        SubCats.setArguments(args);

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.HomeFrame, SubCats)
                .commit();



        //Toast.makeText(getContext(), subcat.get(0).getNombre(), Toast.LENGTH_SHORT).show();
    }
}
