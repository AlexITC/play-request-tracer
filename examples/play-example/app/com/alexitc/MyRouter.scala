package com.alexitc

import javax.inject.Inject

import com.alexitc.play.tracer.PlayRequestTracerRouter
import com.google.inject.Injector

class MyRouter @Inject()(injector: Injector)
    extends PlayRequestTracerRouter[router.Routes](injector)
