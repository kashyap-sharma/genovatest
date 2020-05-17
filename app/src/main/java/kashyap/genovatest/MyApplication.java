package kashyap.genovatest;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;



import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import kashyap.genovatest.connect.ConnectivityReceiver;


public class MyApplication extends Application {
    Context context;
    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mInstance = this;

    }
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }


    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
