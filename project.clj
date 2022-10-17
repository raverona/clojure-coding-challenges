(defproject clojure-coding-challenges "0.0.1-SNAPSHOT"
  :description "Clojure implementations of Cracking the code interview"
  :url "https://github.com/raverona/clojure-coding-challenges"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]]

  :profiles {:dev {:plugins      [[com.github.clojure-lsp/lein-clojure-lsp "1.3.14"]]
                   :dependencies [[nubank/matcher-combinators "3.5.1"]
                                  [org.clojure/tools.namespace "1.3.0"]
                                  [prismatic/schema "1.4.1"]]
                   :source-paths ["config"]
                   :repl-options {:init-ns user}}}

  :repl-options {:init-ns clojure-coding-challenges.core}

  :aliases {"diagnostics"     ["clojure-lsp" "diagnostics"]
            "format"          ["clojure-lsp" "format" "--dry"]
            "format-fix"      ["clojure-lsp" "format"]
            "clean-ns"        ["clojure-lsp" "clean-ns" "--dry"]
            "clean-ns-fix"    ["clojure-lsp" "clean-ns"]
            "lint"            ["do" ["diagnostics"]  ["format"] ["clean-ns"]]
            "lint-fix"        ["do" ["format-fix"] ["clean-ns-fix"]]})
