(ns clojure-coding-challenges.strings.is-unique
  (:require
   [schema.core :as s]))

; Is Unique: Implement an algorithm to determine if a string has all unique characters.
; What if you cannot use additional data structures?

; Idiomatic implementation

(s/defn is-unique? :- s/Bool
  [s :- (s/maybe s/Str)]
  (if (empty? s) true (apply distinct? s)))

; Without shortcuts implementation
; Time complexity: O(n)
; Space complexity: O(n)

(s/defn is-unique?* :- s/Bool
  [s :- (s/maybe s/Str)]
  (loop [remaining-str s
         char-set #{}]
    (if-let [[first-char & rest-of-chars] remaining-str]
      (if (char-set first-char)
        false
        (recur rest-of-chars (conj char-set first-char)))
      true)))

; Learnings:
;   - keyword function on a char returns nil
;   - first and rest functions on a string returns chars
;   - empty? on nil returns true
