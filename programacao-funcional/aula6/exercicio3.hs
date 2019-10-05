luhn :: Int -> Int -> Int -> Int -> Bool 
luhn w x y z = mod ((luhnDouble w) + x + (luhnDouble y) + z) 10 == 0

luhnDouble :: Int -> Int 
luhnDouble x = if n > 9
               then n - 9
               else n
                   where n = x * 2
