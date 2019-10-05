-- Nomes: Deivid Santos, João Vitor, Henrique Andreata

octalToDecimal :: String -> Int
octalToDecimal [] = 0
octalToDecimal (x:xs) | elem '8' (x:xs) = 0
                      | elem '9' (x:xs) = 0
                      | otherwise = (charToInt x) * (8 ^ (length xs)) + (octalToDecimal xs)

charToInt :: Char -> Int
charToInt x = (strToIntList (x:[]))

strToIntList :: String -> Int
strToIntList xs = read xs :: Int