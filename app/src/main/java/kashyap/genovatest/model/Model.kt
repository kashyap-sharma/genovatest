package kashyap.genovatest.model

import java.util.*

class Model(@JvmField var type: Int, @JvmField var title: String, @JvmField var datas: ArrayList<HomeModel>) {

    companion object {
        const val banner = 0
        const val ListHorizSquare = 1
        const val banner1 = 2
        const val GridSquare = 3
        const val ListHorizBig = 4
        const val ListCarous = 5
        const val List2itemgrid = 6
    }

}