(ns notes.view
  (:require [notes.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn note-form []
  [:div 
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "note" "New note:")
                 (form/text-field "note")
                 (form/submit-button "save"))])

(defn display-notes [notes]
  [:div {:class "all"}
   [:table {:class "table"} ;(:style "border: 0; width: 90%")
         [:th {:class "header"} "id"]
         [:th {:class "header"} "date"]
         [:th {:class "header"} "note"]
   (map 
    (fn [note][:tr {:class "row"} 
         [:td {:class "cell"} (h (:id note))]
         [:td {:class "cell"} (h (:date note))]
         [:td {:class "cell"} (h (:text note))]
      ]) notes)]])

  
   
(defn index [notes]
  (layout/common "My notes"
                 (display-notes notes)
                 (note-form)))