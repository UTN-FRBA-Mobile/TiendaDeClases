package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login.SplashFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager


class MainActivity : AppCompatActivity(), SplashFragment.OnFragmentInteractionListener {

    private lateinit var mPreferencesManager: PreferencesManager

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAnimatedFragment(SplashFragment())
        mPreferencesManager = PreferencesManager(this)

        val firstStart = mPreferencesManager.getBooleanPreference("firstStart")

        Handler().postDelayed(
                {
                    if(firstStart) {
                        mPreferencesManager.setBooleanPreference("firstStart", false)
                        startActivity(Intent(this, OnBoardingActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()

                    }
                }, 1500)
    }

    fun showAnimatedFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .commit()
    }

}
