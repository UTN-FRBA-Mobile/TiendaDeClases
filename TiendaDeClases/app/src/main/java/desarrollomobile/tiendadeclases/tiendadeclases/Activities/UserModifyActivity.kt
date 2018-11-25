package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.R
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import org.w3c.dom.Text
import java.io.ByteArrayOutputStream
import java.util.*

abstract class UserModifyActivity: AppCompatActivity() {


    fun displayDate(dateSetListener: DatePickerDialog.OnDateSetListener?) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(
                this,
                R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener,
                year, month, day)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    fun dateModelate(year: Int, month: Int, day: Int, displayDate: TextView?) {
        var month = month
        month += 1

        val date = month.toString() + "/" + day + "/" + year
        displayDate?.text = date
    }

    fun startLocationActivity(request: Int) {
        val builder = PlacePicker.IntentBuilder()
        startActivityForResult(builder.build(this), request)
    }

    fun openGallery(image: Int) {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, image)
    }


    fun imageToString(bitmap: Bitmap?): ByteArray? {

        val byteArray = ByteArrayOutputStream()
        if(bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArray)
            return byteArray.toByteArray()
        }
        return null
    }


}