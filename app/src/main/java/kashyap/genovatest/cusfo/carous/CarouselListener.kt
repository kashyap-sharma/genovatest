package kashyap.genovatest.cusfo.carous

/**
 * Author:  Kashyap Mudgal
 * Date:    12/22/17
 * Email:   kashyapboon@gmail.com
 */
interface CarouselListener {
    /**
     * @param position current position
     */
    fun onPositionChange(position: Int)

    /**
     * @param dx delta x
     * @param dy delta y
     */
    fun onScroll(dx: Int, dy: Int)
}
