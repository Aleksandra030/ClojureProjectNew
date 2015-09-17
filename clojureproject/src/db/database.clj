(ns db.database
  (:require [noir.session :as session]
            [clj-time.format :as time-format]
            [clj-time.core :as time])
  (:use [somnium.congomongo]))

(def conn 
  (make-connection "baseNew"))

(set-connection! conn)

(defn- generate-id [collection]
  "Generate entity identifier." 
  (:seq (fetch-and-modify :sequences {:_id collection} {:$inc {:seq 1}}
                          :return-new? true :upsert? true)))

(defn- insert-entity [collection values]
   "Insert an entity into database."
  (insert! collection (assoc values :_id (generate-id collection))))

(defn insert-user
  [name lastName mail username password]
  "Insert user into database." 
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
    
    
(defn get-user-by-username [username]
  "Find user by username."  
  (fetch-one :users :where {:username username}))

(defn get-admin-by-username [username]
  "Find admin by username."  
  (fetch-one :admin :where {:username username}))

(defn get-item-by-id [id]
  "Find item by id."  
  (fetch-one :items :where {:_id (Integer/valueOf id)}))

(defn get-all-item []
  (fetch :items))

(defn delete-item [id]
  "Delete items from the database."
  (println id)
  (destroy! :items {:_id (Integer/valueOf id)}))

(defn update-type-on-register
  [type]
  "Update register user"
  (let [register-user  (session/get :register-user)
        id (:_id register-user)
        name (:name register-user)
        lastName (:lastName register-user)
        mail (:mail register-user)
        password (:password register-user)
        username (:username register-user)]
    
   (fetch-and-modify :users {:_id id} {:_id id :name name :lastName lastName :mail mail :password password :username username :type type})))
