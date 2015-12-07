import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BookShelfSimulation extends Simulation {
  val httpConf = http.baseURL("http://localhost:9000")

  object Books {
    def view = exec(http("books list").get("/api/books").check(status.is(200)))
      .pause(5 seconds)
      .exec(http("view book").get("/api/books/1").check(status.is(200)))

    def register = exec(http("books list").get("/api/books").check(status.is(200)))
      .pause(1 minute)
      .exec(http("create books").post("/api/books").formParamSeq(Seq("title"->"Learning Scala", "authorName" -> "Jason Swartz")))
  }

  val scns = List(Books.view, Books.register)
  val users = scenario("books api action").exec(scns)

  setUp(users.inject(
    rampUsers(100) over(5 minutes)
  ).protocols(httpConf))
}
