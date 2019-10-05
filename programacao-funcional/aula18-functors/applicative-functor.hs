mult :: [Int] -> [Int] -> [Int]
mult (xs) (ys) = [x * y | x <- xs, y <- ys ]

instance applicative [] where
    pure x = [x]
    fs<*> xs = [f x | f <- fs, x <- xs]