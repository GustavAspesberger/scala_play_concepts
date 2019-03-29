package health

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class Controller @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def ping = Action(_ => Ok)
}
