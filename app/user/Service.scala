package user

import javax.inject.Inject
import play.api.Logger
import play.api.db.Database

class Service @Inject()(
  repo: Repository,
  db: Database
)(){

  private val log = Logger("application")

  def get(id: Long) =  {
    log.info(s"Servicelayer => (get - id: $id) => Repolayer")
    val x = db.withConnection(implicit connection => repo.get(id)(connection))
    log.info(s"Service => ($x) => Controller")
    x
  }

  def list() = {
    log.info("Servicelayer => (list) => Repolayer")
    val x = db.withConnection(implicit c => repo.list()(c))
    log.info(s"Service => ($x) => Controller")
    x
  }

  def create(user: User): Either[Error, User] = {
    log.info(s"Servicelayer => (create) => Repolayer\nuser: $user")
    val x =  db.withTransaction(implicit c => repo.create(user)(c))
    log.info(s"Service => ($x) => Controller")
    x
  }

  /*def update(id: Long, patch: UpdateRequest): Either[Error, Option[UpdateRequest]] = {
    log.info(s"Servicelayer => (update) => Repolayer\nuser: $patch")
    val x = db.withTransaction(implicit c => repo.update(id, patch)(c))
    log.info(s"Service => ($x) => Controller")
    x
  }*/

}
