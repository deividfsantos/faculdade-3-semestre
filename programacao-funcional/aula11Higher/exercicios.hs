--mapilter :: (a -> a) -> (a -> Bool) -> [a] -> [a]
--mapilter f p xs = [f x | x <- xs, p x]
mapfilter f p xs = map f (filter p xs)
