Building the Service
===

Since we are focused on wiring together the subcomponents of the application rather than on implementing the functionality, the service is minimalist, equivalent in functionality to "Hello World". 

## Defining the API

At this stage, the service will simply greet a user. To define an API,

1. The trait should extend the `Service` trait of the Lagom API
1. Define one or more functions that return a `ServiceCall[A, B]`. The first type parameter of the `ServiceCall` is the type of request object, and the second is type of the response object
1. Impelment the `descriptor` method, which defines metadata that is used if an external (i.e. non Lagom) service is consuming your service. At this stage, this is essentially some boiletplate code that needs to be written. 

The code for the service is:

```scala
package simianquant.mood

import com.lightbend.lagom.scaladsl.api._

trait MoodService extends Service {

  def greet: ServiceCall[String, String]

  override def descriptor = {
    import Service._
    named("moodservice")
      .withCalls(
        pathCall("/api/greet", greet)
      )
      .withAutoAcl(true)
  }
}
```
