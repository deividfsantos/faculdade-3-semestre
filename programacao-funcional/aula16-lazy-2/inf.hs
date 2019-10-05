inf :: Int
inf = 1+inf

fst' :: (a,a) -> a
fst' (x,y) = x

square :: Int -> Int
square n = n * n

ones :: [Int]
ones = 1:ones 

sumwith :: Int -> [Int] -> Int
sumwith v [] = v
sumwith v (x:xs) = sumwith (v+x) xs

sumwith' :: Int -> [Int] -> Int
sumwith' v [] = v
sumwith' v (x:xs) = (sumwith $! (v+x)) xs

--     Out
-- square (1+2)
-- (1+2) * (1+2)
--   3   *   3
--       9

--     Inner
-- square (1+2)
-- square 3
-- 3 * 3 
--   9

-- sumwith 0 [1,2,3]
-- sumwith (0+1) [2,3]
-- sumwith ((0+2)+2) [3]
-- sumwith (((0+1)+1)+3) []
-- (((0+1)+2)+3)
-- ((1+2)+3)
-- (3+3)
-- (6)