package com.alexitc.play.tracer

import javax.inject.Inject

import org.slf4j.{Logger, Marker}

/**
 * A proxy class to <code>org.slf4j.Logger</code> that includes PlayRequestId in every log message.
 *
 * @param playRequestId the play request id to include in every log message
 * @param logger the logger to proxy the logs
 */
class PlayRequestTracerLogger @Inject()(playRequestId: PlayRequestId, logger: Logger) extends Logger {
  require(playRequestId != null, "The playRequestId should not be null")

  private def withRequestId(msg: String) = {
    s"$playRequestId - $msg"
  }

  override def getName = logger.getName

  override def isWarnEnabled = logger.isWarnEnabled

  override def isWarnEnabled(marker: Marker) = logger.isWarnEnabled(marker)

  override def error(msg: String) = logger.error(withRequestId(msg))

  override def error(format: String, arg: scala.Any) = logger.error(withRequestId(format), arg)

  override def error(format: String, arg1: scala.Any, arg2: scala.Any) = logger.error(withRequestId(format), arg1, arg2)

  override def error(format: String, arguments: AnyRef*) = logger.error(withRequestId(format), arguments)

  override def error(msg: String, t: Throwable) = logger.error(withRequestId(msg), t)

  override def error(marker: Marker, msg: String) = logger.error(marker, withRequestId(msg))

  override def error(marker: Marker, format: String, arg: scala.Any) = logger.error(marker, withRequestId(format), arg)

  override def error(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any) = logger.error(marker, withRequestId(format), arg1, arg2)

  override def error(marker: Marker, format: String, arguments: AnyRef*) = logger.error(marker, withRequestId(format), arguments)

  override def error(marker: Marker, msg: String, t: Throwable) = logger.error(marker, withRequestId(msg), t)

  override def trace(msg: String) = logger.trace(withRequestId(msg))

  override def trace(format: String, arg: scala.Any) = logger.trace(withRequestId(format), arg)

  override def trace(format: String, arg1: scala.Any, arg2: scala.Any) = logger.trace(withRequestId(format), arg1, arg2)

  override def trace(format: String, arguments: AnyRef*) = logger.trace(withRequestId(format), arguments)

  override def trace(msg: String, t: Throwable) = logger.trace(withRequestId(msg), t)

  override def trace(marker: Marker, msg: String) = logger.trace(marker, withRequestId(msg))

  override def trace(marker: Marker, format: String, arg: scala.Any) = logger.trace(marker, withRequestId(format), arg)

  override def trace(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any) = logger.trace(marker, withRequestId(format), arg1, arg2)

  override def trace(marker: Marker, format: String, argArray: AnyRef*) = logger.trace(marker, withRequestId(format), argArray)

  override def trace(marker: Marker, msg: String, t: Throwable) = logger.trace(marker, withRequestId(msg), t)

  override def isInfoEnabled = logger.isInfoEnabled

  override def isInfoEnabled(marker: Marker) = logger.isInfoEnabled(marker)

  override def isErrorEnabled = logger.isErrorEnabled

  override def isErrorEnabled(marker: Marker) = logger.isErrorEnabled(marker)

  override def isTraceEnabled = logger.isTraceEnabled

  override def isTraceEnabled(marker: Marker) = logger.isTraceEnabled(marker)

  override def info(msg: String) = logger.info(withRequestId(msg))

  override def info(format: String, arg: scala.Any) = logger.info(withRequestId(format), arg)

  override def info(format: String, arg1: scala.Any, arg2: scala.Any) = logger.info(withRequestId(format), arg1, arg2)

  override def info(format: String, arguments: AnyRef*) = logger.info(withRequestId(format), arguments)

  override def info(msg: String, t: Throwable) = logger.info(withRequestId(msg), t)

  override def info(marker: Marker, msg: String) = logger.info(marker, withRequestId(msg))

  override def info(marker: Marker, format: String, arg: scala.Any) = logger.info(marker, withRequestId(format), arg)

  override def info(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any) = logger.info(marker, withRequestId(format), arg1, arg2)

  override def info(marker: Marker, format: String, arguments: AnyRef*) = logger.info(marker, withRequestId(format), arguments)

  override def info(marker: Marker, msg: String, t: Throwable) = logger.info(marker, withRequestId(msg), t)

  override def debug(msg: String) = logger.debug(withRequestId(msg))

  override def debug(format: String, arg: scala.Any) = logger.debug(withRequestId(format), arg)

  override def debug(format: String, arg1: scala.Any, arg2: scala.Any) = logger.debug(withRequestId(format), arg1, arg2)

  override def debug(format: String, arguments: AnyRef*) = logger.debug(withRequestId(format), arguments)

  override def debug(msg: String, t: Throwable) = logger.debug(withRequestId(msg), t)

  override def debug(marker: Marker, msg: String) = logger.debug(marker, withRequestId(msg))

  override def debug(marker: Marker, format: String, arg: scala.Any) = logger.debug(marker, withRequestId(format), arg)

  override def debug(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any) = logger.debug(marker, withRequestId(format), arg1, arg2)

  override def debug(marker: Marker, format: String, arguments: AnyRef*) = logger.debug(marker, withRequestId(format), arguments)

  override def debug(marker: Marker, msg: String, t: Throwable) = logger.debug(marker, withRequestId(msg), t)

  override def warn(msg: String) = logger.warn(withRequestId(msg))

  override def warn(format: String, arg: scala.Any) = logger.warn(withRequestId(format), arg)

  override def warn(format: String, arguments: AnyRef*) = logger.warn(withRequestId(format), arguments)

  override def warn(format: String, arg1: scala.Any, arg2: scala.Any) = logger.warn(withRequestId(format), arg1, arg2)

  override def warn(msg: String, t: Throwable) = logger.warn(withRequestId(msg), t)

  override def warn(marker: Marker, msg: String) = logger.warn(marker, withRequestId(msg))

  override def warn(marker: Marker, format: String, arg: scala.Any) = logger.warn(marker, withRequestId(format), arg)

  override def warn(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any) = logger.warn(marker, withRequestId(format), arg1, arg2)

  override def warn(marker: Marker, format: String, arguments: AnyRef*) = logger.warn(marker, withRequestId(format), arguments)

  override def warn(marker: Marker, msg: String, t: Throwable) = logger.warn(marker, withRequestId(msg), t)

  override def isDebugEnabled = logger.isDebugEnabled

  override def isDebugEnabled(marker: Marker) = logger.isDebugEnabled(marker)
}
