package kashyap.genovatest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        Objects.requireNonNull(supportActionBar)?.hide()
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        String go = getIntent().getStringExtra("go");
//        navView.setSelectedItemId(R.id.navigation_cart);
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_gn, R.id.navigation_search, R.id.navigation_cart, R.id.navigation_navigation, R.id.navigation_profile)
                .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
        if (Static_Catelog.getStringProperty(this, "added").equals("yes", ignoreCase = true)) {
            navView.selectedItemId = R.id.navigation_cart
        }
    }
}