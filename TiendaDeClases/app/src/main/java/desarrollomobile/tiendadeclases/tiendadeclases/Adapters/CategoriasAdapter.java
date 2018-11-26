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

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriasViewHolder>  {
    private final CategoriaListener listener;
    private List<Categoria> items;


    public static class CategoriasViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView subCats;

        public CategoriasViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.ivCategoria);
            nombre = (TextView) v.findViewById(R.id.tvCategoria);
            subCats = (TextView) v.findViewById(R.id.tvSubCategoria1);
        }
    }

    public CategoriasAdapter(List<Categoria> items, CategoriaListener listener) {
        this.items = items;
        this.listener = listener;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CategoriasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cat_item, viewGroup, false);
        return new CategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriasViewHolder viewHolder, int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        final ArrayList<SubCategorias> subcat = items.get(i).getListaSubCategorias();
        Picasso.get()
                .load(items.get(i).getImagen())
                .resize(150, 150)
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.subCats.setText(items.get(i).getSubCats());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(subcat);
            }
        });
    }
}