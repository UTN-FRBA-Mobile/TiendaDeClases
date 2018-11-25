package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Messages.MessagesApi
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.classes.Class
import desarrollomobile.tiendadeclases.tiendadeclases.classes.MessagesClassesAdapter
import io.reactivex.disposables.Disposable


private const val ARG_PARAM1 = "param1"

class MessageFragment : Fragment() {

    var classesList: MutableList<Class> = ArrayList<Class>()
    private var listener: OnListFragmentInteractionListener? = null

    val messagesApi by lazy {
        MessagesApi.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classes_from_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeActivity = this.activity as HomeActivity
        if (view is RecyclerView) {
            with(view) {
                var classesAdapter = MessagesClassesAdapter(listener)
                layoutManager = LinearLayoutManager(context)
                adapter = classesAdapter
                classesAdapter.items = homeActivity.messagesClassesList
                classesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var disposable: Disposable? = null
    }


    interface OnListFragmentInteractionListener : ClassesFragment.OnListFragmentInteractionListener {
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MessageFragment()
    }
}
