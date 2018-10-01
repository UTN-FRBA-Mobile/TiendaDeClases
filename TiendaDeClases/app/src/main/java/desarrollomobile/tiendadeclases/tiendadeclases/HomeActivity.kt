package desarrollomobile.tiendadeclases.tiendadeclases

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

class HomeActivity : AppCompatActivity(), CategoriesFragment.OnFragmentInteractionListener, ClassesFragment.OnFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_categories -> {
                supportFragmentManager.beginTransaction().replace(R.id.HomeFrame, CategoriesFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_classes -> {
                supportFragmentManager.beginTransaction().replace(R.id.HomeFrame, ClassesFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_messages -> {
                supportFragmentManager.beginTransaction().replace(R.id.HomeFrame, MessagesFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var navigation = findViewById<BottomNavigationView>(R.id.navigation)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
