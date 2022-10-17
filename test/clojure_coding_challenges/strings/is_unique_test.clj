(ns clojure-coding-challenges.strings.is-unique-test
  (:require
   [clojure-coding-challenges.strings.is-unique :refer [is-unique? is-unique?*]]
   [clojure.test :refer [are deftest testing use-fixtures]]
   [clojure.test.check.clojure-test :refer [defspec]]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [schema.test :refer [validate-schemas]]))

(use-fixtures :once validate-schemas)

; Declarative tests

(deftest is-unique-test
  (testing "Returns true for strings composed of unique characters only"
    (let [unique-string "asdqwertyfgh"]
      (are [x] x
        (is-unique? unique-string)
        (is-unique?* unique-string))))

  (testing "Returns false for strings composed of non-unique characters"
    (let [non-unique-string "asdqwertyfgha"]
      (are [x] (not x)
        (is-unique? non-unique-string)
        (is-unique?* non-unique-string))))

  (testing "Returns true for empty string"
    (are [x] x
      (is-unique? "")
      (is-unique?* "")))

  (testing "Returns true for nil"
    (are [x] x
      (is-unique? nil)
      (is-unique?* nil))))

; Generative tests

(def unique-string-generator
  (gen/fmap (fn [xs] (apply str (set xs)))
            (gen/not-empty (gen/vector gen/char-ascii))))

(defspec 'is-unique 1000
  (prop/for-all [unique-string unique-string-generator]
                (is-unique? unique-string)
                (is-unique?* unique-string)))

(def non-unique-string-generator
  (gen/fmap (fn [xs] (apply str (concat xs xs)))
            (gen/not-empty (gen/vector gen/char-ascii))))

(defspec 'is-not-unique 1000
  (prop/for-all [non-unique-string non-unique-string-generator]
                (not (is-unique? non-unique-string))
                (not (is-unique?* non-unique-string))))
