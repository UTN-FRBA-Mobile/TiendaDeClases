package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.CategoriasFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.ClassesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.MessagesFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.Messages.Message
import desarrollomobile.tiendadeclases.tiendadeclases.Messages.MessagesApi
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.classes.Api
import desarrollomobile.tiendadeclases.tiendadeclases.classes.Class
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeActivity : AppCompatActivity(), ClassesFragment.OnListFragmentInteractionListener, MessagesFragment.OnListFragmentInteractionListener {

    lateinit var mPreferencesManager: PreferencesManager

    var classesList: MutableList<Class> = ArrayList<Class>()
    var markedClassesList: MutableList<Class> = ArrayList<Class>()
    var payedClassesList: MutableList<Class> = ArrayList<Class>()
    var scheduledClassesList: MutableList<Class> = ArrayList<Class>()
    var messagesList: MutableList<Message> = ArrayList<Message>()
    var messagesClassesList: MutableList<Class> = ArrayList<Class>()

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

        mPreferencesManager = PreferencesManager(this)

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
                            markedClassesList = classesList.filter{c -> c.status == 1}.toMutableList()
                            payedClassesList = classesList.filter{c -> c.status == 2}.toMutableList()
                            scheduledClassesList = classesList.filter{c -> c.status == 3}.toMutableList()
                        },
                        { error ->
                            Toast.makeText(this, "No se encontraron clases! " + error, Toast.LENGTH_LONG).show()
                        }
                )
        disposable = MessagesApi.create().getMessages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            messagesList = result.messages;
                        },
                        { error ->
                            Toast.makeText(this, "No se encontraron mensajes! " + error, Toast.LENGTH_LONG).show()
                        }
                )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val firstStart = mPreferencesManager.getStringPreference("userName")

        if(firstStart != "") {
            menuInflater.inflate(R.menu.profile, menu)
        } else {
            menuInflater.inflate(R.menu.not_signed, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.menu_logout -> {
                mPreferencesManager.setStringPreference("userName", "")
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            R.id.menu_signin -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.menu_signup -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
