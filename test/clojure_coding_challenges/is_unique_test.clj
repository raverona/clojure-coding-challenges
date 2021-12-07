(ns clojure-coding-challenges.is-unique-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure-coding-challenges.is-unique :refer [is-unique?]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))

(deftest is-unique-test
  (testing "Returns true for strings composed of unique characters only"
    (is (is-unique? "asdqwertyfgh")))

  (testing "Returns false for strings composed of non-unique characters"
    (is (not (is-unique? "asdqwertyfgha"))))

  (testing "Returns true for empty string"
    (is (is-unique? ""))))

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