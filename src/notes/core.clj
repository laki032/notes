(ns notes.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]))

(defn index []
  "<h2>Hello World</h2>")

(defroutes app
  (GET "/" [] (index )))