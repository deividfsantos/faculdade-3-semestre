data Nat = Zero | Succ Nat 

add :: Nat -> Nat -> Nat
add m n = int2nat (nat2int m + nat2int n) 

nat2int :: Nat -> Int
nat2int Zero = 0
nat2int (Succ n) = 1 + nat2int n 

int2nat :: Int -> Nat 
int2nat 0 = Zero 
int2nat n = Succ (int2nat (n-1)) 

mult :: Nat -> Nat -> Nat
mult n Zero = Zero
mult m (Succ n) = add m (mult m n)

data Tree a = Leaf a 
            | Node (Tree a) a (Tree a) 

t :: Tree Int
t = Node (Node (Leaf 1) 3 (Leaf 4)) 5 (Node (Leaf 6) 7 (Leaf 9))

completeTree :: Tree a -> Bool
completeTree (Leaf x) = True
completeTree (Node x y z)   | (x == null) && (z != null) = False
                            | (x != null) && (z == null) = False
                            | otherwise  = completeTree x && completeTree z

occurs :: Ord a => a -> Tree a -> Bool 
occurs x (Leaf y)     = x == y 
occurs x (Node l y r) = x == y s
                        || occurs x l 
                        || occurs x r 
