(ns clojure-coding-challenges.strings.is-unique)

; Is Unique: Implement an algorithm to determine if a string has all unique characters.

(defn is-unique? [str]
  (if (empty? str) true (apply distinct? str)))


; What if you cannot use additional data structures?
(defn is-unique*? [str]
  (let [sorted-str (sort str)]
    (loop [first-char (first sorted-str)
           last-char  nil
           remaining-chars  (rest sorted-str)]
      (if (empty? remaining-chars)
        true
        (if (= first-char last-char)
          false
          (recur (first remaining-chars)
                  first-char
                 (rest remaining-chars)))))))
