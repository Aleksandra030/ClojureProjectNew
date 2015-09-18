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
            [db.database :only [get-user-by-username]]
            [noir.session :as session])
    (:use  [db.database :only [get-admin-by-username insert-user insert-item]]))

(defn init []
  (println "clojureproject is starting")
  (let [admin (get-admin-by-username "admin")] 
      (if (= admin nil)
        (do 
              (insert-user "admin" "admin" "admin@admin" "admin" "admin")
              (insert-item "Math" "5" "programming")
              (insert-item "English" "3" "management")
              ( insert-item "Economy" "5" "management")
              )
        (println "Admin not exist!"))
    )
  )

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
