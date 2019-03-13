package test

import javax.inject.Inject
import play.Logger
import play.api.mvc._
import play.api.libs.json.Json

class Controller @Inject(
)(
  service: Service,
  cc: ControllerComponents
)() extends AbstractController(cc) {

  def get() = Action {

    Logger.info("get request in Controller")

    service.get() match {
      case z => Ok(Json.toJson(z))
      case _ => ???
    }
  }

  def find()  = Action  {
    service.find() match {
      case Some(z) => Ok(Json.toJson(z))
      case _ => ???
    }
  }

  def search()  = Action  {
    Ok(Json.toJson(service.search()))
  }

  def list()  = Action  {
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
