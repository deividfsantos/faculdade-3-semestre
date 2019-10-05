--a
--Mostre que sum [x] = x para qualquer valor numérico x.
sum :: Num a => [a] -> a
sum [x] = x

--b
--Defina uma função que calcula o produtório de uma
--lista de números. Por exemplo, produtorio [2,3,4]
produtorio :: Num a => [a] -> a 
produtorio [] = 1
produtorio (n:ns) = n * produtorio ns

--c
--Como a função qsort deve ser alterada para produzir
--uma lista em ordem decrescente?

--Original
qsort :: Ord a => [a] -> [a]
qsort [] = []
qsort (x:xs) = qsort smaller ++ [x] ++ qsort larger
  where
    smaller = [a | a <- xs, a <= x]
    larger = [b | b <- xs, b > x]

--Inversa
qsortInverse :: Ord a => [a] -> [a]
qsortInverse [] = []
qsortInverse (x:xs) = qsortInverse larger ++ [x] ++ qsortInverse smaller
  where
    smaller = [a | a <- xs, a <= x]
    larger = [b | b <- xs, b > x]

--d
--Qual seria o efeito de <= fosse trocado por < na função
--qsort? (Dica: considere a entrada [2,2,3,1,1].)
--Ignorar os iguais
qsort' :: Ord a => [a] -> [a]
qsort' [] = []
qsort' (x:xs) = qsort' smaller ++ [x] ++ qsort' larger
  where
    smaller = [a | a <- xs, a < x]
    larger = [b | b <- xs, b > x]

--e
--Mostre as aplicações sucessivas de qsort na seguinte
--chamada: qsort [3,5,1,4,2]
--qsort [3,5,1,4,2]
--qsort [3,1,2]
--qsort [1,2]

--qsort [5,4]
--qsort [4]
--qsort [4,5]















