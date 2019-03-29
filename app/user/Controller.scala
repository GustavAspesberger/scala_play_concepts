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

  def get(id: Long) = Action { _ =>
    log.info(s"Controllerlayer => (get - id: $id) => Servicelayer")
    val x = service.get(id)
    log.info(s"Service => $x => Controller")
    Ok(Json.toJson(x))
  }

  def list()  = Action  {
    log.info("Controllerlayer => (list) => Servicelayer")
    val x = service.list()
    log.info(s"Service => $x => Controller")
    Ok(Json.toJson(x))
  }

  def create: Action[User] = Action(parse.json[User])  { request =>
    log.info(s"Controllerlayer => (create) => Servicelayer\nrequest.body => ${request.body}")
    service.create(request.body) match {
      case Right(x) => {
        log.info(s"ServiceLayer => Right($x) => Controller")
        Ok(Json.toJson(x))
      }
      case Left(x) => {
        log.info(s"ServiceLayer => Left($x) => Controller")
        handleError(x)
      }
    }
  }

  /*def update(id: Long): Action[UpdateRequest]  = Action(parse.json[UpdateRequest]) { request =>
    log.info(s"Controllerlayer => (update(id: $id)) => Servicelayer\nrequest.body => ${request.body}")
    service.update(id, request.body) match {
      case Right(()) => {
        log.info(s"Service => OK => Controller")
        Ok
      }
      case Left(x) => {
        log.info(s"Service => $x => Controller")
        handleError(x)
      }
    }
  }*/

  private def handleError(x: Error): Result = {
    x match {
      case Error.RESOURCE_NOT_FOUND => NotFound
      case Error.RESOURCE_CONFLICT => Conflict
      case x => BadRequest(x.toString)
    }
  }

}
