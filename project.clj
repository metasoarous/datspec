(defproject datspec "0.0.1-alpha1-SNAPSHOT"
  :description "Protocols and specs for the Datsys architecture - the shape of things."
  :url "http://github.com/metasoarous/datspec"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0-alpha6"]
                 [org.clojure/clojurescript "1.9.36"]]
  :cljsbuild {:builds [{:id "release"
                        :source-paths ["src" "bench/src"]
                        :assert false
                        :compiler {:output-to     "release-js/datspec.bare.js"
                                   :optimizations :advanced
                                   :pretty-print  false
                                   :elide-asserts true
                                   :output-wrapper false 
                                   :parallel-build true}}]}
                        ;:notify-command ["release-js/wrap_bare.sh"]
  :profiles {:dev {:source-paths ["bench/src" "test" "dev" "src"]
                   :plugins [[lein-cljsbuild "1.1.2"]
                             [lein-typed "0.3.5"]]
                   :cljsbuild {:builds [{:id "advanced"
                                         :source-paths ["src" "bench/src" "test"]
                                         :compiler {:output-to     "target/datspec.js"
                                                    :optimizations :advanced
                                                    :source-map    "target/datspec.js.map"
                                                    :pretty-print  true
                                                    :recompile-dependents false
                                                    :parallel-build true}}
                                        {:id "none"
                                         :source-paths ["src" "bench/src" "test" "dev"]
                                         :compiler {:main          datspec.test
                                                    :output-to     "target/datspec.js"
                                                    :output-dir    "target/none"
                                                    :optimizations :none
                                                    :source-map    true
                                                    :recompile-dependents false
                                                    :parallel-build true}}]}}}
  :clean-targets ^{:protect false} ["target"
                                    "release-js/datspec.bare.js"
                                    "release-js/datspec.js"]
  ;;
  ;; ## Back to from extraction...
  ;; =============================
  ;;
  ;; Once we're ready 
  ;:core.typed {:check []
               ;:check-cljs []}
  ;; Don't need resources for now
  ;; Thought could maybe use this as a place for common schema code...
  ;:resource-paths ["resources" "resources-index/prod"]
  :target-path "target/%s"
  :aliases {"package"
            ["with-profile" "prod" "do"
             "clean" ["cljsbuild" "once"]]})

