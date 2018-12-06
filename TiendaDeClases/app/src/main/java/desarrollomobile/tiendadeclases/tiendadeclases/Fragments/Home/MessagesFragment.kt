package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login.RequireLoginFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Messages.MessagesAdapter
import desarrollomobile.tiendadeclases.tiendadeclases.Messages.MessagesApi
import desarrollomobile.tiendadeclases.tiendadeclases.R
import io.reactivex.disposables.Disposable


class MessagesFragment : Fragment() {
    private var listener: MessagesFragment.OnListFragmentInteractionListener? = null

    private val TAG = "Messages"
    val messagesApi by lazy {
        MessagesApi.create()
    }

    var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeActivity = this.activity as HomeActivity
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                var messagesAdapter = MessagesAdapter(listener)
                layoutManager = LinearLayoutManager(context)
                adapter = messagesAdapter
                messagesAdapter.items = homeActivity.messagesList
                messagesAdapter.notifyDataSetChanged()
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
                MessagesFragment()
    }
}
