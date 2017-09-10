package com.alexitc.controllers

import javax.inject.Inject

import com.alexitc.controllers.actions.LoggingAction
import com.alexitc.play.tracer.{PlayRequestId, PlayRequestTracerLoggerFactory, PlayRequestTracing}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MyController @Inject()(
    cc: ControllerComponents,
    loggingAction: LoggingAction,
    myService: MyService)
    extends AbstractController(cc)
    with PlayRequestTracing {


  def index() = loggingAction.async { implicit request: Request[AnyContent] =>
    logger.info("Index called")

    myService
        .verify()
        .map(_ => Ok("Done!"))
  }

}

class MyService @Inject()(implicit val requestId: PlayRequestId) {

  val logger = PlayRequestTracerLoggerFactory.getLogger(classOf[MyService])

  def verify() = Future {
    logger.info("verifying something")
  }
}
