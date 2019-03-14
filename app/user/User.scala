package user

import play.api.libs.json.{Format, Json}

case class User (
  id: Long,
  name: String,
  surname: String,
  email: String
)

object User{
  implicit val format = Json.format[User]
}
