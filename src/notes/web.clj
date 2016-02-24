(ns notes.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [notes.core :as core]
            [notes.layout :as layout]))

(defroutes routes
  core/routes
  (route/resources "/")
  (route/not-found (layout/error)))

(def app
    (wrap-defaults routes site-defaults))