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
         [:th {:class "header"} "delete"]
         [:th {:class "header"} "update"]
   (map 
    (fn [note][:tr {:class "row"} 
         [:td {:class "cell"} (h (:id note))]
         [:td {:class "cell"} (h (:date note))]
         [:td {:class "cell"} (h (:text note))]
         [:td {:class "cell"} [:a {:href (str "/delete/" (h (:id note)))} "delete"]]
         [:td {:class "cell"} [:a {:href (str "/update/" (h (:id note)))} "update"]]
      ]) notes)]])

(defn index [notes]
  (layout/common "My notes"
                 (display-notes notes)
                 (note-form)))

(defn update [id]
  (layout/common "Update note" 
                 [:div (form/form-to 
                         [:post "/saveupdate/"]
				                 (form/label "note" "New text: ")
				                 (form/text-field "note")
				                 (form/submit-button "update")
                         (form/hidden-field "id" (h id))
                         (anti-forgery/anti-forgery-field))]))
