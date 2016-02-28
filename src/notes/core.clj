(ns notes.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [notes.view :as view]
            [notes.db :as db]))

(defn index []
  (view/index (db/all)))

(defn create [note]
  (when-not (str/blank? note)
    (db/create note))
  (ring/redirect "/"))

(defn update [id]
  (when-not (str/blank? (str id ""))
    (view/update id)))

(defn save-update [id & text]
  (when-not (str/blank? (str id ""))
    (db/update id text))
  (ring/redirect "/"))

(defn delete [id]
  (when-not (str/blank? id)
    (db/delete id))
  (ring/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (POST "/" [note] (create note))
  (POST "/update/" [id & other] (save-update id (get other :new-text)))
  (GET "/update/:id" [id] (update id))
  (GET "/delete/:id" [id] (delete id)))