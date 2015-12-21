package entities

case class Book (
  id: Long,
  title: String,
  authorId: Long
) {
  private var _author: Option[Author] = None
  lazy val author = _author.get
}

object Book {
  implicit class BookWithAuthor(tuple: (Book, Author)) {
    def withAuthor = {
      tuple._1._author = Some(tuple._2)
      tuple._1
    }
  }

  def tupled = (Book.apply _).tupled
}
