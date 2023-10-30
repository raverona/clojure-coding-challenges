(ns clojure-coding-challenges.strings.permutation
  (:require [schema.core :as s]))

; Check Permutation: Given two strings, write a method to decide if
; one is a permutation of the other.

; Idiomatic implementation

(s/defn permutation? :- s/Bool
  [first :- s/Str
   second :- s/Str]
  (= (frequencies first) (frequencies second)))

; Without shortcuts implementation
; Time complexity: O(n)
; Space complexity: O(n)

(s/defn permutation?* :- s/Bool
  [first-str :- s/Str
   second-str :- s/Str]
  (if (= (count first-str) (count second-str))
    (loop [remaining-first first-str
           remaining-second second-str
           first-frequencies {}
           second-frequencies {}]
      (if-let [[first-char & rest-of-chars] remaining-first]
        (recur
         rest-of-chars
         (rest remaining-second)
         (merge-with + first-frequencies {first-char 1})
         (merge-with + second-frequencies {(first remaining-second) 1}))
        (= first-frequencies second-frequencies)))
    false))

; Learnings:

; Other implementation ideas:
;   - sort the strings and check if they are equal.
;     - Time complexity: O(n*log(n))
;     - Space complexity: O(1)
