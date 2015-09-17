(ns clojureproject.routes.questions 
  (:require [compojure.core :refer :all]
            [clojureproject.views.layout :as layout]
            [noir.session :as session]
            [ring.util.response :as response])
  (:use  [hiccup.form :refer :all] 
         [db.database :only [update-type-on-register]]))

(defn test []
  (layout/common [:h1 "Questions!"]
                (form-to [:post "/getAnswers"]
                         [:div.reg
                          "Da li dosta svog vremena provodite na racunaru?"
                         [:div
                           (drop-down :answer1 [:1 :2 :3 :4])]
                          "Direktan rad sa ljudima, koji podrazumeavju pruzanje neke ugostiteljske usluge mi deluje zanimljivo i ispunjujuce??"
                         [:div
                           (drop-down :answer2 [:1 :2 :3 :4])]
                            "Privlaci me primena administrativnih propisa na praspolaganje finansijskim sredstvima?"
                         [:div
                           (drop-down :answer3 [:1 :2 :3 :4])]
                            "Vise bih volela da se bavim prakticnim i konkretnim zadacima, a ne da ucim puno teorije?"
                         [:div
                           (drop-down :answer4 [:1 :2 :3 :4])]
                            "Sa lakocom resavam slozene matematicke formule i razumem njihovu primenu u fizici i hemiji?"
                         [:div
                           (drop-down :answer5 [:1 :2 :3 :4])]
                            "Koliko vas privlace sastanci?"
                         [:div
                           (drop-down :answer6 [:1 :2 :3 :4])]
                           [:div
                           (submit-button "Save item")]
              
  
          ])
             
))

(defn setAnswers 
  [answer1 answer2 answer3 answer4 answer5 answer6]
  (println "dosao")
  (do
 (let [sum (+ ( Integer/valueOf answer1) (Integer/valueOf answer2) (Integer/valueOf answer3) (Integer/valueOf answer4) (Integer/valueOf answer5) (Integer/valueOf answer5))
       register-user (session/get :register-user)]
   (println "suma")
   (println sum)
   (let [ register-type  ( cond
                             (<  (Integer/valueOf sum) 7) "low manager"
                             (< (Integer/valueOf sum) 13) "high manager"
                             (< (Integer/valueOf sum) 19) "low programer"
                             :else "high programer")]
     (update-type-on-register register-type)
     (session/put! :user register-user)
     (session/remove! :admin)
     )
   
   )
 
     (response/redirect "/main")
                    )
 )

(defroutes question-routes
  (GET "/test" [] (test))
  (POST "/getAnswers" [answer1 answer2 answer3 answer4 answer5 answer6] (setAnswers answer1 answer2 answer3 answer4 answer5 answer6))

  )