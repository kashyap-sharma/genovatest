package kashyap.genovatest.model

import kashyap.genovatest.cusfo.carous.CarouselModel


class EmptySampleModel constructor(private val text: String) : CarouselModel() {

    fun getText(): String {
        return text
    }
}