inverseQsort :: Ord a => [a] -> [a]
inverseQsort []= []
inverseQsort (x:xs)= inverseQsort larger ++ [x] ++ inverseQsort smaller
  where
    smaller= [a | a <- xs, a <= x]
    larger= [b | b <- xs, b> x]