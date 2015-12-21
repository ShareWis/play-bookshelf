package repositories

import javax.inject.Singleton

import com.google.inject.Inject
import dao.{AuthorDao, BookDao}
import entities.{Author, Book}
import Book.BookWithAuthor
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BooksRepository@Inject()(
  bookDao: BookDao,
  authorDao: AuthorDao,
  protected val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._


  def findAll(implicit executionContext: ExecutionContext):Future[List[Book]] = db.run(bookDao.findAll().result).map(_.toList)

  def findById(id: Long)(implicit executionContext: ExecutionContext):Future[Book] = db.run{
    (for {
      b <- bookDao.books.filter(_.id === id)
      a <- authorDao.authors.filter(_.id === b.authorId)
    } yield (b, a)).result.head.transactionally
  }.map(_.withAuthor)

  def create(title:String, authorName: String): Future[Book] = {
    val author = Author(2, authorName)
    val book = Book(2, title, author.id)

    Future.successful(book)
  }
}
