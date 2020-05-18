package kashyap.genovatest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.shuhart.stepview.StepView
import kashyap.genovatest.ui.main.SectionsPagerAdapter
import java.util.*

class CheckoutA : AppCompatActivity() {
    var steps: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val step_view = findViewById<StepView>(R.id.step_view)
        viewPager.adapter = sectionsPagerAdapter
        step_view.go(viewPager.currentItem + 1, true)
        Log.e("data", "data" + viewPager.currentItem + 1)
        steps.add("LOGIN")
        steps.add("SHIPPING")
        steps.add("PAYMENT")
        step_view.setSteps(steps)
        // TabLayout tabs = findViewById(R.id.tabs);
        //  tabs.setVisibility(View.GONE);

        // tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                step_view.go(position, true)
                Log.e("data", "data$position")
            }

            override fun onPageSelected(position: Int) {
                if (position == 1) {  // if you want the second page, for example
                    //Your code here
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}