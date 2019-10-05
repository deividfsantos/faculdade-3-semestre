--1
halve :: [a] -> ([a],[a])
halve [] = ([],[])
halve (x:xs) = (take n (x:xs), drop n (x:xs))
    where n = (div (length xs) 2)+1
             
--2
--a
third :: [a] -> a
third (x:xs) = (head (tail xs))

--b
third' :: [a] -> a
third' xs = xs !! 2

--c
third'' :: [a] -> a
third'' (_:_:x:_) = x