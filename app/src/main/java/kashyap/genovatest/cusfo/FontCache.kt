package kashyap.genovatest.cusfo

import android.content.Context
import android.graphics.Typeface
import java.util.*


object FontCache {

    private val fontCache = Hashtable<String, Typeface>()

    operator fun get(name: String, context: Context): Typeface? {
        var tf: Typeface? = fontCache[name]
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.assets, name)
            } catch (e: Exception) {
                return null
            }

            fontCache[name] = tf
        }
        return tf
    }
}
