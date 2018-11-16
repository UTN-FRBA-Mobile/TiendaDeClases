package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.CategoriasFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ProfileFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ClassesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.MessagesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.classes.Api
import desarrollomobile.tiendadeclases.tiendadeclases.classes.Class
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeActivity : AppCompatActivity(), ClassesFragment.OnListFragmentInteractionListener, MessagesFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    var classesList: MutableList<Class> = ArrayList<Class>()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_categories -> {
                supportFragmentManager.beginTransaction().replace(R.id.HomeFrame, CategoriasFragment()).commit()
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

        var toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_categories

        var disposable: Disposable? = null
        disposable = Api.create().getClasses()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            classesList = result.classes
                        },
                        {
                            error ->
                            Toast.makeText(this, "No se encontraron clases! " + error, Toast.LENGTH_LONG).show() }
                )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var mMenuInflater = menuInflater
        mMenuInflater.inflate(R.menu.profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
