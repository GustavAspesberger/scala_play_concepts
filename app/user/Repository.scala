package user

import java.sql.Connection

import anorm._
import anorm.Macro.namedParser
import error.DBError
import javax.inject.Inject
import play.api.Logger

class Repository @Inject()()(){

  private val log = Logger("application")

  private val parser = namedParser[User]

  def get(id: Long)(implicit c: Connection) =  {
    log.info("Repolayer")
    val x = {
      SQL"""
         SELECT id, name, surname, email
         FROM project.users
         WHERE id = $id
        """.as(parser.singleOpt)
    }
    log.info(s"Repositorylayer => $x => Servicelayer")
    x
  }

  def list()(implicit c: Connection) = {
    log.info("Repolayer")
    val x = {
      SQL"""
         SELECT id, name, surname, email
         FROM project.users
        """.as(parser.*)
    }
    log.info(s"Repositorylayer => $x => Servicelayer")
    x
  }

  def create(user: User)(implicit c: Connection): Either[Error, User] = {
    log.info("Repositorylayer (create)")
    val x = {
      SQL"""
        INSERT INTO
          project.users (name, surname, email)
         VALUES
          (${user.name}, ${user.surname}, ${user.email})
        """.executeInsert(SqlParser.scalar[Long].single)
    }
    log.info(s"Repositorylayer => $x => Servicelayer")
    x match {
      case z => Right(user.copy(id = Option(z)))
      case _ => Left(Error.BAD_REQUEST("Problem writing that entry to db"))
    }
  }

  def update(id: Long, patch: UpdateRequest)(implicit c: Connection): Either[DBError, Unit] = {
    log.info("Repositorylayer (update)")
    val x = {
      SQL"""
        UPDATE
          project.users
        SET
          name = COALESCE(${patch.name}, name),
          surname = COALESCE(${patch.surname}, surname),
          email = COALESCE(${patch.email}, email)
         WHERE
          id = ${id}
        """.executeUpdate()
    }
    log.info(s"Repositorylayer => $x => Servicelayer")
    x match {
      case z if z > 0 => Right(Unit)
      case _ => Left(DBError.NOT_FOUND(s"id: $id not found"))
    }
  }

/*  def delete(id: Long)(implicit c: Connection): Either[DBError, Unit] = {

  }*/

}
