package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home

import android.app.Activity.RESULT_FIRST_USER
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import desarrollomobile.tiendadeclases.tiendadeclases.R
import org.w3c.dom.Text
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.MainActivity
import java.util.*
import android.widget.DatePicker
import com.google.android.gms.location.places.ui.PlacePicker
import android.widget.Toast
import com.google.android.gms.location.places.Place
import android.content.Intent




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val TAG = "ProfileFragment"
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var mDisplayLocation: TextView? = null
    private var mLocationButton: Button? = null
    private val PLACE_PICKER_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val viewInflated = inflater.inflate(R.layout.fragment_profile, container, false)

        mDisplayDate = viewInflated.findViewById(R.id.user_birthday)

        mDisplayDate?.setOnClickListener(View.OnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                    this.context,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year, month, day)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        })

        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1

            val date = month.toString() + "/" + day + "/" + year
            mDisplayDate?.setText(date)
        }

        mLocationButton = viewInflated.findViewById(R.id.change_location)

        mLocationButton?.setOnClickListener(View.OnClickListener {
            val builder = PlacePicker.IntentBuilder()

            startActivityForResult(builder.build(activity), PLACE_PICKER_REQUEST)

        })

        return viewInflated
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(context, data)
                val locationLabel =  activity!!.findViewById<TextView>(R.id.user_location)

                locationLabel.setText(place.latLng.toString())
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}