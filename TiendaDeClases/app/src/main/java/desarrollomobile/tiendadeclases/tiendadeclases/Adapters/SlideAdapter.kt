package desarrollomobile.tiendadeclases.tiendadeclases.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.HomeActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.LoginActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.ProfileActivity
import desarrollomobile.tiendadeclases.tiendadeclases.Activities.RegisterActivity
import desarrollomobile.tiendadeclases.tiendadeclases.R

class SlideAdapter: PagerAdapter {

    lateinit var layouts: IntArray
    lateinit var inflater: LayoutInflater
    lateinit var con: Context

    constructor(layouts: IntArray, con: Context) : super() {
        this.layouts = layouts
        this.con = con
        inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return layouts.size
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var view: View = inflater.inflate(layouts[position], container, false)
        if (position == layouts.size - 1){

            val btnInvited = view.findViewById(R.id.Tutorial6_button) as TextView
            val btnRegister = view.findViewById(R.id.Tutorial5_button) as Button
            val btnLogin = view.findViewById(R.id.Tutorial4_button) as Button

            btnInvited.setOnClickListener {
                con.startActivity(Intent(con, HomeActivity::class.java))
            }

            btnLogin.setOnClickListener {
                con.startActivity(Intent(con, LoginActivity::class.java))
            }

            btnRegister.setOnClickListener {
                con.startActivity(Intent(con, RegisterActivity::class.java))

            }



        }
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        var view: View = `object` as View
        container.removeView(view)
    }


}