(ns clojure-coding-challenges.is-unique)

; Is Unique: Implement an algorithm to determine if a string has all unique characters.

(defn is-unique? [str]
  (= (count (set str))
     (count str)))
