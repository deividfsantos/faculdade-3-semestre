import System.IO     

act :: IO (Char,Char) 
act = do x <- getChar
         getChar
         y <- getChar
         return (x,y) 

getLine' :: IO String 
getLine' = do x <- getChar 
              if x == '\n' then 
                 return [] 
              else 
                 do xs <- getLine' 
                    return (x:xs)

putStr' :: String -> IO () 
putStr' []     = return () 
putStr' (x:xs) = do putChar x 
                    putStr' xs 

putStrLn' :: String -> IO () 
putStrLn' xs = do putStr' xs 
                  putChar '\n' 

strlen :: IO () 
strlen = do putStr "Enter a string: " 
            xs <- getLine 
            putStr "The string has " 
            putStr (show (length xs)) 
            putStrLn " characters" 





hangman :: IO () 
hangman = do putStrLn "Think of a word: " 
             word <- sgetLine 
             putStrLn "Try to guess it:" 
             play word 

sgetLine :: IO String 
sgetLine = do x <- getCh 
              if x == '\n' then 
                 do putChar x 
                    return [] 
              else 
                 do putChar '-' 
                    xs <- sgetLine 
                    return (x:xs) 

getCh :: IO Char   
getCh = do hSetEcho stdin False                
           x <- getChar               
           hSetEcho stdin True               
           return x 

play :: String -> IO () 
play word = 
   do putStr "? " 
      guess <- getLine 
      if guess == word then 
         putStrLn "You got it!" 
      else 
         do putStrLn (match word guess) 
            play word 

match :: String -> String -> String 
match xs ys = 
   [if elem x ys then x else '-' | x <- xs] 



nim :: IO ()
nim = do printList ["1 * * * * *","2 * * * *","3 * * *","4 * *", "5 *"]
         selectPlayer ["1 * * * * *","2 * * * *","3 * * *","4 * *", "5 *"]

selectPlayer :: [[Char]] -> IO ()
selectPlayer xs = do putStr "Player 1"
		     playNim xs
		     putStr "Player 2"
                     playNim xs
		     selectPlayer

playNim :: IO String
playNim xs = do putStr "Digite a linha que deseja remover simbolos:"
                line <- sgetLine
	        putStr "Digite a quantidade de simbolos que deseja remover:"
                remover <- sgetLine
                removeSimbols xs

removeSimbols :: [[Char]] -> Int -> Int -> IO () 
removeSimbols xs x y = do printList xs 
                          selectPlayer (drop (x+1) (take y))


printList :: [[Char]] -> IO()
printList xs = putStr (unlines xs)







