(ns clojureproject.routes.main
    (:require [compojure.core :refer :all]
            [clojureproject.views.layoutMainPage :as layout]
              [ring.util.response :as response]
              [noir.session :as session])
  (:use  [hiccup.form]
         [hiccup.element :only (link-to)]
         [db.database :only [get-all-item delete-item]]))



(defn make-button-admin [id]
    (form-to [:delete  "/delete-item"]
           [:div
            (hidden-field :id id)
           (submit-button "Delete")]
           )
  )
(defn make-button-user [id]
    (form-to [:delete  "/calculate"]
           [:div
            (hidden-field :id id)
           (submit-button "Calculate")]
           )
  )

(defn make-delete-button [id]
   (println id)
   (let [admin (session/get :admin), user (session/get :user)] 
         (if user (make-button-user id) (make-button-admin id)))

           )

(defn show-list []
  [:table.items
   [:tr
    [:th "Id"]
    [:th "Item name"]
    [:th "Coefficient"]
    [:th "Type"]
    [:td ""]
    [:td ]]
   (for [{:keys [ _id item coefficient type]} (get-all-item)]
     [:tr
      [:td _id]
      [:td item]
      [:td coefficient]
      [:td type]
      [:td (make-delete-button _id)]
    ])])

(defn main []
  (layout/common [:h1 "MAIN PAGE!"]
          (list 
      [:h2 "Item list"]
      [:br][:br]
      (show-list))
          ))

 (defn delete-item-form-table
  [id]
   (do
       (delete-item id)
        (response/redirect "/main"))
                    )
 

(defroutes main-routes
  (GET "/main" [] (main))
   (DELETE "/delete-item" [id] (delete-item-form-table id))
   (POST "/calculate" [id] (delete-item-form-table id))
  )