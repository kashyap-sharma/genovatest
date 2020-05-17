package kashyap.genovatest.cusfo.carous.helper

import android.content.res.Resources


/**
 * Author:  Kashyap Mudgal
 * Date:    2019-06-14
 * Email:   kashyapboon@gmail.com
 */

object ViewHelper {

    /**
     * @return screen width size
     */
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    /**
     * @return screen height size
     */
    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}