package desarrollomobile.tiendadeclases.tiendadeclases.classes


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ClassesFragment

import desarrollomobile.tiendadeclases.tiendadeclases.R


class PayedClassesFragment : Fragment() {

    private var listener: ClassesFragment.OnListFragmentInteractionListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeActivity = this.activity as HomeActivity
        if (view is RecyclerView) {
            with(view) {
                val classesAdapter = PayedClassesAdapter(listener)
                layoutManager = LinearLayoutManager(context)
                adapter = classesAdapter
                classesAdapter.items = homeActivity.classesList.filter { c -> c.status.equals(2) }
                classesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.classes_payed, container, false)
    }

}
