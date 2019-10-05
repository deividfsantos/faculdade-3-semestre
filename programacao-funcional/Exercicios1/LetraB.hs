produtorio :: Num a => [a] -> a
produtorio [] = 1
produtorio (x : xs) = x * produtorio xs