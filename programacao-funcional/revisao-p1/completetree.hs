data Tree = Leaf Int | Node Tree Tree

complete :: Tree -> Bool
complete (Node l r) = size l == size r

size :: Tree -> Int
size (Leaf n) = 1
size (Node l r) = size l + size r

t :: Tree
t = Node (Node (Leaf 1) (Leaf 4)) (Node (Leaf 6) (Leaf 9))
