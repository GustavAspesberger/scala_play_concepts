package user

import anorm._
import anorm.Macro.namedParser
import javax.inject.Inject
import play.api.Logger
import play.api.db.DBApi

class Repository @Inject()(
  dbApi: DBApi
)(){

  private val log = Logger("application")
  private val db = dbApi.database("default")

  private val parser = namedParser[User]

  def get(name: String) =  {
    log.info("Repolayer")
    val x = db.withConnection( implicit c =>
      SQL"""
         SELECT id, name, surname, email
         FROM users
         where name = $name
        """.as(parser.singleOpt)
    )
    log.info(s"Repositorylayer => $x => Servicelayer")
    x
  }

  def find() = {
    Some(1)
  }

  def search() = {
    List(Some(1))
  }

  def list() = {
    List(1)
  }

  def create() = {
    1
  }

  def update() = {
    1
  }

}
