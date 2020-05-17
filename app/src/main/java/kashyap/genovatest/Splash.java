package kashyap.genovatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import kashyap.genovatest.connect.ConnectivityReceiver;

public class Splash extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkConnection();
        init();
    }

    private void init() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, HomePage.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new ConnectivityReceiver(), intentFilter);
        MyApplication.getInstance().setConnectivityListener(this);

    }


    private void showSnack(boolean isConnected) {
        if (isConnected){
            findViewById(R.id.animation_vie).setVisibility(View.GONE);
            System.out.println("Gone");
        }else{
            findViewById(R.id.animation_vie).setVisibility(View.VISIBLE);
            System.out.println("NoGone");
        }
    }
}
