(ns clojureproject.routes.questions 
  (:require [compojure.core :refer :all]
            [clojureproject.views.layout :as layout]
            [noir.session :as session]
            [ring.util.response :as response])
  (:use  [hiccup.form :only [form-to label text-field password-field submit-button]]
          ))

(defn test []
  (layout/common [:h1 "Questions!"]
                [:div.reg
              
  
           ]))

(defroutes question-routes
  (GET "/test" [] (test))

  )