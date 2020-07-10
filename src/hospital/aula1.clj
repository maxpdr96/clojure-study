(ns hospital.aula1
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

(defn simula-um-dia []
  ;root binding
  (def hospital (h.model/new-hospital))
  (h.logic/chega-em hospital :espera "111")
  (h.logic/chega-em hospital :espera "222")
  (h.logic/chega-em hospital :espera "333")
  (pprint hospital)

  (h.logic/atende hospital :laboratorio1)
  (h.logic/atende hospital :espera)

  )


;(simula-um-dia)

(defn chega-em-bad [pessoa]
  (def hospital (h.logic/chega-em hospital :espera pessoa))
  (println "apos inserir" pessoa)
  )

(defn simula-um-dia-em-paralelo
  []
  (def hospital (h.model/new-hospital))
  (.start (Thread. (fn [] (chega-em-bad "111"))))
  (.start (Thread. (fn [] (chega-em-bad "222"))))
  (.start (Thread. (fn [] (chega-em-bad "333"))))
  (.start (Thread. (fn [] (chega-em-bad "444"))))
  (.start (Thread. (fn [] (chega-em-bad "555"))))
  (.start (Thread. (fn [] (chega-em-bad "666"))))
  (.start (Thread. (fn [] (Thread/sleep 4000)
                           (pprint hospital))))
  )

(simula-um-dia-em-paralelo)