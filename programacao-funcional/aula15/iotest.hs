import Data.Char

nim :: IO ()
nim = do printList ["1 *****","2 ****","3 ***","4 **", "5 *"]
         selectPlayer ["1 *****","2 ****","3 ***","4 **", "5 *"]

selectPlayer :: [[Char]] -> IO ()
selectPlayer xs = do putStrLn "Player 1: "
		     playNim xs
		     putStrLn "Player 2: "
                     playNim xs
		     selectPlayer xs

playNim :: [[Char]] -> IO ()
playNim xs = do putStrLn "Digite a linha que deseja remover simbolos:"
                line <- getChar
	        putStrLn "\nDigite a quantidade de simbolos que deseja remover:"
                remover <- getChar
                putStr "\n\n"
                removeSimbols xs line (digitToInt remover)

removeSimbols :: [[Char]] -> Char -> Int -> IO () 
removeSimbols xs x y = do printList xs 
                          selectPlayer (removeSimbolsAux xs x y)

removeSimbolsAux :: [[Char]] -> Char -> Int -> [[Char]]
removeSimbolsAux [] _ _ = []
removeSimbolsAux (x:xs) y z = if head x == y 
    then take ((length x) - z) x : xs
    else x : removeSimbolsAux xs y z

printList :: [[Char]] -> IO()
printList xs = putStr (unlines xs)



