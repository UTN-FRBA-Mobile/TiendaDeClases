package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.location.places.ui.PlacePicker
import desarrollomobile.tiendadeclases.tiendadeclases.R
import java.util.*

class ProfileActivity: AppCompatActivity() {

    private val TAG = "ProfileFragment"
    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var mLocationButton: Button? = null
    private val PLACE_PICKER_REQUEST = 1
    private val PICK_IMAGE = 100
    private lateinit var mPictureProfile: ImageView
    private lateinit var imageUri: Uri

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

        mPictureProfile = findViewById(R.id.profile_pic_view)
        mPictureProfile.setOnClickListener(View.OnClickListener {
                openGallery()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            val place = PlacePicker.getPlace(this, data)
            val locationLabel =  findViewById<TextView>(R.id.user_location)

            locationLabel.setText(place.latLng.toString())
        }
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data!!.data
            mPictureProfile.setImageURI(imageUri)
        }
    }

    fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }
}