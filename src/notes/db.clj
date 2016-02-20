(ns notes.db
  (:require [clojure.java.jdbc :as sql]))

(def spec 
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//127.0.0.1:3306/notes"
   :user "laki"
   :password "laki"})

(defn all []
  (into [] (sql/query spec ["select * from note order by date desc"])))

(defn create [shout]
  (sql/insert! spec :note [:body] [shout]))