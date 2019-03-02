package simianquant.mood

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.client.ConfigurationServiceLocatorComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

abstract class MoodApplication(context: LagomApplicationContext)
    extends LagomApplication(context)
    with AhcWSComponents {
  override lazy val lagomServer = serverFor[MoodService](wire[MoodServiceImpl])
}

abstract class MoodApplicationLoader extends LagomApplicationLoader {
  override def loadDevMode(ctx: LagomApplicationContext) = new MoodApplication(ctx) with LagomDevModeComponents
  override def describeService = Some(readDescriptor[MoodService])
}

class MoodApplicationLoaderDev extends MoodApplicationLoader {
  override def load(ctx: LagomApplicationContext) = new MoodApplication(ctx) {
    override def serviceLocator = ServiceLocator.NoServiceLocator
  }
}

final class MoodApplicationStatic(ctx: LagomApplicationContext)
    extends MoodApplication(ctx)
    with ConfigurationServiceLocatorComponents

class MoodApplicationLoaderStatic extends MoodApplicationLoader {
  override def load(ctx: LagomApplicationContext) = new MoodApplicationStatic(ctx)
}
