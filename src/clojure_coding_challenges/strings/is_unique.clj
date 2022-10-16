(ns clojure-coding-challenges.strings.is-unique
  (:require [schema.core :as s]))

; Is Unique: Implement an algorithm to determine if a string has all unique characters.
; What if you cannot use additional data structures?

(s/defn is-unique? :- s/Bool
  [str :- (s/maybe s/Str)]
  (if (empty? str) true (apply distinct? str)))
