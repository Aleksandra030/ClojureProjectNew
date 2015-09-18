(ns clojureproject.routes.add
    (:require [compojure.core :refer :all]
              [clojureproject.views.layoutMainPage :as layout]
              [noir.session :as session]
              [ring.util.response :as response])
  (:use  [hiccup.form :refer :all]
         [db.database :only [insert-item]]
      ))

(defn add []
  (layout/common [:h1 "ADD ITEM!"]
                 (form-to [:post "/saveItem"]
                      [:div.addForm 
                          [:div 
                           (label {:class "addlabel"} :item "Item: ")  (text-field :item )]
                          [:div
                           (label {:class "addlabel"} :coefficient "Coefficient: ") (text-field :coefficient)]
                          [:div
                           (label {:class "addlabel"} :type "Type: ") (drop-down :type [:programming :management])]
                         [:div
                           (submit-button "Save item")]]
                      )
                 )
  )

(defn saveItem 
  [item coefficient type]
   (do
       (insert-item item coefficient type)
        (response/redirect "/main")
        )
   )
                  
(defroutes add-routes
  (GET "/add" [] (add))
  (POST "/saveItem"  [item coefficient type] (saveItem item coefficient type)))