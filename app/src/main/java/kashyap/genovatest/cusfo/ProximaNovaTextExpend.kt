package kashyap.genovatest.cusfo

/**
 * Created by Kashyap on 03/14/16.
 */

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kashyap.genovatest.R



open class ProximaNovaTextExpend : AppCompatTextView {


    constructor(context: Context) : super(context) {
        if (isInEditMode()) {
            return
        }
        val tf = Fontcache2.get("fonts/ProximaNovaRegular.otf", context)
        if (tf != null) {
            this.setTypeface(tf)
        }
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val tf: Typeface?
        @SuppressLint("CustomViewStyleable") val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.textfontstyle, 0, 0
        )
        var font_name = a.getString(R.styleable.textfontstyle_fontname)
        a.recycle()
        if (font_name == null) {
            font_name = "ll"
            //ProximaNovaBold.otf
            //ProximaNovaThin.otf
            //ProximaNovaBlack.otf
            //
            //ProximaNovaRegular.otf
        }
        when (font_name) {
            "bold" -> tf = Fontcache2.get("fonts/ProximaNovaBold.otf", context)
            "light" -> tf = Fontcache2.get("fonts/ProximaNovaThin.otf", context)
            "medium" -> tf = Fontcache2.get("fonts/ProximaNovaBlack.otf", context)
            "heavy" -> tf = Fontcache2.get("fonts/ProximaNovaExtrabold.otf", context)
            else -> tf = Fontcache2.get("fonts/ProximaNovaRegular.otf", context)
        }

        if (tf != null) {
            this.typeface = tf
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val tf = Fontcache2.get("fonts/ProximaNovaRegular.otf", context)
        if (tf != null) {
            this.typeface = tf

        }
    }

    protected override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}