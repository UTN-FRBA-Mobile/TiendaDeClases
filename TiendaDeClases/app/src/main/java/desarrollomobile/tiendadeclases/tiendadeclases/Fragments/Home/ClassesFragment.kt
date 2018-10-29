package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.classes.Api
import desarrollomobile.tiendadeclases.tiendadeclases.classes.ClassesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ClassesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ClassesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ClassesFragment : Fragment() {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    val api by lazy {
        Api.create()
    }

    var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                var tweetsAdapter = ClassesAdapter(listener)
                layoutManager = LinearLayoutManager(context)
                adapter = tweetsAdapter
                disposable =
                        api.getClasses()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        { result ->
                                            tweetsAdapter.items = result.classes
                                            tweetsAdapter.notifyDataSetChanged()
                                        },
                                        {
                                            error ->
                                            Toast.makeText(activity, "No se encontraron clases! " + error, Toast.LENGTH_LONG).show() }
                                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
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
