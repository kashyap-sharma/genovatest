package kashyap.genovatest

import android.app.Application
import android.content.Context
import kashyap.genovatest.connect.ConnectivityReceiver
import kashyap.genovatest.connect.ConnectivityReceiver.ConnectivityReceiverListener

class MyApplication : Application() {
    var context: Context? = null
    override fun onCreate() {
        super.onCreate()
        context = this
        instance = this
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
    }

    fun setConnectivityListener(listener: ConnectivityReceiverListener?) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }

    companion object {
        val TAG = MyApplication::class.java.simpleName

        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }
}