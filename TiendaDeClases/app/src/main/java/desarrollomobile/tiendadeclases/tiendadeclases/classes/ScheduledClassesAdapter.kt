package desarrollomobile.tiendadeclases.tiendadeclases.classes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ClassesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import kotlinx.android.synthetic.main.class_scheduled.view.*

class ScheduledClassesAdapter(private val mListener: ClassesFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<ScheduledClassesAdapter.ViewHolder>() {

    var items : List<Class> = ArrayList<Class>()

    override fun getItemViewType(position: Int): Int {
        return R.layout.class_scheduled
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.date.text = item.date
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val name: TextView = mView.className
        val date: TextView = mView.classDate
    }
}