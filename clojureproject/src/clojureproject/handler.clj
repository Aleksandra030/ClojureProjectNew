(ns clojureproject.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clojureproject.routes.home :refer [home-routes]]
            [clojureproject.routes.register :refer [register-routes]]
            [clojureproject.routes.main :refer [main-routes]]
            [clojureproject.routes.add :refer [add-routes]]
            [clojureproject.routes.questions :refer [question-routes]]
       
             [noir.session :as session]))

(defn init []
  (println "clojureproject is starting"))

(defn destroy []
  (println "clojureproject is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes register-routes  main-routes add-routes question-routes app-routes)
      (handler/site)
      (wrap-base-url)
    (session/wrap-noir-flash)
    (session/wrap-noir-session)))
