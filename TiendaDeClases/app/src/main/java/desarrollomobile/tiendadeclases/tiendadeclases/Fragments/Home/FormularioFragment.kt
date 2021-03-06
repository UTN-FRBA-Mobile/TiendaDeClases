package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home

import android.Manifest
import android.content.Context
import android.content.Intent
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
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.places.ui.PlacePicker
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.FormActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login.RequireLoginFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import kotlinx.android.synthetic.main.fragment_formulario.*
import kotlin.properties.Delegates
import android.app.Activity
import android.provider.MediaStore


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
    /*
    private var locationManager : LocationManager? = null
    var deschequear: Boolean by Delegates.observable(false) {
        prop, old, new ->
            if(new) {
                cbUbicacionActual.isChecked = false
                deschequear = false
            }
    }
    */

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


        val v = inflater.inflate(R.layout.fragment_formulario, container, false)

        // Inflate the layout for this fragment
        return v;
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editCategoria.setText(this.subCategoria, TextView.BufferType.EDITABLE)
            buttonEnviar.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View): Unit {
                    // Handler code here.

                    val intent = Intent(context, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(context, "Formulario enviado", Toast.LENGTH_SHORT).show()

                }
            })
        /*
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
        */
            checkLunes.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    lunesDesde.isEnabled=true
                    lunesHasta.isEnabled=true
                }
                else {
                    lunesDesde.isEnabled=false
                    lunesHasta.isEnabled=false
                    lunesDesde.text.clear()
                    lunesHasta.text.clear()
                }
            }
            checkMartes.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    martesDesde.isEnabled=true
                    martesHasta.isEnabled=true
                }
                else {
                    martesDesde.isEnabled=false
                    martesHasta.isEnabled=false
                    martesDesde.text.clear()
                    martesHasta.text.clear()
                }
            }
            checkMiercoles.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    miercolesDesde.isEnabled=true
                    miercolesHasta.isEnabled=true
                }
                else {
                    miercolesDesde.isEnabled=false
                    miercolesHasta.isEnabled=false
                    miercolesDesde.text.clear()
                    miercolesHasta.text.clear()
                }
            }
            checkJueves.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    juevesDesde.isEnabled=true
                    juevesHasta.isEnabled=true
                }
                else {
                    juevesDesde.isEnabled=false
                    juevesHasta.isEnabled=false
                    juevesDesde.text.clear()
                    juevesHasta.text.clear()
                }
            }
            checkViernes.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    viernesDesde.isEnabled=true
                    viernesHasta.isEnabled=true
                }
                else {
                    viernesDesde.isEnabled=false
                    viernesHasta.isEnabled=false
                    viernesDesde.text.clear()
                    viernesHasta.text.clear()
                }
            }
            checkSabado.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    sabadoDesde.isEnabled=true
                    sabadoHasta.isEnabled=true
                }
                else {
                    sabadoDesde.isEnabled=false
                    sabadoHasta.isEnabled=false
                    sabadoDesde.text.clear()
                    sabadoHasta.text.clear()
                }
            }

            change_location.setOnClickListener {
                startLocationActivity(context!!)
            }
    }

    /*
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
    */

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
        //locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?;
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

    val PICK_LOCATION = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_LOCATION && resultCode == Activity.RESULT_OK) {
            var selected = PlacePicker.getPlace(contexto, data).latLng
            latitude = selected.latitude
            longitude = selected.longitude
            editLatitud.setText(latitude.toString(), TextView.BufferType.EDITABLE)
            editLongitud.setText(longitude.toString(),TextView.BufferType.EDITABLE)
        }
    }

    fun startLocationActivity(context: Context) {
        if (hasPermissions(context, Manifest.permission.ACCESS_COARSE_LOCATION) && hasPermissions(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
            val builder = PlacePicker.IntentBuilder()
            val intent = builder.build(getActivity())
            startActivityForResult(intent, PICK_LOCATION)
        } else {
            //ActivityCompat.requestPermissions(getActivity()., arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), PICK_LOCATION);
            try {
                if (ActivityCompat.checkSelfPermission(context!!,
                                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context!!,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
                            100)
                } else {
                    val builder = PlacePicker.IntentBuilder()
                    val intent = builder.build(getActivity())
                    startActivityForResult(intent, PICK_LOCATION)
                    Log.e("DB", "PERMISSION GRANTED")
                }
            } catch(ex: SecurityException) {
                Log.d("myTag", "Security Exception, no location available");
            }
        }
    }

    fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }
    /*
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
    */
}
