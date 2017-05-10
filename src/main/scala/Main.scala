import java.io.File

import com.sksamuel.scrimage.filter.{GrayscaleFilter, SobelsFilter, ThresholdFilter}
import com.sksamuel.scrimage.{Image, Pixel}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.effect._
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
/**
  * Created by Jonathan on 10/05/2017.
  */
object Main extends JFXApp  {

  stage = new PrimaryStage {
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
  }

  val image = Image.fromFile(new File("img.jpg"))
  val out = new File("img2.png")


  val pixel = Pixel.apply(0, 255, 0, 100)



  val blank = Image.filled(100, 100)

  blank.map { case (x, y, pix) =>
    Pixel(0, 255, 0, 255)
  }.output("green.png")

  val t = SobelsFilter
  val r = ThresholdFilter

  val s = ThresholdFilter.apply(128)

  //image.filter(GrayscaleFilter, SobelsFilter, s).output(out)



}
