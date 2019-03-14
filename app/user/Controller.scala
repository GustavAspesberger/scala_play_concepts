package user

import javax.inject.Inject
import play.api.Logger
import play.api.mvc._
import play.api.libs.json.Json

class Controller @Inject(
)(
  service: Service,
  cc: ControllerComponents
)() extends AbstractController(cc) {

  private val log = Logger("application")

  def get(name: String) = Action {
    log.info("Controllerlayer => (get) => Servicelayer")
    service.get(name) match {
      case Some(z) => {
        log.info(s"Service => $z => Controller")
        Ok(Json.toJson(z))
      }
      case _ => {
        log.info(s"Error 404 $name not found")
        NotFound
      }
    }
  }

  def find()  = Action  {
    service.find() match {
      case Some(z) => Ok(Json.toJson(z))
      case _ => ???
    }
  }

  def list()  = Action  {
    log.info("Controllerlayer => (list) => Servicelayer")
    Ok(Json.toJson(service.list()))
  }

  def create()  = Action  { request =>
    service.create()
    Created
  }

  def update()  = Action { request =>
    service.update()
    NoContent
  }

}
