package sbtsimple

import com.typesafe.scalalogging.slf4j.Logging

import scalaz._
import Scalaz._

object Main extends Logging{
  def main(args: Array[String]){
    "hello world!" |> println
    logger info "some info statement..."
  }
}