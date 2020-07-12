(ns hospitals.aula3
  (:use clojure.pprint)
  (:require [hospitals.logic :as h.logic]))

(defn carrega-paciente [id]
  (println "Carregando" id)
  (Thread/sleep 1000)
  {:id id, :carregado-em (h.logic/agora)})


(pprint (carrega-paciente 15))
