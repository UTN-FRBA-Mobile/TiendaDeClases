package desarrollomobile.tiendadeclases.tiendadeclases

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        Handler().postDelayed(
                {
                    setContentView(R.layout.fragment_tutorial1)

                    val button = findViewById<Button>(R.id.Tutorial4_button)
                    button.setOnClickListener{
                        setContentView(R.layout.fragment_fragment_login)
                    }
                },3500 )
    }



}
