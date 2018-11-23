package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.users.User
import desarrollomobile.tiendadeclases.tiendadeclases.users.UsersApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        setContentView(R.layout.fragment_login)

        val button = findViewById<Button>(R.id.button_login)

        button.setOnClickListener{
            val userName = findViewById<EditText>(R.id.text_usuario).text
            val password = findViewById<EditText>(R.id.text_contraseña).text

            val response = userApi.loginUser(User(userName.toString(), password.toString(), "", ""))
            response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{it ->
                if(it.status == 200) {
                    var preference = getSharedPreferences("preferences", Context.MODE_PRIVATE)
                    val editor = preference.edit()
                    editor.putString("userName", userName.toString())
                    editor.apply()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Sorry there was an error on your login, change username/password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}