and' :: [Bool] -> Bool
and' [] = True
and' (b:bs) = if b then and' bs else False

elem' :: Eq a => a -> [a] -> Bool 
elem' _ [] = False
elem' y (x:xs) = if y == x then True else elem' y xs

merge :: Ord a => [a] -> [a] -> [a] 
merge xs ys = qsort (concat' xs ys)

concat' :: Ord a => [a] -> [a] -> [a]
concat' xs ys = xs ++ ys

qsort :: Ord a => [a] -> [a]
qsort []= []
qsort (x:xs)= qsort smaller ++ [x] ++ qsort larger
  where
    smaller = [a | a <- xs, a <= x]
    larger = [b | b <- xs, b > x]

msort :: Ord a => [a] -> [a] 
msort [] = []
msort (x:xs) = merge (take ((div (length xs) 2)+1) xs) (drop (div (length xs) 2) xs)



--merge :: Ord a => [a] -> [a] -> [a] 
--merge [] [] = []
--merge (x:xs) (y:ys) = if x > y then [y] ++ [x] ++ merge xs ys else [x] ++ [y] ++ merge xs ys
