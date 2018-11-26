package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home;


import android.content.Intent;
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

import desarrollomobile.tiendadeclases.tiendadeclases.Activities.FormActivity;
import desarrollomobile.tiendadeclases.tiendadeclases.Adapters.CategoriaListener;
import desarrollomobile.tiendadeclases.tiendadeclases.Adapters.CategoriasAdapter;
import desarrollomobile.tiendadeclases.tiendadeclases.Adapters.SubCategoriaListener;
import desarrollomobile.tiendadeclases.tiendadeclases.Adapters.SubCategoriasAdapter;
import desarrollomobile.tiendadeclases.tiendadeclases.ApiClient;
import desarrollomobile.tiendadeclases.tiendadeclases.R;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.SubCategorias;
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
public class SubCategoriasFragment extends Fragment implements SubCategoriaListener {

    /*
    Declarar instancias globales
    */
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ImageView imagenSubCat;
    private TextView nombreSubCat, subSubCat;

    public SubCategoriasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_subcategorias, container, false);
        //inicializarVistas(view);
        // Obtener el Recycler
        recycler = (RecyclerView) view.findViewById(R.id.SubCatsRecycler);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
        obtenerCategorias();
        return view;
    }

    private void inicializarVistas(View view) {
        imagenSubCat = view.findViewById(R.id.ivCategoria);
        nombreSubCat = view.findViewById(R.id.tvCategoria);
        subSubCat = view.findViewById(R.id.tvSubCategoria1);
    }

    private void obtenerCategorias() {

        Bundle bundle = this.getArguments();
        ArrayList<SubCategorias> subCatsList = bundle.getParcelableArrayList("subCategorias");

        if (subCatsList != null) {
            mostrarCategorias(subCatsList);
        } else {
            Toast.makeText(getActivity(), "Falló la conexión, por favor intentá de nuevo", Toast.LENGTH_SHORT).show();
        }

        /*
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
        */
    }


    private void mostrarCategorias(ArrayList<SubCategorias> lista) {
        // Crear un nuevo adaptador
        adapter = new SubCategoriasAdapter(lista, SubCategoriasFragment.this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String nombreSubcat) {
        //Toast.makeText(getContext(), nombreSubcat, Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getContext(), FormActivity.class);
        myIntent.putExtra("subcategoria", nombreSubcat);
        startActivity(myIntent);
    }

}
