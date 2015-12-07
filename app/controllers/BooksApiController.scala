package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import repositories.BooksRepository
import skinny.util.JSONStringOps._
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

class BooksApiController @Inject()(booksRepository: BooksRepository) extends Controller {

  def list() = Action.async { implicit request =>
    for(books <- booksRepository.findAll) yield {
      Ok(toJSONString(books)).as(JSON)
    }
  }

  def book(bookId: Long) = Action.async { implicit request =>
    for( book <- booksRepository.findById(bookId) ) yield {
      Ok(toJSONString(book)).as(JSON)
    }
  }

  def create() = Action.async { implicit request =>
    bookForm.bindFromRequest().fold(
      failed => Future.successful(BadRequest),
      success => {
        for(book <- booksRepository.create(success.title, success.authorName)) yield {
          Ok(toJSONString(book)).as(JSON)
       }
      }

    )
  }

  val bookForm = Form(
    mapping("title" -> nonEmptyText,
      "authorName" -> nonEmptyText)(BookInfo.apply)(BookInfo.unapply)
  )

  case class BookInfo(title: String, authorName: String)
}
