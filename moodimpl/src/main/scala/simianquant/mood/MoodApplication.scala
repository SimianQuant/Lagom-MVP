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
  override final lazy val lagomServer = serverFor[MoodService](wire[MoodServiceImpl])
}

abstract class MoodApplicationLoader extends LagomApplicationLoader {
  override final def loadDevMode(ctx: LagomApplicationContext) = new MoodApplication(ctx) with LagomDevModeComponents
  override final def describeService = Some(readDescriptor[MoodService])
}

final class MoodApplicationLoaderDev extends MoodApplicationLoader {
  override final def load(ctx: LagomApplicationContext) = new MoodApplication(ctx) {
    override def serviceLocator = ServiceLocator.NoServiceLocator
  }
}

final class MoodApplicationStatic(ctx: LagomApplicationContext)
    extends MoodApplication(ctx)
    with ConfigurationServiceLocatorComponents

final class MoodApplicationLoaderStatic extends MoodApplicationLoader {
  override final def load(ctx: LagomApplicationContext) = new MoodApplicationStatic(ctx)
}
