package test

import javax.inject.Inject

class Repository @Inject()(

)(){

  def get() =  {
    1
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
