package desarrollomobile.tiendadeclases.tiendadeclases.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import desarrollomobile.tiendadeclases.tiendadeclases.Adapters.SlideAdapter
import desarrollomobile.tiendadeclases.tiendadeclases.R

class OnBoardingActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var mPager: ViewPager
    var layouts: IntArray = intArrayOf(R.layout.first_tutorial, R.layout.second_tutorial,
            R.layout.third_tutorial, R.layout.final_tutorial)
    lateinit var dotsLayout: LinearLayout
    lateinit var dots: Array<ImageView>
    lateinit var mAdapter: PagerAdapter
    lateinit var btnNext: Button
    lateinit var btnSkip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        this.setContentView(R.layout.on_boarding)

        mPager = findViewById(R.id.slideViewPager)
        mAdapter = SlideAdapter(layouts, this)
        mPager.adapter = mAdapter
        dotsLayout = findViewById(R.id.dotsLayout)
        btnSkip = findViewById(R.id.btnSkip)
        btnNext = findViewById(R.id.btnNext)

        btnSkip.setOnClickListener(this)
        btnNext.setOnClickListener(this)

        createDots(0)

        mPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                createDots(position)

                if (position == layouts.size - 1) {
                    btnNext.visibility = View.INVISIBLE
                    btnSkip.visibility = View.INVISIBLE

                } else {
                    btnNext.visibility = View.VISIBLE
                    btnSkip.visibility = View.VISIBLE
                }
            }

        })

    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.btnSkip -> startActivity(Intent(baseContext, HomeActivity::class.java))
            R.id.btnNext -> {
                var nextSlide: Int = mPager.currentItem + 1

                if(nextSlide < layouts.size) {
                    mPager.setCurrentItem(nextSlide)
                }
            }
        }

    }

    fun createDots(position: Int) {

        dotsLayout.removeAllViews()
        dots = Array(layouts.size) { _ -> ImageView(this) }

        for (i in 0 until layouts.size) {
            dots[i] = ImageView(this)

            if (i == position) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots))
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots))
            }

            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(4, 0, 4, 0)
            dotsLayout.addView(dots[i], params)
        }
    }
}