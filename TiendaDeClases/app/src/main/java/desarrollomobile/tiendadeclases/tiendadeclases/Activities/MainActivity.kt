package desarrollomobile.tiendadeclases.tiendadeclases.Activities

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

        Handler().postDelayed(
                {
                    showAnimatedFragment(Tutorial1Fragment())
                }, 3500)
    }

    fun showAnimatedFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .commit()
    }

}
