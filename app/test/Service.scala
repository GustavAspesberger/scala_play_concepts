package test

import javax.inject.Inject

class Service @Inject()(
  repository: Repository
)(){

  def get() =  {
    repository.get()
  }

  def find() = {
    repository.find()
  }

  def search() = {
    repository.search()
  }

  def list() = {
    repository.list()
  }

  def create() = {
    repository.create()
  }

  def update() = {
    repository.update()
  }

}
