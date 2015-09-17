(ns clojureproject.routes.home
  (:require [compojure.core :refer :all]
            [clojureproject.views.layout :as layout]
            [noir.session :as session]
            [ring.util.response :as response])
  (:use  [hiccup.form :only [form-to label text-field password-field submit-button]]
           [db.database :only [get-user-by-username get-admin-by-username]]))


(defn clearSession []
  (session/remove! :admin)
  (session/remove! :user)
  (session/remove! :register-user)
  )
(defn home []
  (clearSession)
  (layout/common [:h1 "Hello World!"]
      (form-to [:post "/login"]
                          [:div.loginform 
                          [:div 
                (label {:class "loginlabel"} :username "Username: ")  (text-field :username )]
               [:div
                (label {:class "loginlabel"} :password "Password: ") (password-field :password)]
               [:div
                (submit-button "Log In")]]
               )
     (form-to [:get "/register"]
              [:div.loginform
               [:div
                (submit-button "Register")]]
              )))


(defn- validate-user
  "Check if the user with given username and passwored is valid."
  [user password]
    (cond
      (nil? user) "User with given username does not exist."
      (not= password (:password user)) "Password is not correct."
      :else true))

(defn- validate-admin
  "Check if the user with given username and passwored is valid."
  [admin password]
    (cond
      (nil? admin) "User with given username does not exist."
      (not= password (:password admin)) "Password is not correct."
      :else true))


(defn do-login
  "If username and password are correct, put the information into session that the admin is logged in."
  [username password]
    (let [
      user (get-user-by-username username)
      admin (get-admin-by-username username)
      error-user (validate-user user password)
      error-admin (validate-admin admin password)
      ]
    (cond
       (= true error-user) (do (session/put! :user user)(response/redirect "/main"))
       (= true error-admin) (do (session/put! :admin admin)(response/redirect "/main"))
      :else (do (session/flash-put! :login-error "User with given username and password does not exist.") (response/redirect "/")))))


(defroutes home-routes
  (GET "/" [] (home))
  (POST "/login" [username password] (do-login username password))
  )
