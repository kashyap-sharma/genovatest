package kashyap.genovatest

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRadioButton
import kashyap.genovatest.cusfo.ProximaNovaButton

class DetailsP : AppCompatActivity() {
    var r1: AppCompatRadioButton? = null
    var r2: AppCompatRadioButton? = null
    var r3: AppCompatRadioButton? = null
    var r4: AppCompatRadioButton? = null
    var add_to_cart: ProximaNovaButton? = null
    var wishlist: AppCompatImageView? = null
    var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_p)
        r1 = findViewById(R.id.r1)
        mContext = this
        add_to_cart = findViewById(R.id.add_to_cart)
        r2 = findViewById(R.id.r2)
        r3 = findViewById(R.id.r3)
        r4 = findViewById(R.id.r4)
        wishlist = findViewById(R.id.wishlist)
        add_to_cart?.setOnClickListener(View.OnClickListener {
            Static_Catelog.setStringProperty(mContext as DetailsP, "added", "yes")
            val intent = Intent(mContext, HomePage::class.java)
            intent.putExtra("go", "yes")
            startActivity(intent)
            finish()
        })
    }

    fun onRadio(view: View) {
        val isSelected = (view as AppCompatRadioButton).isChecked
        when (view.getId()) {
            R.id.r1 -> if (isSelected) {
                r1!!.setTextColor(Color.WHITE)
                r2!!.setTextColor(Color.BLACK)
                r3!!.setTextColor(Color.BLACK)
                r4!!.setTextColor(Color.BLACK)
            }
            R.id.r2 -> if (isSelected) {
                r1!!.setTextColor(Color.BLACK)
                r2!!.setTextColor(Color.WHITE)
                r3!!.setTextColor(Color.BLACK)
                r4!!.setTextColor(Color.BLACK)
            }
            R.id.r3 -> if (isSelected) {
                r1!!.setTextColor(Color.BLACK)
                r2!!.setTextColor(Color.BLACK)
                r3!!.setTextColor(Color.WHITE)
                r4!!.setTextColor(Color.BLACK)
            }
            R.id.r4 -> if (isSelected) {
                r1!!.setTextColor(Color.BLACK)
                r2!!.setTextColor(Color.BLACK)
                r3!!.setTextColor(Color.BLACK)
                r4!!.setTextColor(Color.WHITE)
            }
        }
    }

    fun setImageLike(view: View) {
        val isSelected = wishlist!!.tag == "hi"
        when (view.id) {
            R.id.wishlist -> if (isSelected) {
                wishlist!!.setImageResource(R.drawable.ic_heart_unfilled)
                wishlist!!.tag = "bi"
                Toast.makeText(this@DetailsP, "Removed from wishlist.", Toast.LENGTH_SHORT).show()
            } else {
                wishlist!!.setImageResource(R.drawable.ic_heart_filled)
                wishlist!!.tag = "hi"
                Toast.makeText(this@DetailsP, "Added to wishlist.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}