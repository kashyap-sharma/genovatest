package kashyap.genovatest.cusfo

/**
 * Created by Kashyap on 03/14/16.
 */

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import kashyap.genovatest.R
import kashyap.genovatest.cusfo.FontCache




class ProximaNovaInputLayout : TextInputLayout {


    constructor(context: Context) : super(context) {
        if (isInEditMode) {
            return
        }
        val tf = FontCache["fonts/ProximaNovaRegular.otf", context]
        if (tf != null) {
            this.setTypeface(tf)
        }
    }

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val tf: Typeface?
        val a = context.obtainStyledAttributes(attrs,
                R.styleable.textfontstyle, 0, 0)
        var font_name = a.getString(R.styleable.textfontstyle_fontname)
        a.recycle()
        if (font_name == null) {
            font_name = "ll"
            //ProximaNovaBold.otf
            //ProximaNovaThin.otf
            //ProximaNovaBlack.otf
            //ProximaNovaRegular.otf
        }
        tf = when (font_name) {
            "bold" -> FontCache["fonts/ProximaNovaBold.otf", context]
            "light" -> FontCache["fonts/ProximaNovaThin.otf", context]
            "medium" -> FontCache["fonts/ProximaNovaBlack.otf", context]
            "heavy" -> FontCache["fonts/ProximaNovaExtrabold.otf", context]
            else -> FontCache["fonts/ProximaNovaRegular.otf", context]
        }

        if (tf != null) {
            this.setTypeface(tf)
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val tf = FontCache["fonts/ProximaNovaRegular.otf", context]
        if (tf != null) {
            this.setTypeface(tf)

        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

}