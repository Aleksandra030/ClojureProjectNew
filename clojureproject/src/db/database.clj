(ns db.database
  (:require [noir.session :as session]
            [clj-time.format :as time-format]
            [clj-time.core :as time])
  (:use   [somnium.congomongo]))


;; ----- Database connection -----
(def conn 
  (make-connection "baseNew"))

(set-connection! conn)

;; ----- Helper functions -----


;; ----- Find admin by username -----
(defn get-admin-by-username [username] 
  (println username)
  (fetch-one :admin :where {:username username}))

;; ----- Find item by id -----
(defn get-item-by-id [id]
  (fetch-one :items :where {:_id (Integer/valueOf id)}))

;; ----- Query functions -----

(defn- generate-id [collection]
  "Generate entity identifier." 
  (:seq (fetch-and-modify :sequences {:_id collection} {:$inc {:seq 1}}
                          :return-new? true :upsert? true)))

;; ----- Insert an entity into databas -----
(defn- insert-entity [collection values]
  (insert! collection (assoc values :_id (generate-id collection))))

;; ----- Insert user into database -----
(defn insert-user
  [name lastName mail username password]
  (println "dodao")
  (insert-entity :users 
                  {:name name
                   :lastName lastName
                   :mail mail
                   :username username
                   :password password
                   :type ""}))

(defn insert-item
    [item coefficient type]
     (insert-entity :items 
                  {:item item
                   :coefficient coefficient
                   :type type}))

;; ----- Find user by username -----
(defn get-user-by-username [username]  
  (fetch-one :users :where {:username username}))

(defn get-all-item []
  (fetch :items))

;; ----- Delete items from the database -----
(defn delete-item [id]
  (println id)
  (destroy! :items {:_id (Integer/valueOf id)}))

;; ----- Update register user -----
(defn update-type-on-register
  [type]
  
  (let [register-user  (session/get :register-user)
     id (:_id register-user)
     name (:name register-user)
     lastName (:lastName register-user)
     mail (:mail register-user)
     password (:password register-user)
     username (:username register-user)]
   (fetch-and-modify :users {:_id id} {:_id id :name name :lastName lastName :mail mail :password password :username username :type type})))
