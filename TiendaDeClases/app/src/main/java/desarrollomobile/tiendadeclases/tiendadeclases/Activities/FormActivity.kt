package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import desarrollomobile.tiendadeclases.tiendadeclases.R

class FormActivity : AppCompatActivity() {
    private final var subCategoria : String ?= null ;

    override fun onCreate(savedInstanceState: Bundle?) {
        this.subCategoria = intent.getStringExtra("subcategoria")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_formulario)


    }

    public final fun getSubCategoria(): String? {
        return this.subCategoria;
    }
}
