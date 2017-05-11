/*import com.sksamuel.scrimage.{Filter, Image, Pixel, MutableAwtImage}

/**
  * Created by lecointe on 11/05/17.
  */
object OptimizeContrastFilter extends Filter {
  def apply(img: Image): Unit = {
    val histogram = new Array[Int](256)

    img.foreach { case (_, _, pixel) =>
      histogram(pixel.red) += 1
    }

    val cumulatedHistogram = new Array[Int](256)

    histogram.zipWithIndex.foreach { case (nbGreyForIdx, greyIdx) =>
      if (greyIdx == 0) {
        cumulatedHistogram(greyIdx) = nbGreyForIdx
      } else {
        cumulatedHistogram(greyIdx) = cumulatedHistogram(greyIdx - 1) + nbGreyForIdx
      }
    }

    val product = img.width * img.height
    val res = img.mapInPlace { case (x, y, pix) =>

      val newGrey = 255 * cumulatedHistogram(pix.red) / product
      Pixel.apply(newGrey, newGrey, newGrey, 255)
    }

    println(res)
  }

}
*/