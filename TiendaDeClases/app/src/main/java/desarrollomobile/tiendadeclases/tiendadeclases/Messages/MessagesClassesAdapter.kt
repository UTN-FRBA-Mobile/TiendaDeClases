package desarrollomobile.tiendadeclases.tiendadeclases.classes

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ClassesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import kotlinx.android.synthetic.main.class_info_from_notification.view.*

class MessagesClassesAdapter(private val mListener: ClassesFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<MessagesClassesAdapter.ViewHolder>() {

    var items: List<Class> = ArrayList<Class>()

    override fun getItemViewType(position: Int): Int {
        return R.layout.class_main_from_notification
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        holder.name.text = item.name
        holder.description.text = item.description
        holder.datePrice.text = " " + item.date + " - " + item.price
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val name: TextView = mView.class_name
        val description: TextView = mView.class_description
        val datePrice: TextView = mView.class_price

        init {
            val favButton: ImageButton = mView.favButton
            favButton.setOnClickListener { v -> addToClasses(v) }
        }

        fun addToClasses(v: View) {
            Log.d("BREAKPOINT", adapterPosition.toString())
            var mutableItems = items.toMutableList()
            var item = mutableItems.removeAt(adapterPosition)
            items = mutableItems.toList()
            var activity = v.context as HomeActivity
            activity.classesList.add(item)
            notifyDataSetChanged()
        }

    }
}
