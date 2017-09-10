package com.alexitc.play.tracer

import com.google.inject.AbstractModule

class PlayRequestTracerModule extends AbstractModule {

  override def configure(): Unit = {
    val playRequestId = PlayRequestId.random

    bind(classOf[PlayRequestId])
        .toInstance(playRequestId)
  }
}
