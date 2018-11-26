package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.Fragments.Home.FormularioFragment
import desarrollomobile.tiendadeclases.tiendadeclases.R

class FormActivity : AppCompatActivity() {
    private final var subCategoria : String ?= null ;


    override fun onCreate(savedInstanceState: Bundle?) {
        this.subCategoria = intent.getStringExtra("subcategoria")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val bundle = Bundle()
        bundle.putString("NAME_KEY", subCategoria)

        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        val myFragment = FormularioFragment()
        myFragment.setArguments(bundle)

        getSupportFragmentManager().beginTransaction().replace(R.id.formActivity, myFragment).commit();
    }

    public final fun getSubCategoria(): String? {
        return this.subCategoria;
    }
}
