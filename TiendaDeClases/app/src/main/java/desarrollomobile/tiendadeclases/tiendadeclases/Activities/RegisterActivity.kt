package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.model.LatLng
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.users.Position
import desarrollomobile.tiendadeclases.tiendadeclases.users.User
import desarrollomobile.tiendadeclases.tiendadeclases.users.UsersApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.util.*

class RegisterActivity: UserModifyActivity() {

    private val TAG = "RegisterFragment"
    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private lateinit var mLocationButton: Button
    private var mRegisterButton: Button? = null
    private lateinit var mPreferencesManager: PreferencesManager
    private lateinit var mPictureProfile: ImageView
    private var imageUri: Uri? = null
    private var bitmap: Bitmap? = null
    private var mPlace: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPreferencesManager = PreferencesManager(this)

        mDisplayDate = findViewById(R.id.user_birthday)
        mDisplayDate?.setOnClickListener{
            displayDate(mDateSetListener)
        }
        mDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            dateModelate(year, month, day, mDisplayDate)
        }

        mLocationButton = findViewById(R.id.add_location)
        mLocationButton.setOnClickListener{
            startLocationActivity(this)
        }

        mPictureProfile = findViewById(R.id.profile_pic_view)
        mPictureProfile.setOnClickListener{
            openGallery(this)
        }

        mRegisterButton = findViewById(R.id.register_button)

        mRegisterButton?.setOnClickListener{
            if (obligatoryFieldsNotNull()) {
                val userApi = UsersApiClient.getRetrofitClient()

                val responsePost = userApi.addUser(getUserRegister())
                responsePost.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ response ->
                    if(response.status == 200) {
                        mPreferencesManager.setStringPreference("userName", findViewById<EditText>(R.id.userName_edit).text.toString())
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, this.getString(R.string.username_already_exist), Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, this.getString(R.string.fill_obligatory_fields), Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_LOCATION && resultCode == Activity.RESULT_OK) {
            mPlace = PlacePicker.getPlace(this, data).latLng
            val locationLabel =  findViewById<TextView>(R.id.user_location)

            locationLabel.text = mPlace.toString()
        }
        if(requestCode == PICK_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            imageUri = data!!.data
            mPictureProfile.setImageURI(imageUri)
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        }
    }

    private fun obligatoryFieldsNotNull(): Boolean {

        return findViewById<EditText>(R.id.userName_edit).text.toString() != "" &&
                findViewById<EditText>(R.id.password_edit).text.toString() != "" &&
                mPlace != null
    }

    private fun getUserRegister(): User {

        return User(findViewById<EditText>(R.id.userName_edit).text.toString(), findViewById<EditText>(R.id.password_edit).text.toString(),
                findViewById<EditText>(R.id.firstName_edit).text.toString(), findViewById<EditText>(R.id.lastName_edit).text.toString(), mDisplayDate!!.text.toString(),
                Position(mPlace!!.latitude, mPlace!!.longitude), imageToString(bitmap))
    }

}