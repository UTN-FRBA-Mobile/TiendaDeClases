package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.gson.GsonBuilder
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ProfileFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.users.User
import desarrollomobile.tiendadeclases.tiendadeclases.users.UsersApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPreferencesManager = PreferencesManager(this)

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

        mLocationButton = findViewById(R.id.add_location)

        mLocationButton?.setOnClickListener(View.OnClickListener {
            val builder = PlacePicker.IntentBuilder()

            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)

        })

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://167.99.3.180:8080/TDC-0.1/").client(client).build()

        mRegisterButton = findViewById<Button>(R.id.register_button)

        mRegisterButton?.setOnClickListener(View.OnClickListener {
            if (findViewById<EditText>(R.id.userName_edit).text.toString() != "" &&
                    findViewById<EditText>(R.id.password_edit).text.toString() != "") {
                val userApi = retrofit.create(UsersApi::class.java)

                val responsePost = userApi.addUser(User(findViewById<EditText>(R.id.userName_edit).text.toString(), findViewById<EditText>(R.id.password_edit).text.toString(),
                        findViewById<EditText>(R.id.firstName_edit).text.toString(), findViewById<EditText>(R.id.lastName_edit).text.toString(),null,
                        imageToString()))
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
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        }
    }

    fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    fun imageToString(): ByteArray {

        val byteArray = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 0, byteArray)

        return byteArray.toByteArray()
    }



}