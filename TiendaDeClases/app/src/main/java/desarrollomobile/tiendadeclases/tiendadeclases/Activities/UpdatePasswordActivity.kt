package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.users.PasswordRequest
import desarrollomobile.tiendadeclases.tiendadeclases.users.UsersApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UpdatePasswordActivity: AppCompatActivity() {

    private lateinit var mPreferenceManager: PreferencesManager
    private lateinit var mButtonChangePassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_password)
        mPreferenceManager = PreferencesManager(this)

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://167.99.3.180:8080/TDC-0.1/").client(client).build()

        mButtonChangePassword = findViewById(R.id.change_password_button)
        mButtonChangePassword.setOnClickListener(View.OnClickListener {

            if (findViewById<EditText>(R.id.newPassword_edit).text.toString() == findViewById<EditText>(R.id.repeatPassword_edit).text.toString()) {
                val userApi = retrofit.create(UsersApi::class.java)
                val responsePut = userApi.updatePassword(PasswordRequest(mPreferenceManager.getStringPreference("userName"),findViewById<EditText>(R.id.oldPassword_edit).text.toString(), findViewById<EditText>(R.id.newPassword_edit).text.toString()))
                responsePut.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ it ->
                    if(it.status == 200) {
                        Toast.makeText(this, "Contraseña Actualizada", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(this, "La contraseña vieja es incorrecta", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_LONG).show()
            }
        })
    }

}