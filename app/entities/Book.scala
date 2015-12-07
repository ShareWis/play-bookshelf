package entities

case class Book (
  id: Long,
  title: String,
  authorId: Long,
  private val _author: Option[Author] = None
) {
  lazy val author = _author.get
}

object Book {
  implicit class BookWithAuthor(tuple: (Book, Author)) {
    def withAuthor = tuple._1.copy(_author = Some(tuple._2))
  }
}
