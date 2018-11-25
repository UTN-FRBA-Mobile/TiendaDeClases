package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.users.User
import desarrollomobile.tiendadeclases.tiendadeclases.users.UsersApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity: AppCompatActivity() {

    private lateinit var mPreferencesManager: PreferencesManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPreferencesManager = PreferencesManager(this)

        setContentView(R.layout.fragment_login)

        val button = findViewById<Button>(R.id.button_login)

        button.setOnClickListener{
            val userName = findViewById<EditText>(R.id.text_usuario).text
            val password = findViewById<EditText>(R.id.text_contraseña).text

            val response = UsersApiClient.getRetrofitClient().loginUser(User(userName.toString(), password.toString(), "", "", "", null, null))
            response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{it ->
                if(it.status == 200) {
                    mPreferencesManager.setStringPreference("userName", userName.toString())

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Sorry there was an error on your login, change username/password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}