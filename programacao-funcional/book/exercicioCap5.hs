squares :: Int -> Int
squares x = sum [y^2 | y <- [1..x]]

grid :: Int -> Int -> [(Int,Int)]
grid m n = [(x,y) | x <- [0..m], y <- [0..n]]

square :: Int -> [(Int,Int)]
square n = [(x,y) | (x,y) <- (grid n n), x /= y]

