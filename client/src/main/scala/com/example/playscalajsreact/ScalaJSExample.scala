package com.example.playscalajsreact

import com.example.playscalajsreact.shared.SharedMessages
import org.scalajs.dom
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra._
import com.example.playscalajsreact.model.HelloWorldSJSRComponent
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.extra.router.StaticDsl.Route


object ScalaJSExample {

  def main(args: Array[String]): Unit = {

    sealed trait Page
    case object Home extends Page
    case object Hello extends Page
    case class Person(user: String) extends Page
    case class ID(id: Int) extends Page

    case class Menu(name: String, route: Page)

    val mainMenu = Vector(
      Menu("Home", Home),
      Menu("Hello", Hello)
    )

    def layout(c: RouterCtl[Page], r: Resolution[Page]) =
      <.ul(
        // c.link(Home)("Home"),
        // c.link(Hello)("Hello"),
        mainMenu.toTagMod { item =>
          {
            <.li(
              ^.key := item.name,
              item.name,
              c setOnClick item.route
            )
          }
        },
        r.render()
      )

    val x = <.ol(
      ^.id := "my-list",
      ^.lang := "en",
      ^.margin := 8.px,
      <.li("Item 1"),
      <.li("Item 2"),
      HelloWorldSJSRComponent("Hello", 18)
    )

    val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
      import dsl._
      import japgolly.scalajs.react.vdom.Implicits._

      case class Item(category: String, itemId: java.util.UUID) extends Page
      // val r =
      //   ("category" / string("[a-z]+") / "item" / int.caseClassDebug[ID])

      // FIXME uncomment this block to get an error - Companion object not found for class Product
      // case class Product(category: Int, item: Int) extends Page
      // val r: Route[Product] = ("cat" / int / "item" / int).caseClass[Product]

      // val testRoute =
      //   ("user" / string("[a-z0-9]{1,20}") / "age" / int).pmap {
      //     case (a, b) => {}
      //   }
      (emptyRule
        | staticRoute(root, Home) ~> render(x)
        // FIXME uncomment this block to get an error - Companion object not found for class Person
        // | dynamicRouteCT("user" / string("[a-z0-9]{1,20}").caseClass[Person]) ~> dynRender(
        //   (page: Person) => {
        //     HelloWorldSJSRComponent(page.user, 0)
        //   }
        // )
        | staticRoute("#hello", Hello) ~> render(<.div("TODO"))
        | staticRedirect("#hey") ~> redirectToPage(Hello)(SetRouteVia.HistoryReplace))
        .notFound(redirectToPage(Home)(SetRouteVia.HistoryReplace))
        .renderWith(layout)
    }

    // x.renderIntoDOM(dom.document.getElementById("app"))
    val router = Router(BaseUrl.fromWindowOrigin / "index.html", routerConfig)
    router().renderIntoDOM(dom.document.getElementById("app"))

    dom.document.getElementById("scalajsShoutOut").textContent =
      SharedMessages.itWorks
  }
}
