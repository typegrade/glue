package glue

trait NaturalTransformation[F[_], G[_]] { self =>
  def apply[A](fa: F[A]): G[A]

  def compose[E[_]](f: NaturalTransformation[E, F]): NaturalTransformation[E, G] =
    new NaturalTransformation[E, G] {
      def apply[A](ea: E[A]): G[A] = self(f(ea))
    }

  def andThen[H[_]](f: NaturalTransformation[G, H]): NaturalTransformation[F, H] =
    f.compose(self)
}

object NaturalTransformation {
  def id[F[_]]: NaturalTransformation[F, F] = new NaturalTransformation[F, F] {
    def apply[A](fa: F[A]): F[A] = fa
  }
}
