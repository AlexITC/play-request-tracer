package com.alexitc.play.tracer

import javax.inject.Inject

import com.alexitc.play.api.inject.guice.RequestScopedRouter
import com.google.inject.Injector
import play.api.routing.Router

import scala.reflect.ClassTag

/**
 * The router used for creating request-scoped <code>PlayRequestId</code> objects.
 *
 * The way for using this class is to create a class extending it like this:
 * {{{
 *   package yourpackage
 *
 *   import javax.inject.Inject
 *
 *   import com.alexitc.play.tracer.PlayRequestTracerRouter
 *   import com.google.inject.Injector
 *
 *   class MyRouter @Inject()(injector: Injector)
 *       extends PlayRequestTracerRouter[router.Routes](injector)
 * }}}
 *
 * Then, add the following line to your <code>application.conf</code> file:
 * {{{
 *   com = play.http.router = "yourpackage.MyRouter"
 * }}}
 *
 * The class should be created because <code>router.Routes</code> is generated
 * by Play! at compile time.
 *
 * @param injector the guice injector
 * @tparam T the type of the router to use, usually <code>router.Routes</code>
 */
class PlayRequestTracerRouter[T <: Router] @Inject()(injector: Injector)(
    implicit routerClassTag: ClassTag[T])
    extends RequestScopedRouter[T](
      injector,
      modules = Seq(classOf[PlayRequestTracerModule])
    )
