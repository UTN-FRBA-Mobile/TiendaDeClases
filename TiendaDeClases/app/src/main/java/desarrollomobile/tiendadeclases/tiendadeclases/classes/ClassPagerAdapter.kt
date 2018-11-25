package desarrollomobile.tiendadeclases.tiendadeclases.classes

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ClassPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MarkedClassesFragment()
            }
            1 -> {
                PayedClassesFragment()
            }
            else -> {
                return ScheduledClassesFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Seleccionadas"
            1 -> "Pagadas"
            else -> {
                return "Agendadas"
            }
        }
    }
}