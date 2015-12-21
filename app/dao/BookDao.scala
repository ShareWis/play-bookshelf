package dao

import javax.inject.Singleton

import com.google.inject.Inject
import entities.{Book, Author}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by kuchitama on 15/12/21.
  */
@Singleton
class BookDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile]{
  import driver.api._

  val books = TableQuery[BookTable]
  class BookTable(tag: Tag) extends Table[Book](tag, "book") {
    def id = column[Long]("id")
    def title= column[String]("title")
    def authorId = column[Long]("author_id")

    def * = (id, title, authorId) <> (Book.tupled, Book.unapply)
  }

  def findAll() = books
}
