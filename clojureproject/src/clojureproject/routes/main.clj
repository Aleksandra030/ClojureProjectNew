(ns clojureproject.routes.main
    (:require [compojure.core :refer :all]
            [clojureproject.views.layout :as layout]
              [ring.util.response :as response]
              [noir.session :as session])
  (:use  [hiccup.form]
         [hiccup.element :only (link-to)]))

(defn main []
  (layout/common [:h1 "MAIN PAGE!"]
           ))

                  
(defroutes main-routes
  (GET "/main" [] (main))
  )