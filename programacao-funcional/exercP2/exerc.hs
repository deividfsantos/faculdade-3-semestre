taxicab :: Int -> [(Int,Int,Int,Int)]
taxicab n = [ (a,b,c,d) | a <- [1..n], b <- [1..n], c <- [1..n], d <- [1..n], a^3 + b^3 == c^3 + d^3 
            , a /= b, a /= c 
            , a /= d, b /= c 
            , b /= d, c /= d]

disjoint :: (Ord a) => [a] -> [a] -> Bool
disjoint [] _ = False
disjoint _ [] = False
disjoint (x:xs) (y:ys) | x == y = True
                       | otherwise = disjoint (x:xs) ys || disjoint xs (y:ys)

-- data Tree a = Leaf | Node (Tree a) a (Tree a) deriving Show

-- balance :: [a] -> Tree a
-- balance [] = Leaf
-- balance (x:xs) = Node (balance (head (divl xs))) x (balance (head (tail (divl xs))))

-- data Tree a = Leaf a | Node Tree Tree deriving Show

-- balance :: [a] -> Tree a
-- balance [] = Leaf 0
-- balance (x:xs) = Node (balance (head (divl xs))) (balance (head (tail (divl xs))))

divl :: [a] -> [[a]]
divl xs = [(take (div (length xs) 2) xs)] ++ [(drop (div (length xs) 2) xs)]