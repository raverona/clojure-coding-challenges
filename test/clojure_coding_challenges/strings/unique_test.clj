(ns clojure-coding-challenges.strings.unique-test
  (:require
   [clojure-coding-challenges.strings.unique :refer [unique? unique?*]]
   [clojure.test :refer [are deftest testing use-fixtures]]
   [clojure.test.check.clojure-test :refer [defspec]]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [schema.test :refer [validate-schemas]]))

(use-fixtures :once validate-schemas)

; Declarative tests

(deftest unique?-declarative-test
  (testing "Returns true for strings composed of unique characters only"
    (let [unique-string "asdqwertyfgh"]
      (are [x] x
        (unique? unique-string)
        (unique?* unique-string))))

  (testing "Returns false for strings composed of non-unique characters"
    (let [non-unique-string "asdqwertyfgha"]
      (are [x] (not x)
        (unique? non-unique-string)
        (unique?* non-unique-string))))

  (testing "Returns true for empty string"
    (are [x] x
      (unique? "")
      (unique?* "")))

  (testing "Returns true for nil"
    (are [x] x
      (unique? nil)
      (unique?* nil))))

; Generative tests

(def unique-string-generator
  (gen/fmap (fn [xs] (apply str (set xs)))
            (gen/not-empty (gen/vector gen/char-ascii))))

(defspec unique?-generative-test 1000
  (prop/for-all [unique-string unique-string-generator]
                (unique? unique-string)
                (unique?* unique-string)))

(def non-unique-string-generator
  (gen/fmap (fn [xs] (apply str (concat xs xs)))
            (gen/not-empty (gen/vector gen/char-ascii))))

(defspec not-unique?-generative-test 1000
  (prop/for-all [non-unique-string non-unique-string-generator]
                (not (unique? non-unique-string))
                (not (unique?* non-unique-string))))
