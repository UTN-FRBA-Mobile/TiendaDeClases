package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.Manifest.permission.*
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.google.android.gms.location.places.ui.PlacePicker
import java.io.ByteArrayOutputStream
import java.util.*

abstract class UserModifyActivity: AppCompatActivity() {

    val PICK_IMAGE = 100
    val PICK_LOCATION = 1


    fun displayDate(dateSetListener: DatePickerDialog.OnDateSetListener?) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(
                this,
                android.R.style.Theme_Material_Dialog_MinWidth,
                dateSetListener,
                year, month, day)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    fun dateModelate(year: Int, month: Int, day: Int, displayDate: TextView?) {
        var nextMonth = month
        nextMonth += 1

        val date = nextMonth.toString() + "/" + day + "/" + year
        displayDate?.text = date
    }

    fun startLocationActivity(context: Context) {
        if (hasPermissions(context, ACCESS_COARSE_LOCATION) && hasPermissions(context, ACCESS_FINE_LOCATION)) {
            val builder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this), PICK_LOCATION)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION), PICK_LOCATION);
        }
    }

    fun openGallery(context: Context?) {
        if(hasPermissions(context, READ_EXTERNAL_STORAGE)) {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE), PICK_IMAGE);
        }
    }


    fun imageToString(bitmap: Bitmap?): String? {

        val byteArray = ByteArrayOutputStream()
        if(bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 5, byteArray)
            return Base64.getEncoder().encodeToString(byteArray.toByteArray())
        }
        return null
    }


    fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PICK_IMAGE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    startActivityForResult(gallery, PICK_IMAGE)
                }
            PICK_LOCATION ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    val builder = PlacePicker.IntentBuilder()
                    startActivityForResult(builder.build(this), PICK_LOCATION)
                }
        }
    }


}