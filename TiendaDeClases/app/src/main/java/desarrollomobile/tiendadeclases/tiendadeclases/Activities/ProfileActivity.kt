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
import android.widget.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.gson.GsonBuilder
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
    private lateinit var mChangePasswordButton: Button
    private lateinit var mSaveChangesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_profile)
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

        mLocationButton = findViewById(R.id.change_location)
        mLocationButton.setOnClickListener(View.OnClickListener {
            val builder = PlacePicker.IntentBuilder()

            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)

        })

        mPictureProfile = findViewById(R.id.profile_pic_view)
        mPictureProfile.setOnClickListener(View.OnClickListener {
                openGallery()
        })

        mChangePasswordButton = findViewById(R.id.change_password)
        mChangePasswordButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UpdatePasswordActivity::class.java)
            startActivity(intent)
        })

        mSaveChangesButton = findViewById(R.id.save_changes)
        mSaveChangesButton.setOnClickListener(View.OnClickListener {
            val userName = mPreferencesManager.getStringPreference("userName")
            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            val client : OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http://167.99.3.180:8080/TDC-0.1/").client(client).build()

            val userApi = retrofit.create(UsersApi::class.java)

            val userModify = User(userName, "", findViewById<EditText>(R.id.user_first_name).text.toString(), findViewById<EditText>(R.id.user_last_name).text.toString())

            val responseGet = userApi.modifyUser(userModify)

            responseGet.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ it ->

                Toast.makeText(this, "User succesfuly modified", Toast.LENGTH_LONG)


            }
        })

        fillProfileData()
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

    fun fillProfileData() {

        val userName = mPreferencesManager.getStringPreference("userName")
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://167.99.3.180:8080/TDC-0.1/").client(client).build()

        val userApi = retrofit.create(UsersApi::class.java)

        val responseGet = userApi.getUser(userName)
        responseGet.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ it ->

            findViewById<TextView>(R.id.user_username).text = userName
            findViewById<TextView>(R.id.user_first_name).text = it.firstName
            findViewById<TextView>(R.id.user_last_name).text = it.lastName


        }
    }


}