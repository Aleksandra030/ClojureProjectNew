(ns clojureproject.views.layoutMainPage
  (:require [noir.session :as session])
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to clojureproject"]
     (include-css "/css/screen.css")]
    [:body body
       (let [admin (session/get :admin), user (session/get :user)] 
         (if-not admin (if-not user (not-logged-in-menu) (user-logged-in-menu)) (logged-in-menu)))
       
         [:div#content
          [:div.content_inner
           (init-slider)
	          content
           ]
          ]]))