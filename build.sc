// build.sc
import mill._, scalalib._

object Common extends ScalaModule
{ def scalaVersion = "3.1.0"
  def sources = T.sources(millSourcePath / os.up / "src")
}