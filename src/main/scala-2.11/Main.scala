/**
 * Created by ba0hx on 2015/8/25.
 */
import akka.actor.{PoisonPill, ActorSystem, Props, Actor}
import scala.io.Source
import java.net.URLEncoder
import java.io.File
class Client extends Actor {
  import Receptionist._
  val path = new File("").getAbsolutePath
  val fileName = path + "\\NewsTheme.txt"
  //val fileName = "D:\\ScalaProject\\InfoCenter\\src\\main\\scala-2.11\\NewsTheme.txt"
  val companyNames = Source.fromFile(fileName, "utf-8").getLines.toList.map(_.replace("\n", ""))
  def receive = {
    case "StartBaidu" =>
      for (companyName <- companyNames) {
        context.actorOf(Receptionist.props(companyName, s"http://news.baidu.com/ns?word=title:${companyName}&tn=newsrss&sr=0&cl=2&rn=20&ct=0")) ! "getrss"
      }
    case "StartWeixin" =>
      for (companyName <- companyNames) {
        context.actorOf(Receptionist.props(companyName, s"http://weixin.sogou.com/weixin?query=${URLEncoder.encode(companyName,"UTF-8")}&type=2&page=1")) ! "gethtml"
      }
  }
}

object Main extends App {
  val system = ActorSystem("InfoCenter")
  val ic = system.actorOf(Props[Client], "ic")
  ic ! "StartBaidu"
  ic ! "StartWeixin"
  Thread.sleep(10000)
  ic ! PoisonPill
  Thread.sleep(10000)
  system.shutdown()
}