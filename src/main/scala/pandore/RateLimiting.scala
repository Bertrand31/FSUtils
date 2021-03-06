package pandore

import cats.effect.Concurrent
import cats.effect.std.Semaphore
import cats.implicits._

object RateLimiting {

  def throttle[F[_], A, B](semaphore: Semaphore[F], fn: A => F[B])
                          (implicit C: Concurrent[F]): A => F[B] =
    (semaphore.acquire *> fn(_) <* semaphore.release)
}
