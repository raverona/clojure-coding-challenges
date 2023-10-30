(ns clojure-coding-challenges.strings.permutation-test
  (:require [clojure-coding-challenges.strings.permutation :refer [permutation?
                                                                   permutation?*]]
            [clojure.test :refer [are deftest testing use-fixtures]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [schema.test :refer [validate-schemas]]))

(use-fixtures :once validate-schemas)

; Declarative tests

(deftest permutation?-declarative-test
  (testing "Returns true for strings that are a permutation of each other"
    (let [original-string "asdqwertyfgh"
          permuted-string "hgfytrewqdsa"]
      (are [x] x
        (permutation? original-string permuted-string)
        (permutation?* original-string permuted-string))))

  (testing "Returns false for strings that have the same length but are not a permutation of each other"
    (let [original-string "asdqwertyfgh"
          non-permuted-string "hgfytrewqdse"]
      (are [x] (not x)
        (permutation? original-string non-permuted-string)
        (permutation?* original-string non-permuted-string))))

  (testing "Returns false for strings that do not have the same length"
    (let [original-string "asdqwertyfgh"
          non-permuted-string "hgfytrewqds"]
      (are [x] (not x)
        (permutation? original-string non-permuted-string)
        (permutation?* original-string non-permuted-string))))

  (testing "Returns true for empty strings"
    (are [x] x
      (permutation? "" "")
      (permutation?* "" ""))))

; Generative tests

(def string-and-permutation-generator
  (gen/bind
   gen/string-ascii
   (fn [string]
     (gen/tuple
      (gen/return string)
      (gen/fmap #(apply str %) (gen/shuffle string))))))

(defspec permutation?-generative-test 1000
  (prop/for-all [[string permutation] string-and-permutation-generator]
                (permutation? string permutation)
                (permutation?* string permutation)))

(def string-and-non-permutation-generator
  (gen/bind
   (gen/tuple
    (gen/not-empty (gen/vector gen/char-ascii 0 30))
    (gen/not-empty (gen/vector gen/char-ascii 1 30)))
   (fn [[chars additional-chars]]
     (let [string (apply str chars)
           additional-string (apply str additional-chars)]
       (gen/tuple
        (gen/return string)
        (gen/fmap #(apply str %) (gen/shuffle (str string additional-string))))))))

(defspec not-permutation?-generative-test 1000
  (prop/for-all [[string non-permutation] string-and-non-permutation-generator]
                (not (permutation? string non-permutation))
                (not (permutation?* string non-permutation))))
