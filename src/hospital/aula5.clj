(ns hospital.aula5
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))


(defn cabe-na-fila? [fila]
  (-> fila
      count
      (< 5)))

(defn chega-em
  [fila pessoa]
  (if (cabe-na-fila? fila)
    (conj fila pessoa)
    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))))

(defn chega-em!
  "troca de referencia via ref-set"
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (ref-set fila (chega-em @fila pessoa))))

(defn chega-em!
  "troca de rerencia via alter"
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))

(defn simula-um-dia
  []
  (let [hospital {:espera       (ref h.model/empty-queue)
                  :laboratorio1 (ref h.model/empty-queue)
                  :laboratorio2 (ref h.model/empty-queue)
                  :laboratorio3 (ref h.model/empty-queue)}]
    (dosync (chega-em! hospital "guilherme"))
    (pprint hospital)))

;(simula-um-dia)

(defn async-chega-em!
  [hospital pessoa]
  (future
    (Thread/sleep (rand 5000))
    (dosync
      (println "Tentando o cdg sincronizado" pessoa)
      (chega-em! hospital pessoa))))

(defn simula-um-dia-async
  []
  (let [hospital {:espera       (ref h.model/empty-queue)
                  :laboratorio1 (ref h.model/empty-queue)
                  :laboratorio2 (ref h.model/empty-queue)
                  :laboratorio3 (ref h.model/empty-queue)}]
    (def futures (mapv #(async-chega-em! hospital %) (range 10)))
    (future
      (dotimes [n 4]
        (Thread/sleep 2000)
        (pprint hospital)
        (pprint futures)))
    ))

(simula-um-dia-async)