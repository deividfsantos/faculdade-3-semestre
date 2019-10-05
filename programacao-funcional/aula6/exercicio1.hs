halve :: [a] -> ([a],[a])
halve xs = if even (length xs) 
                then ((take n xs), (drop n xs))
                else ([],[])
                where n = div (length xs) 2


halve' :: [a] -> ([a],[a])
halve' xs = (take n xs, drop n xs)
                where n = div (length xs) 2

