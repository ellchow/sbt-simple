import scalaz._
import Scalaz._

import org.slf4j.LoggerFactory

object Main{
  def log = LoggerFactory.getLogger(getClass.getName)

  def main(args: Array[String]){
    "hello world!" |> println
    log info "some info statement..."
  }
}
