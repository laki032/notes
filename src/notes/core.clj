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
    (view/update (db/get-by-id id))))

(defn save-update [id & n]
  (when-not (str/blank? id)
    (db/update id n))
  (ring/redirect "/"))

(defn delete [id]
  (when-not (str/blank? id)
    (db/delete id))
  (ring/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (POST "/" [note] (create note))
  (POST "/update/:id" [id & note] (save-update id note))
  (GET "/update/:id" [id] (update id))
  (GET "/delete/:id" [id] (delete id)))