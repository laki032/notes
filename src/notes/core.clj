(ns notes.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [notes.view :as view]
            [notes.db :as db]))

(defn index []
  (view/index (db/all)))

(defn create
  [note]
  (when-not (str/blank? note)
    (db/create note))
  (ring/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (POST "/" [note] (create note)))