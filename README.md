# play-request-tracer
A module for Play! framework that allows to trace a request flow easily.

# Description
When you are looking at the logs from an application serving several concurrent requests, it can be a pain to trace the flow of a request from its start to its end. This plugin helps you to trace a request flow easily.

This plugin works only when you are using Guice for dependency injection.

This is a very simple log produced by an example application:

```
[info] c.a.c.a.LoggingAction - PlayRequest(6037d497a8e6472a83798d19ccb8cf74) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.a.LoggingAction - PlayRequest(7666f2d20ba24fb9a997d162cf2aa94a) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(7666f2d20ba24fb9a997d162cf2aa94a) - Index called
[info] c.a.c.a.LoggingAction - PlayRequest(efc07cb351ac45e989644c034de12d2e) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(efc07cb351ac45e989644c034de12d2e) - Index called
[info] c.a.c.MyService - PlayRequest(7666f2d20ba24fb9a997d162cf2aa94a) - verifying something
[info] c.a.c.MyService - PlayRequest(efc07cb351ac45e989644c034de12d2e) - verifying something
[info] c.a.c.MyController - PlayRequest(6037d497a8e6472a83798d19ccb8cf74) - Index called
[info] c.a.c.a.LoggingAction - PlayRequest(d14a76c362f347e7997e9eb4b8bf7ebd) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(d14a76c362f347e7997e9eb4b8bf7ebd) - Index called
[info] c.a.c.MyService - PlayRequest(6037d497a8e6472a83798d19ccb8cf74) - verifying something
[info] c.a.c.MyService - PlayRequest(d14a76c362f347e7997e9eb4b8bf7ebd) - verifying something
[info] c.a.c.a.LoggingAction - PlayRequest(e904eea1057b4f11aaa16a6c32c91339) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(e904eea1057b4f11aaa16a6c32c91339) - Index called
[info] c.a.c.MyService - PlayRequest(e904eea1057b4f11aaa16a6c32c91339) - verifying something
[info] c.a.c.a.LoggingAction - PlayRequest(51cdab26396246d0b5e3af29c1ea1f59) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(51cdab26396246d0b5e3af29c1ea1f59) - Index called
[info] c.a.c.MyService - PlayRequest(51cdab26396246d0b5e3af29c1ea1f59) - verifying something
[info] c.a.c.a.LoggingAction - PlayRequest(3c3036197fa442aab2ddfbb91a20bd5c) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(3c3036197fa442aab2ddfbb91a20bd5c) - Index called
[info] c.a.c.a.LoggingAction - PlayRequest(a3f6e546284242c9a4bc1a5dbfd559bc) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(a3f6e546284242c9a4bc1a5dbfd559bc) - Index called
[info] c.a.c.MyService - PlayRequest(a3f6e546284242c9a4bc1a5dbfd559bc) - verifying something
[info] c.a.c.MyService - PlayRequest(3c3036197fa442aab2ddfbb91a20bd5c) - verifying something
```

As you can see, each log have a `PlayRequest` attached, which could be used to filter a request flow with a simple `grep` command:
- `grep "6037d497a8e6472a83798d19ccb8cf74" logs/application.log`
```
[info] c.a.c.a.LoggingAction - PlayRequest(6037d497a8e6472a83798d19ccb8cf74) - REQUEST:  localhost:9000 - GET / HTTP/1.1 127.0.0.1
[info] c.a.c.MyController - PlayRequest(6037d497a8e6472a83798d19ccb8cf74) - Index called
[info] c.a.c.MyService - PlayRequest(6037d497a8e6472a83798d19ccb8cf74) - verifying something
```

We could be more precise using `grep "PlayRequest(6037d497a8e6472a83798d19ccb8cf74)" logs/application.log`.

As you can see, this have removed all the noise from logs that are not related to the request that we selected.

# Usage

## Add Dependency
At this time, the plugin is not released to any repository, so you need to clone the project and run `sbt publishLocal` command to get the dependency installed locally.

Add this line to your `build.sbt`:
- `libraryDependencies += "com.alexitc" %% "play-request-tracer" % "0.1.0"`

## Create a custom router
This plugin uses request-scoped objects, you need to create a custom router like this:
```scala
package com.alexitc

import javax.inject.Inject

import com.alexitc.play.tracer.PlayRequestTracerRouter
import com.google.inject.Injector

class MyRouter @Inject()(injector: Injector)
    extends PlayRequestTracerRouter[router.Routes](injector)

```

Then, add it to your `application.conf` file with a line like this:
- `play.http.router = "com.alexitc.MyRouter"`

And that's it, now you can integrate the request logger.

## Integration

There are two predefined ways to integrate this plugin, both of them uses a `logger` value which uses `slf4j` to support any of its implementations.

### 1. Creating the logger manually.

In order to create the logger, you need a `PlayRequestId`, it could be easily injected by Guice once you follow the previous steps.

This is an example of a service using our request tracer:
```scala
import javax.inject.Inject

import com.alexitc.play.tracer.{PlayRequestId, PlayRequestTracerLoggerFactory}

class MyService @Inject()(implicit val requestId: PlayRequestId) {

  val logger = PlayRequestTracerLoggerFactory.getLogger(classOf[MyService])
 
  logger.info("It's working!")
 
  ...
}
```

### 2. Using the predefined trait.

This is an example of a controller using the predefined trait:
```scala
import javax.inject.Inject

import com.alexitc.play.tracer.{PlayRequestId, PlayRequestTracing}

class MyController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc)
    with PlayRequestTracing {
   
    ...
   
}
```

## Limitations

At this time, there are some limitations that you should be aware of.

### The request tracer doesn't work in filters.
Sadly, at this time there is no way to get request-scoped objects in filters, hence we can't create our request tracer.

In order to link a request with a log, we would need to log request information, to overcome to this limitation, you could use the action composition approach from Play! (see https://www.playframework.com/documentation/2.6.x/ScalaActionsComposition).
There is a demo of this approach (https://github.com/AlexITC/play-request-tracer/blob/master/examples/play-example/app/com/alexitc/controllers/actions/LoggingAction.scala) that you could modify to suit your needs.


### The `PlayRequestTracing` trait doesn't let you log on the class constructor.
While this is a very convenient way for integrating the plugin, you should avoid it if you plan to add complex logic in the class constructor.

### Guice dependency injection is required
At this time, there is no way to use the plugin without Guice dependency injection.


## Development
If you want to help growing the project, you are welcome to submit issues or pull requests, any contribution would be appreciated.
