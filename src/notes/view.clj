(ns notes.view
  (:require [notes.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn note-form []
  [:div 
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "note" "New notes:")
                 (form/text-area "note")
                 (form/submit-button "insert"))])

(defn display-notes [notes]
  [:div
   (map
    (fn [note] [:h2 {:class "note"} (h (:body note))])
    notes)])

(defn index [notes]
  (layout/common "My notes"
                 (note-form)
                 [:div {:class "clear"}]
                 (display-notes notes)))