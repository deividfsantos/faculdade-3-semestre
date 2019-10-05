cdsieve :: [Int] -> [Int]
sieve (p:ps) = p : sieve [x | x <- ps, mod x p /= 0]