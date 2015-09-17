(ns clojureproject.views.layoutMainPage
  (:require [noir.session :as session])
  (:require [hiccup.page :refer [html5 include-css]]))


(defn user-menu
  "Generates the menu for all pages when the user is not logged in."
  []
  [:header
   [:div.header_inner
    [:ul#nav 
	   [:li 
	    [:a {:href "/test"} "Home"]]
     [:li 
	    [:a {:href "/aboutus"} "Plan to learn"]]
      [:div.top_header
     [:a {:href "/"} "Logout"]]]
    ]
   ])


(defn admin-menu
  "Generates the menu for all pages when the admin is logged in."
  []
  [:header
   [:div.header_inner
    [:ul#nav 
	   [:li 
	    [:a {:href "/"} "Home"]]
     [:li 
      [:a {:href "/add"} "Add"]]
     [:li 
	    [:a {:href "/users"} "Users"]]]
    [:div.top_header
     [:a {:href "/"} "Logout"]]
    ]
   ])


(defn common [& content]
  (html5
    [:head
     [:title "Welcome to clojureproject"]
     (include-css "/css/screen.css")]
    [:body 
     (let [admin (session/get :admin), user (session/get :user)] 
         (if user (user-menu) (admin-menu)))
          [:div.main content]
       ]))