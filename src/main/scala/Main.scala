import java.io.File

import com.sksamuel.scrimage.Filter
import com.sksamuel.scrimage.filter.{ContrastFilter, GrayscaleFilter, SobelsFilter, ThresholdFilter}
import com.sksamuel.scrimage.{Image, Pixel}
/**
  * Created by Jonathan on 10/05/2017.
  */
object Main extends App {
  // JFXApp  {

  /*stage = new PrimaryStage {
    title = "ScalaFX Hello World"
    width = 650
    height = 450
    scene = new Scene {
      fill = Black
      content = new HBox {
        //        padding = Insets(5)
        children = Seq(new Text {
          text = "Scala"
          style = "-fx-font-size: 100pt"
          fill = new LinearGradient(
            endX = 0,
            stops = Stops(PaleGreen, SeaGreen))
        }, new Text {
          text = "FX"
          style = "-fx-font-size: 100pt"
          fill = new LinearGradient(
            endX = 0,
            stops = Stops(Cyan, DodgerBlue))
          effect = new DropShadow {
            color = DodgerBlue
            radius = 25
            spread = 0.25
          }
        })
        effect = new Reflection {
          fraction = 0.5
          topOffset = -5.0
          bottomOpacity = 0.75
          input = new Lighting {
            light = new Light.Distant {
              elevation = 60
            }
          }
        }
      }
    }
  }*/

  val image = Image.fromFile(new File("ecran.png"))
  val out = new File("img2.png")

  image.filter(GrayscaleFilter)

  val contrasted = test(image)

  contrasted.output(new File("contrasted.png"))

  val thresholded = threshold(contrasted, 30, 60)

  thresholded.output(new File("thresholded.png"))

/*
  val grey = applyAndSave(image, GrayscaleFilter, "gray.png")

  //val contrasted = applyAndSave(grey, ContrastFilter.apply(0.5), "contrasted.png")

  val sobeled = applyAndSave(grey, SobelsFilter, "sobeled.png")

*/
  def test(img: Image): Image = {
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
    val res = img.map { case (x, y, pix) =>

      val newGrey = 255 * cumulatedHistogram(pix.red) / product
      Pixel.apply(newGrey, newGrey, newGrey, 255)
    }

    val resultHistogram = new Array[Int](256)

    res.foreach { case (_, _, pixel) =>
      resultHistogram(pixel.red) += 1
    }

    println(resultHistogram.mkString("-"))
    res
  }

  def threshold(img: Image, inf: Int, sup: Int): Image = {
    img.map { case (_, _, pixel) =>
      if (pixel.red > sup || pixel.red < inf) {
        Pixel.apply(0, 0, 0, 255)
      } else {
        Pixel.apply(255, 255, 255, 255)
      }
    }
  }

  def applyAndSave(img: Image, filter: Filter, outPath: String) = {
    val filtered = img.filter(filter)
    filtered.output(new File(outPath))

    filtered
  }
}
