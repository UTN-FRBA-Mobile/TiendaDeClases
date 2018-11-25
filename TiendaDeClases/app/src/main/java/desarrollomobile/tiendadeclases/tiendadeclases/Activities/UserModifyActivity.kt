package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.R
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import org.w3c.dom.Text
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


}