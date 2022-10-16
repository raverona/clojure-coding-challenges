(ns clojure-coding-challenges.strings.is-unique-test
  (:require [clojure.test :refer [deftest testing is use-fixtures]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure-coding-challenges.strings.is-unique :refer [is-unique?]]
            [schema.test :refer [validate-schemas]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))

(use-fixtures :once validate-schemas)

; Declarative tests

(deftest is-unique-test
  (testing "Returns true for strings composed of unique characters only"
    (is (is-unique? "asdqwertyfgh")))

  (testing "Returns false for strings composed of non-unique characters"
    (is (not (is-unique? "asdqwertyfgha"))))

  (testing "Returns true for empty string"
    (is (is-unique? "")))

  (testing "Returns false for nil"
    (is (is-unique? nil))))

; Generative tests

(def unique-string-generator
  (gen/fmap (fn [xs] (apply str (set xs)))
            (gen/not-empty (gen/vector gen/char-ascii))))

(defspec is-unique 1000
  (prop/for-all [unique-string unique-string-generator]
    (is-unique? unique-string)))

(def non-unique-string-generator
  (gen/fmap (fn [xs] (apply str (concat xs xs)))
            (gen/not-empty (gen/vector gen/char-ascii))))

(defspec is-not-unique 1000
  (prop/for-all [non-unique-string non-unique-string-generator]
    (not (is-unique? non-unique-string))))