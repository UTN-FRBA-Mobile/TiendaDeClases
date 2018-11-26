package desarrollomobile.tiendadeclases.tiendadeclases.classes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ClassesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import kotlinx.android.synthetic.main.class_marked.view.*

class MarkedClassesAdapter(private val mListener: ClassesFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<MarkedClassesAdapter.ViewHolder>() {

    var items : List<Class> = ArrayList<Class>()

    override fun getItemViewType(position: Int): Int {
        return R.layout.class_marked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.description.text = item.description
        holder.date.text = item.date
        holder.price.text = item.price
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val name: TextView = mView.markedClassName
        val description: TextView = mView.markedClassDescription
        val date: TextView = mView.markedClassDate
        val price: TextView = mView.markedClassPrice

        init {
            val payButton: Button = mView.payButton
            payButton.setOnClickListener { v -> payClass(v) }
        }

        fun payClass(v: View) {
            val activity = v.context as HomeActivity
            var item = activity.markedClassesList.removeAt(adapterPosition)
            item.status = 2
            activity.payedClassesList.add(item)
            notifyDataSetChanged()
            Toast.makeText(v.context, "Clase pagada exitosamente", Toast.LENGTH_SHORT).show()
        }
    }
}