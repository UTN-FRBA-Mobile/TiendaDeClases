package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.FormActivity
import desarrollomobile.tiendadeclases.tiendadeclases.R
import kotlinx.android.synthetic.main.fragment_formulario.*
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FormularioFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FormularioFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FormularioFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var subCategoria: String? = null

//    private var listener: ClassesFragment.OnListFragmentInteractionListener? = null

    private var locationManager : LocationManager? = null
    var deschequear: Boolean by Delegates.observable(false) {
        prop, old, new ->
            if(new) {
                cbUbicacionActual.isChecked = false
                deschequear = false
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        this.subCategoria = this.arguments!!.getString("NAME_KEY")!!.toString()



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editCategoria.setText(this.subCategoria, TextView.BufferType.EDITABLE)

        cbUbicacionActual.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                try {
                    if (ActivityCompat.checkSelfPermission(context!!,
                                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context!!,
                                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(
                                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
                                100)
                    } else {
                        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener);
                        Log.e("DB", "PERMISSION GRANTED")
                    }
                } catch(ex: SecurityException) {
                    Log.d("myTag", "Security Exception, no location available");
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        try {
            if(grantResults.all { x -> x == PackageManager.PERMISSION_GRANTED}){
                locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener);
            }else{
                Toast.makeText(contexto, "No va a poder utilizar la funcion de localizar automaticamente sin el permiso de GPS aceptado", Toast.LENGTH_SHORT).show()
                deschequear = true
            }
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available");
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
  //  fun onButtonPressed(uri: Uri) {
 //       listener?.onFragmentInteraction(uri)
 //   }

    var contexto : Context ?= null
    override fun onAttach(context: Context) {
        super.onAttach(context)
    //    if (context is CategoriasFragment.OnFragmentInteractionListener) {
  //          listener = context
      //  } else {
       //     throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
       // }
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?;
        contexto = context
    }

    override fun onDetach() {
        super.onDetach()
        //slistener = null
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
         * @return A new instance of fragment FormularioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FormularioFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    var latitude : Double ?= null
    var longitude : Double ?= null
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitude = location.latitude
            longitude = location.longitude
            //Toast.makeText(contexto, "La ubicacion es la siguiente: latitud -> " + latitude + " longitude -> " + longitude, Toast.LENGTH_SHORT).show()
            editLatitud.setText(latitude.toString(), TextView.BufferType.EDITABLE)
            editLongitud.setText(longitude.toString(),TextView.BufferType.EDITABLE)

        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {
            deschequear = true
            Toast.makeText(contexto, "El GPS se debe encontrar encendido para poder utilzar la ubicacion actual", Toast.LENGTH_SHORT).show()
        }
    }

}
