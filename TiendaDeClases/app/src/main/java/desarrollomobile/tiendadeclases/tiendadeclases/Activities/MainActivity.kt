package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login.LoginFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login.SplashFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login.Tutorial1Fragment


class MainActivity : AppCompatActivity(), SplashFragment.OnFragmentInteractionListener, Tutorial1Fragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAnimatedFragment(SplashFragment())

        var preference = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val firstStart = preference.getBoolean("firstStart", true)

        Handler().postDelayed(
                {
                    if(firstStart) {
                        /*val editor = preference.edit()
                        editor.putBoolean("firstStart", false)
                        editor.apply()*/
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
