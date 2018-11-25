package desarrollomobile.tiendadeclases.tiendadeclases.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import desarrollomobile.tiendadeclases.tiendadeclases.R;
import desarrollomobile.tiendadeclases.tiendadeclases.Service.Categoria;

import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriasViewHolder> {
    private List<Categoria> items;

    public static class CategoriasViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView subCats;

        public CategoriasViewHolder(View v) {
            super(v);
            //imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.tvCategoria);
            subCats = (TextView) v.findViewById(R.id.tvSubCategoria1);
        }
    }

    public CategoriasAdapter(List<Categoria> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CategoriasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cat_item, viewGroup, false);
        return new CategoriasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriasViewHolder viewHolder, int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.subCats.setText(items.get(i).getSubCats());
    }
}