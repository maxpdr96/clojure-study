(ns hospital.model)

(def empty-queue clojure.lang.PersistentQueue/EMPTY)

(defn new-hospital []
  {:espera empty-queue
   :laboratorio1 empty-queue
   :laboratorio2 empty-queue
   :laboratorio3 empty-queue})