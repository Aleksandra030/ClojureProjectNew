(ns clojureproject.routes.register
  (:require [compojure.core :refer :all]
            [clojureproject.views.layout :as layout]
              [ring.util.response :as response]
              [noir.session :as session])
  (:use  [hiccup.form]
         [hiccup.element :only (link-to)]
          [db.database :only [insert-user get-user-by-username]]))

(defn register []
  (layout/common [:h1 "Hello World!"]
                [:div.reg
              
                 (form-to [:post "/save"]
                          [:div.registerform
                          [:div 
                           (label {:class "registerlabel"} :username "Name: ")  (text-field :name )]
                          [:div
                           (label {:class "registerlabel"} :password "Last name: ") (text-field :lastName)]
                         [:div
                           (label {:class "registerlabel"} :password "Mail: ") (text-field :mail)]
                          [:div 
                           (label {:class "registerlabel"} :username "Username: ")  (text-field :username )]
                          [:div
                           (label {:class "registerlabel"} :password "Password: ") (password-field :password)]
             
                          [:div
                           (submit-button "Save")]]
                          )
           ]))

(defn register-action
  [name lastName mail username password]
   (do
       (insert-user name lastName mail username password)
       (let [register-user (get-user-by-username username)]
         (do (session/put! :register-user register-user)
              (response/redirect "/test")
              
         ))
                    ))
                  
(defroutes register-routes
  (GET "/register" [] (register))
  (POST "/save" [name lastName mail username password] (register-action name lastName mail username password))
  )