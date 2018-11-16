package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import desarrollomobile.tiendadeclases.tiendadeclases.R

class LoginActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_login)

        val button = findViewById<Button>(R.id.button_login)

        button.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}