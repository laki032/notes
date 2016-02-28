(ns notes.view
  (:require [notes.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [clojure.string :as str]
            [ring.util.anti-forgery :as anti-forgery]))

(defn note-form []
  [:div (form/form-to [:post "/"]
        (anti-forgery/anti-forgery-field)
        (form/label "note" "New note: ")
        (form/text-field "note")
        (form/submit-button "save"))])

(defn display-notes [notes]
  [:div 
   [:table {:class "table"}
         [:th {:class "header"} "id"]
         [:th {:class "header"} "date"]
         [:th {:class "header"} "note"]
         [:th {:class "header"} "update"]
         [:th {:class "header"} "delete"]
   (map 
    (fn [note][:tr {:class "row"} 
         [:td {:class "cell"} (h (:id note))]
         [:td {:class "cell"} (h (:date note))]
         [:td {:class "cell"} (h (:text note))]
         [:td {:class "cell"} [:a {:href (str "/update/" (h (:id note)))} "update"]]
         [:td {:class "cell"} [:a {:href (str "/delete/" (h (:id note)))} "delete"]]
      ]) notes)]])

(defn index [notes]
  (layout/common "My notes"
                 (display-notes notes)
                 (note-form)))

(defn update-form [id]
  [:div 
   (form/form-to [:post (str "/update/" id)]
                 (anti-forgery/anti-forgery-field)
                 (form/label "note" "New text: ")
                 (form/text-field "note")
                 (form/submit-button "update"))])

(defn display-note [note]
  [:div (map (fn [note]
      [:h3 (str "note with id: " (h (:id note)))
       (str "<br/>")
       (str "created on: " (h (:date note)))
       (str "<br/>")
       (str "previous text: " (h (:text note)))
       (str "<br/>")
       ]) note)])

(defn update [note]
  (layout/common "Update note" 
                 (update-form (get note :id))
                 (display-note note)))