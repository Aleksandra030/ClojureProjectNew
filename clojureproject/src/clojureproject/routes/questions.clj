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
                         [:div.reg2
                          "I tend to spend a lot of time in front of a computer."
                         [:div
                           (drop-down :answer1 [:1 :2 :3 :4])]
                          "Working with people, which represents giving catering services appears interesting and fulfilling to me."
                         [:div
                           (drop-down :answer2 [:1 :2 :3 :4])]
                            "I find it interesting to use various administrative regulations and to be in charge of financial funds."
                         [:div
                           (drop-down :answer3 [:1 :2 :3 :4])]
                            "I would rather work on pragmatic tasks and solve real life problems in practice than dealing with a lot of theory."
                         [:div
                           (drop-down :answer4 [:1 :2 :3 :4])]
                            "I can solve complex mathematical formulas and understand their usage in physics and chemistry with ease."
                         [:div
                           (drop-down :answer5 [:1 :2 :3 :4])]
                            "I find company meetings interesting "
                         [:div
                           (drop-down :answer6 [:1 :2 :3 :4])]
                           [:div
                           (submit-button "Save item")]])))

(defn setAnswers 
  [answer1 answer2 answer3 answer4 answer5 answer6]
  (do
    (let [sum (+ ( Integer/valueOf answer1) (Integer/valueOf answer2) (Integer/valueOf answer3) (Integer/valueOf answer4) (Integer/valueOf answer5) (Integer/valueOf answer5))
          register-user (session/get :register-user)]
      (println sum)
      (let [ register-type  ( cond
                             (<  (Integer/valueOf sum) 7) "low manager"
                             (< (Integer/valueOf sum) 13) "high manager"
                                (< (Integer/valueOf sum) 19) "low programer"
                             :else "high programer")]
        (update-type-on-register register-type)
        (session/put! :user register-user)
        (session/remove! :admin)))
        (response/redirect "/main")))

(defroutes question-routes
  (GET "/test" [] (test))
  (POST "/getAnswers" [answer1 answer2 answer3 answer4 answer5 answer6] (setAnswers answer1 answer2 answer3 answer4 answer5 answer6)))