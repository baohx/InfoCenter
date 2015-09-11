/**
 * Created by ba0hx on 2015/9/11.
 */

package Receptionist

import scala.util.matching.Regex
import akka.actor.{Props, Actor}
object GetHtml {
  def props(companyName: String, mat: Regex.Match):Props = Props(new GetHtml(companyName, mat))
}
class GetHtml(companyName: String, mat: Regex.Match) extends Actor {
  def receive = {
    case "analyze" =>
      println(s"Weixin:\t${companyName}\t${mat.group("title").replace("<em><!--red_beg-->", "").replace("<!--red_end--></em>","")}\thttp://weixin.sogou.com${mat.group("url").replace("&amp;","&")}")
    case _ =>
      println("Failed!")
  }
}