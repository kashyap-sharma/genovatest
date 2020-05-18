package kashyap.genovatest

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kashyap.genovatest.connect.ConnectivityReceiver
import kashyap.genovatest.connect.ConnectivityReceiver.ConnectivityReceiverListener

class Splash : AppCompatActivity(), ConnectivityReceiverListener {
    var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mContext = this
        Static_Catelog.setStringProperty(mContext as Splash, "added", "no")
        checkConnection()
        init()
    }

    private fun init() {
        val handler = Handler()
        handler.postDelayed({
            Intent(this@Splash, HomePage::class.java).putExtra("go", "no")
            startActivity(Intent(this@Splash, HomePage::class.java))
            finish()
        }, 3000)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    private fun checkConnection() {
        val isConnected: Boolean = ConnectivityReceiver.isConnected
        showSnack(isConnected)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(ConnectivityReceiver(), intentFilter)
        MyApplication.Companion.instance?.setConnectivityListener(this)
    }

    private fun showSnack(isConnected: Boolean) {
        if (isConnected) {
            findViewById<View>(R.id.animation_vie).visibility = View.GONE
            println("Gone")
        } else {
            findViewById<View>(R.id.animation_vie).visibility = View.VISIBLE
            println("NoGone")
        }
    }
}