(ns notes.layout
  (:require [hiccup.page :as h]))

(defn common [title & body]
  (h/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content
            "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title "My notes"]
    (h/include-css "/stylesheets/style.css")]
   [:body
    [:div {:id "header"}
     [:h1 {:class "container"} "All notes: "]]
    [:div {:id "content" :class "container"} body]]))

(defn error []
  (common [:div {:id "error"} "The page you requested could not be found"]))