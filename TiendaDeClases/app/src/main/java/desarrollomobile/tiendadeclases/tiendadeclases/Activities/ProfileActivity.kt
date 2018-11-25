package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

class ProfileActivity: AppCompatActivity() {

    private val TAG = "ProfileFragment"
    private lateinit var mPreferencesManager: PreferencesManager
    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private lateinit var mLocationButton: Button
    private val PLACE_PICKER_REQUEST = 1
    private val PICK_IMAGE = 100
    private lateinit var mPictureProfile: ImageView
    private lateinit var imageUri: Uri
    private var bitmap: Bitmap? = null
    private lateinit var mChangePasswordButton: Button
    private lateinit var mSaveChangesButton: Button
    private var mPlace: Place? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)
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

        mLocationButton = findViewById(R.id.change_location)
        mLocationButton.setOnClickListener{
            val builder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)

        }

        mPictureProfile = findViewById(R.id.profile_pic_view)
        mPictureProfile.setOnClickListener{
                openGallery()
        }

        mChangePasswordButton = findViewById(R.id.change_password)
        mChangePasswordButton.setOnClickListener{
            val intent = Intent(this, UpdatePasswordActivity::class.java)
            startActivity(intent)
        }

        mSaveChangesButton = findViewById(R.id.save_changes)
        mSaveChangesButton.setOnClickListener{

            var position: Position? = null
            if (mPlace != null) {
                position = Position(mPlace!!.latLng.latitude, mPlace!!.latLng.longitude)
            }

            val userName = mPreferencesManager.getStringPreference("userName")
            val userModify = User(userName, "", findViewById<EditText>(R.id.user_first_name).text.toString(), findViewById<EditText>(R.id.user_last_name).text.toString()
                    , mDisplayDate!!.text.toString(), position, imageToString())

            val responseGet = UsersApiClient.getRetrofitClient().modifyUser(userModify)

            responseGet.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{
                Toast.makeText(this, "User succesfuly modified", Toast.LENGTH_LONG).show()
            }
        }

        fillProfileData()
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

    fun fillProfileData() {

        val userName = mPreferencesManager.getStringPreference("userName")
        val responseGet = UsersApiClient.getRetrofitClient().getUser(userName)
        responseGet.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ it ->

            findViewById<TextView>(R.id.user_username).text = userName
            findViewById<TextView>(R.id.user_first_name).text = it.firstName
            findViewById<TextView>(R.id.user_last_name).text = it.lastName
            mDisplayDate!!.text = it.birthday

            if(it.position != null) {
                val position = LatLng(it.position!!.latitude, it.position!!.longitude)
                findViewById<TextView>(R.id.user_location).text = position.toString()
            }

            if(it.profilePicture != null) {
                val bmp = BitmapFactory.decodeByteArray(it.profilePicture,0, it.profilePicture!!.size);
                findViewById<ImageView>(R.id.profile_pic_view).setImageBitmap(bmp)
            }

        }
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