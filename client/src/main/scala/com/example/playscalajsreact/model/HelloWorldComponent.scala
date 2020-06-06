package com.example.playscalajsreact.model

// import slinky.core.annotations.react
// import slinky.core.StatelessComponent
// import slinky.web.html.h1
import japgolly.scalajs.react._
// @react class HelloWorldComponentSlinky extends StatelessComponent {
//   case class Props(name: String, age: Int)

//   def render = {
//     h1(s"Hello ${props.name} ${props.age}")
//   }
// }

object HelloWorldSJSRComponent {
  import japgolly.scalajs.react.vdom.html_<^._
  case class Props(name: String, age: Int)

  private val component = ScalaComponent
    .builder[Props]("HelloWorldComponent")
    .render_P(p => {
      <.p(p.name + p.age)
    })
    .build

  def apply(name: String, age: Int) = {
    component(Props(name, age))
  }
}
