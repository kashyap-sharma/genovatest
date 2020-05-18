package kashyap.genovatest;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.shuhart.stepview.StepView;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kashyap.genovatest.ui.main.SectionsPagerAdapter;

public class CheckoutA extends AppCompatActivity {
    List<String> steps = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        StepView step_view = findViewById(R.id.step_view);
        viewPager.setAdapter(sectionsPagerAdapter);
        step_view.go(viewPager.getCurrentItem()+1,true);
        Log.e("data","data"+viewPager.getCurrentItem()+1);
        steps.add("LOGIN");
        steps.add("SHIPPING");
        steps.add("PAYMENT");
        step_view.setSteps(steps);
       // TabLayout tabs = findViewById(R.id.tabs);
      //  tabs.setVisibility(View.GONE);

       // tabs.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                step_view.go(position,true);
                Log.e("data","data"+position);
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){  // if you want the second page, for example
                    //Your code here
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}