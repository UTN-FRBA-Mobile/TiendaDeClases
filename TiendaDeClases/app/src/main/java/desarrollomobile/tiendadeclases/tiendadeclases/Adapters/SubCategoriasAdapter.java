package desarrollomobile.tiendadeclases.tiendadeclases.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import desarrollomobile.tiendadeclases.tiendadeclases.R;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.Categoria;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.SubCategorias;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriasAdapter extends RecyclerView.Adapter<SubCategoriasAdapter.SubCategoriasViewHolder>  {
    private final SubCategoriaListener listener;
    private ArrayList<SubCategorias> items;
    //private String nombreSubCat;


    public static class SubCategoriasViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView subCats;

        public SubCategoriasViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.ivCategoria);
            nombre = (TextView) v.findViewById(R.id.tvCategoria);
            subCats = (TextView) v.findViewById(R.id.tvSubCategoria1);
        }
    }

    public SubCategoriasAdapter(ArrayList<SubCategorias> items, SubCategoriaListener listener) {
        this.items = items;
        this.listener = listener;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SubCategoriasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cat_item, viewGroup, false);
        return new SubCategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubCategoriasViewHolder viewHolder, int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        //final ArrayList<SubCategorias> subcat = items.get(i).getListaSubCategorias();
        final String nombreSubCat = items.get(i).getNombre();
        Picasso.get()
                .load(items.get(i).getImagen())
                .resize(150, 150)
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.subCats.setText(items.get(i).getSubCats());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(nombreSubCat);
            }
        });
    }
}