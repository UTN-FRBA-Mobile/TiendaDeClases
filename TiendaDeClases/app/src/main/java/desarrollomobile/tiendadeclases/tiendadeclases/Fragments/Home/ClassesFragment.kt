package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.view.ViewPager
import desarrollomobile.tiendadeclases.tiendadeclases.classes.ClassPagerAdapter
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.classes.Api


class ClassesFragment : Fragment() {

    private var listener: OnListFragmentInteractionListener? = null

    var viewPager: ViewPager? = null
    var tabLayout: TabLayout? = null

    val api by lazy {
        Api.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.classes_tabs, container, false)

        viewPager = view.findViewById(R.id.viewpager)
        tabLayout = view.findViewById(R.id.tabs)

        viewPager?.adapter = ClassPagerAdapter(childFragmentManager)
        tabLayout?.setupWithViewPager(viewPager)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ClassesFragment()
    }
}
