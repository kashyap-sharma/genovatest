package kashyap.genovatest.cusfo.carous

/**
 * Author:  Kashyap Mudgal
 * Date:    12/22/17
 * Email:   kashyapboon@gmail.com
 */

interface CarouselLazyLoadListener {

    fun onLoadMore(page: Int, totalItemsCount: Int, view: CarouselView)

}
