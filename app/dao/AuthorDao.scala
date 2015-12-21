package dao

import javax.inject.Singleton

import com.google.inject.Inject
import entities.Author
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by kuchitama on 15/12/21.
  */
@Singleton
class AuthorDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile]{
  import driver.api._

  val authors = TableQuery[AuthorTable]
  class AuthorTable(tag: Tag) extends Table[Author](tag, "author") {
    def id = column[Long]("id")
    def name = column[String]("name")

    def * = (id, name) <> (Author.tupled, Author.unapply)
  }
}
