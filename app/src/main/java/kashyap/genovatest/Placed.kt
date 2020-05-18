package kashyap.genovatest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Placed : AppCompatActivity() {
    var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placed)
        mContext = this
        findViewById<View>(R.id.backhome).setOnClickListener {
            Static_Catelog.setStringProperty(mContext as Placed, "added", "no")
            startActivity(Intent(mContext, HomePage::class.java))
            finish()
        }
    }
}