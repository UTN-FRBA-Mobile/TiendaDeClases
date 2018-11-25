package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.users.PasswordRequest
import desarrollomobile.tiendadeclases.tiendadeclases.users.UsersApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UpdatePasswordActivity: AppCompatActivity() {

    private lateinit var mPreferenceManager: PreferencesManager
    private lateinit var mButtonChangePassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        mPreferenceManager = PreferencesManager(this)

        mButtonChangePassword = findViewById(R.id.change_password_button)
        mButtonChangePassword.setOnClickListener{

            if (passwordChecks()) {
                val responsePut = UsersApiClient.getRetrofitClient().updatePassword(PasswordRequest(mPreferenceManager.getStringPreference("userName"),findViewById<EditText>(R.id.oldPassword_edit).text.toString(), findViewById<EditText>(R.id.newPassword_edit).text.toString()))
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
        }
    }

    fun passwordChecks(): Boolean {
        return findViewById<EditText>(R.id.newPassword_edit).text.toString() == findViewById<EditText>(R.id.repeatPassword_edit).text.toString()
    }

}