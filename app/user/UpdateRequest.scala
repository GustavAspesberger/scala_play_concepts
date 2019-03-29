package user

import play.api.libs.json.Json

case class UpdateRequest(
  name: Option[String],
  surname: Option[String],
  email: Option[String]
)

object UpdateRequest {

  implicit val format = Json.format[UpdateRequest]

}
