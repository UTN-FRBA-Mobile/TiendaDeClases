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
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.users.Position
import desarrollomobile.tiendadeclases.tiendadeclases.users.User
import desarrollomobile.tiendadeclases.tiendadeclases.users.UsersApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.util.*

class RegisterActivity: AppCompatActivity() {

    private val TAG = "RegisterFragment"
    private val PICK_IMAGE = 100
    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var mLocationButton: Button? = null
    private var mRegisterButton: Button? = null
    private val PLACE_PICKER_REQUEST = 1
    private lateinit var mPreferencesManager: PreferencesManager
    private lateinit var mPictureProfile: ImageView
    private lateinit var imageUri: Uri
    private var bitmap: Bitmap? = null
    private var mPlace: Place? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPreferencesManager = PreferencesManager(this)

        mDisplayDate = findViewById(R.id.user_birthday)

        mDisplayDate?.setOnClickListener{
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
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1

            val date = month.toString() + "/" + day + "/" + year
            mDisplayDate?.setText(date)
        }

        mLocationButton = findViewById(R.id.add_location)

        mLocationButton?.setOnClickListener{
            val builder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)
        }

        mRegisterButton = findViewById(R.id.register_button)

        mRegisterButton?.setOnClickListener{
            if (findViewById<EditText>(R.id.userName_edit).text.toString() != "" &&
                    findViewById<EditText>(R.id.password_edit).text.toString() != "") {
                val userApi = UsersApiClient.getRetrofitClient()
                var position: Position? = null
                if (mPlace != null) {
                    position = Position(mPlace!!.latLng.latitude, mPlace!!.latLng.longitude)
                }

                val responsePost = userApi.addUser(User(findViewById<EditText>(R.id.userName_edit).text.toString(), findViewById<EditText>(R.id.password_edit).text.toString(),
                        findViewById<EditText>(R.id.firstName_edit).text.toString(), findViewById<EditText>(R.id.lastName_edit).text.toString(), mDisplayDate!!.text.toString(),
                        position, imageToString()))
                responsePost.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ it ->
                    if(it.status == 200) {
                        mPreferencesManager.setStringPreference("userName", findViewById<EditText>(R.id.userName_edit).text.toString())
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Sorry username already exists, please choose another", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        mPictureProfile = findViewById(R.id.profile_pic_view)
        mPictureProfile.setOnClickListener{
            openGallery()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            mPlace = PlacePicker.getPlace(this, data)
            val locationLabel =  findViewById<TextView>(R.id.user_location)

            locationLabel.text = mPlace!!.latLng.toString()
        }
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data!!.data
            mPictureProfile.setImageURI(imageUri)
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        }
    }

    fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    fun imageToString(): ByteArray? {

        val byteArray = ByteArrayOutputStream()
        if(bitmap != null) {
            bitmap!!.compress(Bitmap.CompressFormat.PNG, 0, byteArray)
            return byteArray.toByteArray()
        }
        return null
    }



}