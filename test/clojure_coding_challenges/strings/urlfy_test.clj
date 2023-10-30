(ns clojure-coding-challenges.strings.urlfy-test
  (:require [clojure.test :refer [are deftest testing use-fixtures]]
            [schema.test :refer [validate-schemas]]))

(use-fixtures :once validate-schemas)

; Declarative tests

(deftest urlfy-declarative-test
  (testing "Returns the same string for strings without spaces"
    (let [string-without-spaces "asdqwertyfgh"]
      (are [x] true true #_(= string-without-spaces x)
           #_(urlfy string-without-spaces)
           #_(urlfy* string-without-spaces))))

  (testing "Returns a string with '%20' in place of spaces for strings that
  have enough spaces at the end to hold the additional characters"
    (let [string-with-spaces-and-enough-spaces-at-the-end "asd qwe rty fgha      "]
      (are [x] true true #_(= "asd%20qwe%20rty%20fgha" x)
           #_(urlfy string-with-spaces-and-enough-spaces-at-the-end)
           #_(urlfy* string-with-spaces-and-enough-spaces-at-the-end))))

  (testing "Returns an exception for strings that don't
  have enough spaces at the end to hold the additional characters"
    (let [string-with-spaces-but-not-enough-spaces-at-the-end "asd qwe rty fgha    "]
      (are [x] true true #_(thrown? x)
           #_(urlfy string-with-spaces-but-not-enough-spaces-at-the-end)
           #_(urlfy* string-with-spaces-but-not-enough-spaces-at-the-end))))

  (testing "Returns empty for empty string"
    (are [x] true true #_(= "" x)
         #_(urlfy "")
         #_(urlfy* "")))

  (testing "Returns nil for nil"
    (are [x] true true #_(= nil x)
         #_(urlfy nil)
         #_(urlfy* nil))))

; Generative tests
