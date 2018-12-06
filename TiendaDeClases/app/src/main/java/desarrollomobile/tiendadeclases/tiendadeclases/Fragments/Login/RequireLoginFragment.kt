package desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.LoginActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.RegisterActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.CategoriasFragment
import desarrollomobile.tiendadeclases.tiendadeclases.Preferences.PreferencesManager
import desarrollomobile.tiendadeclases.tiendadeclases.R

class RequireLoginFragment: Fragment() {

    private lateinit var mLoginButton: Button
    private lateinit var mRegisterButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_required_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLoginButton = view.findViewById(R.id.button_login)
        mRegisterButton = view.findViewById(R.id.register_button)

        mLoginButton.setOnClickListener {
            context!!.startActivity(Intent(context, LoginActivity::class.java))
        }
        mRegisterButton.setOnClickListener {
            context!!.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }
}