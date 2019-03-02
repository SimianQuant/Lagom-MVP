package simianquant.mood

import com.lightbend.lagom.scaladsl.api._
import concurrent.Future

final class MoodServiceImpl extends MoodService {

  override final def greet: ServiceCall[String, String] = ServiceCall {name =>
    Future.successful(s"hello $name")
  }
}