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
  (insert-entity :users 
                  {:name name
                   :lastName lastName
                   :mail mail
                   :username username
                   :password password}))

(defn get-user-by-username [username]
  "Find user by username."  
  (fetch-one :users :where {:username username}))

