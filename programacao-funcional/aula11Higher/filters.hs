
filter' p xs = [p x | x <- xs]

--filter'' :: ([a] -> Bool) -> [a] -> [a]
filter'' p xs = [x | x <- xs, p x]

odd' :: Int -> Bool
odd' x = not (even x)
