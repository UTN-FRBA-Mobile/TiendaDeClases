package desarrollomobile.tiendadeclases.tiendadeclases.Messages

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.MessageFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.MessagesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import kotlinx.android.synthetic.main.message_info.view.*


class MessagesAdapter(private val mListener: MessagesFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {


    var items: List<Message> = ArrayList<Message>()

    override fun getItemViewType(position: Int): Int {
        return R.layout.message_main
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        holder.description.text = item.description
        if (!item.getRead())
            holder.itemView.setBackgroundColor(Color.rgb(237, 242, 250))
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView), View.OnClickListener {
        val description: TextView = mView.messageDescription


        init {
            mView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            var message: Message = items[adapterPosition]
            view.setBackgroundColor(Color.WHITE)
            message.setRead(true)
            val activity = view.getContext() as HomeActivity
            activity.messagesClassesList = message.getClasses()
            activity.supportFragmentManager.beginTransaction().replace(R.id.HomeFrame, MessageFragment()).commit()
        }

    }

}