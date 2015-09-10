/**
 * Created by ba0hx on 2015/8/25.
 */
package Receptionist

import akka.actor.{Props, Actor}
import scala.xml.Node

object GetRss {
  def props(companyName: String, node: Node):Props = Props(new GetRss(companyName, node))
}

class GetRss(companyName: String, node: Node)  extends Actor{
  val title = (node \ "title").map {case <title>{t}</title> => t; case _ => None}
  val link = (node \ "link").map {case <link>{t}</link> => t; case _ => None}
  val description = (node \ "description").map {case <description>{t}</description> => t; case _ => None}
  val pubDate = (node \ "pubDate").map {case <pubDate>{t}</pubDate> => t; case _ => None}
  val source = (node \ "source").map {case <source>{t}</source> => t; case _ => None}
  val author = (node \ "author").map {case <author>{t}</author> => t; case _ => None}
  //val newsContent = new NewsContent(companyName, title(0).toString(), link(0).toString(), description(0).toString(), pubDate(0).toString(), source(0).toString(), author(0).toString())
  def receive = {
    case "analyze" =>
      println(s"Baidu:\t${companyName}\t${title(0).toString()}\t${link(0).toString()}\t${author(0).toString()}\t${pubDate(0).toString()}\t${description(0).toString()}\t${source(0).toString()}")
    case _ =>
      println("Failed!")
  }
}
