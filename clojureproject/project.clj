(defproject clojureproject "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [lib-noir "0.7.0"]
                 [congomongo "0.4.1"]
                 [org.clojure/clojurescript "0.0-2760"]
                 [racehub/om-bootstrap "0.5.0"]
                 [org.omcljs/om "0.8.8"]]
  :plugins [[lein-ring "0.8.12"]
			[lein2-eclipse "2.0.0"]]
  :ring {:handler clojureproject.handler/app
         :init clojureproject.handler/init
         :destroy clojureproject.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
