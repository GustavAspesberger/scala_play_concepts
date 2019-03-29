package user

sealed trait Error

object Error {

  case class BAD_REQUEST(msg: String) extends Error

  final object RESOURCE_NOT_FOUND extends Error

  final object RESOURCE_CONFLICT extends Error

}

