(ns clojureproject.routes.questions 
  (:require [compojure.core :refer :all]
            [clojureproject.views.layout :as layout]
            [noir.session :as session]
            [ring.util.response :as response])
  (:use  [hiccup.form :only [form-to label text-field password-field submit-button radio-button]]
          ))

(defn test []
  (layout/common [:h1 "Questions!"]
                (form-to [:post "/getAnswers"]
                         [:div.reg
                          "Koliko vremena provodite sa racunarom?"
                          [:div "2h"(radio-button 1 true {:value1 "2"} )
                           [:br] "5h" (radio-button 1 false {:value1 "2"})
                           [:br] "7h"(radio-button 1 true {:value1 2})
                           [:br] "vise od 10" (radio-button 1 false {:value1 2})
                           ]
                           [:div
                           (submit-button "Save item")]
              
  
          ])
             
))

(defn setAnswers 
  [value1]
  (println "dosao")
 (println value1)
                    )

(defroutes question-routes
  (GET "/test" [] (test))
  (POST "/getAnswers" [value1] (setAnswers value1))

  )