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