package desarrollomobile.tiendadeclases.tiendadeclases

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        Handler().postDelayed(
                {
                    setContentView(R.layout.fragment_tutorial1)
                },3500 )
    }
}
