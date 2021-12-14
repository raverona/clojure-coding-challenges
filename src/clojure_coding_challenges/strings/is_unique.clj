(ns clojure-coding-challenges.strings.is-unique)

; Is Unique: Implement an algorithm to determine if a string has all unique characters.
; What if you cannot use additional data structures?

(defn is-unique? [str]
  (if (empty? str) true (apply distinct? str)))
