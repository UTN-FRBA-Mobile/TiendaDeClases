package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.location.places.ui.PlacePicker
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ProfileFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import java.util.*

class ProfileActivity: AppCompatActivity() {

    private val TAG = "ProfileFragment"
    private var param1: String? = null
    private var param2: String? = null
    private var listener: ProfileFragment.OnFragmentInteractionListener? = null
    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var mDisplayLocation: TextView? = null
    private var mLocationButton: Button? = null
    private val PLACE_PICKER_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile)

        mDisplayDate = findViewById(R.id.user_birthday)

        mDisplayDate?.setOnClickListener(View.OnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(
                    this,
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

        mLocationButton = findViewById(R.id.change_location)

        mLocationButton?.setOnClickListener(View.OnClickListener {
            val builder = PlacePicker.IntentBuilder()

            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                val locationLabel =  findViewById<TextView>(R.id.user_location)

                locationLabel.setText(place.latLng.toString())
            }
        }
    }
}