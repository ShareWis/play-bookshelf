package repositories

import javax.inject.Singleton

import com.google.inject.Inject
import entities.{Author, Book}
import Book.BookWithAuthor

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BooksRepository@Inject()() {
  val sampleBook = (Book(1, "Programming in Scala", 1), Author(1, "Martin Odersky"))

  def findAll(implicit executionContext: ExecutionContext):Future[List[Book]] = {
    val books = List(sampleBook).map(_.withAuthor)
    Future.successful(books)
  }
  def findById(long: Long)(implicit executionContext: ExecutionContext):Future[Book] = {
    Future.successful(sampleBook.withAuthor)
  }
  def create(title:String, authorName: String): Future[Book] = {
    val author = Author(2, authorName)
    val book = Book(2, title, author.id)

    Future.successful(book)
  }
}
