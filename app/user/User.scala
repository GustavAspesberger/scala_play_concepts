package user

import play.api.libs.json.Json

case class User (
  id: Option[Long],
  name: String,
  surname: String,
  email: String
)

object User{
  implicit val format = Json.format[User]
}
