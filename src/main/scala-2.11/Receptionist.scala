/**
 * Created by ba0hx on 2015/8/25.
 */
package Receptionist
import akka.actor.{Actor, Props}
import java.util.{Date, Locale}
import java.text.SimpleDateFormat
import scala.xml.XML
import scala.io.Source
import scala.util.matching.Regex
import scala.language.postfixOps


object Receptionist {
  def props(companyName: String, url: String):Props = Props(new Receptionist(companyName, url))
}

class Receptionist(companyName: String, url: String) extends Actor {
  def receive = {
    case "getrss" =>
      for (node <- XML.load(url) \ "channel" \ "item" if
      //new SimpleDateFormat("yyyy", Locale.US).format(new Date()) equals (node \ "pubDate").toString().substring(21, 25)) {
      new SimpleDateFormat("MMM yyyy", Locale.US).format(new Date()) equals (node \ "pubDate").toString().substring(17, 25)) {
        context.actorOf(GetRss.props(companyName, node)) ! "analyze"
      }
    case "gethtml" =>
      val megaURLMatcher = new Regex( """<div class="txt-box">\s*?<h4>\s*?<a target="_blank" href="(.*?)" .*?>(.*?)</a>\s*?</h4>""", "url", "title")
      (megaURLMatcher findAllIn Source.fromURL(url, "utf-8").getLines.mkString("") matchData).foreach(x => println(s"Weixin:\t${companyName}\t${x.group("title").replace("<em><!--red_beg-->", "").replace("<!--red_end--></em>","")}\thttp://weixin.sogou.com${x.group("url").replace("&amp;","&")}"))

  }
}
