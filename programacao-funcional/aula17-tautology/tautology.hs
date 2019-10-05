-- Caeser cipher example from chapter 8 of Programming in Haskell,
-- Graham Hutton, Cambridge University Press, 2016.

-- Propositions

data Prop = Const Bool
          | Var Char
          | Not Prop
          | And Prop Prop
          | Imply Prop Prop

p1 :: Prop
p1 = And (Var 'A') (Not (Var 'A'))
-- A ^ ¬A

p2 :: Prop
p2 = Imply (And (Var 'A') (Var 'B')) (Var 'A')
-- (A ^ B) -> A

p3 :: Prop
p3 = Imply (Var 'A') (And (Var 'A') (Var 'B'))
-- A -> (A ^ B)
-- A B | A ^ B | A -> (A ^ B)
-- 0 0 |   0   |      1
-- 0 1 |   0   |      1
-- 1 0 |   0   |      0
-- 1 1 |   1   |      1

p4 :: Prop
p4 = Imply (And (Var 'A') (Imply (Var 'A') (Var 'B'))) (Var 'B')
-- (A ^ (A -> B)) -> B
-- A B | A -> B | A ^ (A -> B) | (A ^ (A -> B)) -> B
-- 0 0 |   1    |     0        |         1 
-- 0 1 |   1    |     0        |         1
-- 1 0 |   0    |     0        |         1 
-- 1 1 |   1    |     1        |         1


-- Substitutions
type Subst = Assoc Char Bool

type Assoc k v = [(k,v)]

find :: Eq k => k -> Assoc k v -> v
find k t = head [v | (k',v) <- t, k == k']

-- Tautology checker
eval :: Subst -> Prop -> Bool
eval _ (Const b)   = b
eval s (Var x)     = find x s
eval s (Not p)     = not (eval s p)
eval s (And p q)   = eval s p && eval s q
eval s (Imply p q) = eval s p <= eval s q

vars :: Prop -> [Char]
vars (Const _)   = []
vars (Var x)     = [x]
vars (Not p)     = vars p
vars (And p q)   = vars p ++ vars q
vars (Imply p q) = vars p ++ vars q

bools :: Int -> [[Bool]]
bools 0 = [[]]
bools n = map (False:) bss ++ map (True:) bss
          where bss = bools (n-1)

rmdups :: Eq a => [a] -> [a]
rmdups []     = []
rmdups (x:xs) = x : filter (/= x) (rmdups xs)

substs :: Prop -> [Subst]
substs p = map (zip vs) (bools (length vs))
           where vs = rmdups (vars p)

isTaut :: Prop -> Bool
isTaut p = and [eval s p | s <- substs p]