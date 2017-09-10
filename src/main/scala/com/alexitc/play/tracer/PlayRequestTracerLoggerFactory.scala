package com.alexitc.play.tracer

import org.slf4j.{Logger, LoggerFactory}

/**
 * A utility class for creating <code>PlayRequestTracerLogger</code> loggers.
 *
 * It uses <code>org.slf4j.LoggerFactory</code> to be able to use different
 * logger implementations.
 *
 * The idea is to create a class in this way:
 * {{{
 *   class MyClass @Inject()(implicit val playRequestId: PlayRequestId) {
 *     val logger = PlayRequestTracerLoggerFactory.getLogger(this.getClass)
 *   }
 * }}}
 */
object PlayRequestTracerLoggerFactory {

  def getLogger(clazz: Class[_])(implicit playRequestId: PlayRequestId): Logger = {
    val logger = LoggerFactory.getLogger(clazz.getName)

    new PlayRequestTracerLogger(playRequestId, logger)
  }
}
