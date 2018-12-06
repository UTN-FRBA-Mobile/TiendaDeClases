package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.CategoriasFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R

abstract class RequireLoginFragment: Fragment() {

    private lateinit var activityToPresent: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val pm = PreferencesManager(activity!!)

        if (pm.getStringPreference("userName") != "") {
            return viewToInflate(inflater, container, savedInstanceState)
        } else {
            return inflater.inflate(R.layout.fragment_required_log_in, container, false)
        }
    }

    abstract fun viewToInflate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
}