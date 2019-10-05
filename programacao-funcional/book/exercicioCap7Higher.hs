--1
mapilter :: (a -> a) -> (a -> Bool) -> [a] -> [a]
mapilter f p xs = map f (filter p xs)

--2
--a
all' :: Eq a => (a -> Bool) -> [a] -> Bool
all' _ [] = True
all' f xs = if filter f xs == xs then True else False

--a
all'' :: Eq a => (a -> Bool) -> [a] -> Bool
all'' f xs = and (map f xs)

--b
any' :: (a -> Bool) -> [a] -> Bool
any' _ [] = False
any' f xs = if null (filter f xs) then False else True

--b
any'' :: (a -> Bool) -> [a] -> Bool
any'' f xs = or (map f xs)

--c
takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' _ [] = []
takeWhile' f (x:xs) | f x       = x : takeWhile' f xs
                    | otherwise = []
--d
dropWhile' :: (a -> Bool) -> [a] -> [a]
dropWhile' _ [] = []
dropWhile' f (x:xs) | f x       = dropWhile' f xs
                    | otherwise = x:xs

--3
map' :: (a -> b) -> [a] -> [b]
map' f = foldr (\x xs -> (f x) : xs) []

filter' :: (a -> Bool) -> [a] -> [a]
filter' f = foldr (\x xs -> if f x then x:xs else xs) []

--4
dec2int :: [Int] -> Int
dec2int = foldl (\x xs -> (10*x) + xs) 0 

--5
sum' :: Int -> (Int -> Int)
sum' = \x -> (\y -> x + y)

sum3' :: Int -> Int
sum3' x = (sum' 1 2) + x