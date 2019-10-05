--Exercicio A
pyths :: Int -> [(Int, Int, Int)]
pyths n = [(x,y,z) | x <- [1..n], y <- [1..n], z <- [1..n], x^2 + y^2 == z^2]

--Exercicio B
perfects :: Int -> [Int]
perfects x = [x' | x' <- [1..x], isPerfect x'] 

isPerfect :: Int -> Bool
isPerfect x = sum (divisores x) == x

divisores :: Int -> [Int]
divisores x = [x' | x' <- [1..x-1], mod x x' == 0 ]

--Exercicio C
scalarProduct :: [Int] -> [Int] -> Int
scalarProduct xs ys = sum [x * y | (x, y) <- zip xs ys]
