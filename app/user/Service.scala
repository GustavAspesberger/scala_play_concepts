package user

import javax.inject.Inject
import play.api.Logger

class Service @Inject()(
  repository: Repository
)(){

  private val log = Logger("application")

  def get(name: String) =  {
    log.info("Servicelayer => (get) => Repolayer")
    val x = repository.get(name)
    log.info(s"Service => ($x) Controller")
    x
  }

  def find() = {
    repository.find()
  }

  def search() = {
    repository.search()
  }

  def list() = {
    log.info("Servicelayer => (list) => Repolayer")
    val x = repository.list()
    log.info(s"Service => ($x) Controller")
    x
  }

  def create() = {
    repository.create()
  }

  def update() = {
    repository.update()
  }

}
