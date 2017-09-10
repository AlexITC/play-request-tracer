package com.alexitc.play.tracer

import javax.inject.Inject

/**
 * A trait having a <code>PlayRequestTracerLogger</code>.
 *
 * The idea is to create a class in this way:
 * {{{
 *   class MyClass extends PlayRequestTracing
 * }}}
 *
 * Or like this:
 * {{{
 *   class MyClass extends MySuperClass with PlayRequestTracing
 * }}}
 */
trait PlayRequestTracing {

  @Inject()
  var playRequestId: PlayRequestId = _

  lazy val logger = PlayRequestTracerLoggerFactory.getLogger(this.getClass)(playRequestId)
}
