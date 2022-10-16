(defproject clojure-coding-challenges "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :profiles {:dev {:dependencies [[nubank/matcher-combinators "3.5.1"]
                                  [org.clojure/tools.namespace "1.3.0"]
                                  [prismatic/schema "1.4.1"]]
                   :source-paths ["config"]
                   :repl-options {:init-ns user}}}
  :repl-options {:init-ns clojure-coding-challenges.core})
