package kashyap.genovatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import kashyap.genovatest.cusfo.ProximaNovaButton;

public class DetailsP extends AppCompatActivity {
    AppCompatRadioButton r1, r2, r3, r4;
    ProximaNovaButton add_to_cart;
    AppCompatImageView wishlist;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_p);
        r1=findViewById(R.id.r1);
        mContext=this;
        add_to_cart=findViewById(R.id.add_to_cart);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
        wishlist=findViewById(R.id.wishlist);
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Static_Catelog.setStringProperty(mContext,"added","yes");
                Intent intent = new Intent(mContext, HomePage.class);
                intent.putExtra("go","yes");
                startActivity(intent);
                finish();

            }
        });

    }
    public void onRadio(View view){
        boolean isSelected=((AppCompatRadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.r1:
                if (isSelected){
                    r1.setTextColor(Color.WHITE);
                    r2.setTextColor(Color.BLACK);
                    r3.setTextColor(Color.BLACK);
                    r4.setTextColor(Color.BLACK);
                }
                break;
            case R.id.r2:
                if (isSelected){
                    r1.setTextColor(Color.BLACK);
                    r2.setTextColor(Color.WHITE);
                    r3.setTextColor(Color.BLACK);
                    r4.setTextColor(Color.BLACK);
                }
                break;
            case R.id.r3:
                if (isSelected){
                    r1.setTextColor(Color.BLACK);
                    r2.setTextColor(Color.BLACK);
                    r3.setTextColor(Color.WHITE);
                    r4.setTextColor(Color.BLACK);
                }
                break;
            case R.id.r4:
                if (isSelected){
                    r1.setTextColor(Color.BLACK);
                    r2.setTextColor(Color.BLACK);
                    r3.setTextColor(Color.BLACK);
                    r4.setTextColor(Color.WHITE);
                }
                break;
        }
    }

    public void setImageLike(View view){
        boolean isSelected= wishlist.getTag().equals("hi");
        switch (view.getId()){
            case R.id.wishlist:
                if (isSelected){
                 wishlist.setImageResource(R.drawable.ic_heart_unfilled);
                 wishlist.setTag("bi");
                    Toast.makeText(DetailsP.this, "Removed from wishlist.",Toast.LENGTH_SHORT).show();
                }
                else{ wishlist.setImageResource(R.drawable.ic_heart_filled);
                wishlist.setTag("hi");
                    Toast.makeText(DetailsP.this, "Added to wishlist.",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
