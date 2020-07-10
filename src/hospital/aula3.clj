(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

(defn chega-em-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado-logando :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo-com-mapv
  "Simulação utilizando um mapv para forçar"
  []
  (let [hospital (atom (h.model/new-hospital))
        pessoas ["111", "222", "333", "444", "555", "666"]]

    (mapv #(.start (Thread. (fn [] (chega-em-malvado! hospital %)))) pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
   )
  )

;(simula-um-dia-em-paralelo)

(defn simula-um-dia-em-paralelo-com-mapv-refatorado
  []
  (let [hospital (atom (h.model/new-hospital))
        pessoas ["111", "222", "333", "444", "555", "666"]
        starta-thread-de-chegada #(.start (Thread. (fn [] (chega-em-malvado! hospital %))))]

    (mapv starta-thread-de-chegada pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    )
  )

(defn starta-thread-de-chegada
  ([hospital]
   (fn [pessoa] (starta-thread-de-chegada hospital pessoa)))
  ([hospital pessoa]
  (.start (Thread. (fn [] (chega-em-malvado! hospital pessoa)))))
  )


(defn simula-um-dia-em-paralelo-com-mapv-extraida
  []
  (let [hospital (atom (h.model/new-hospital))
        pessoas ["111", "222", "333", "444", "555", "666"]
        starta (starta-thread-de-chegada hospital)]

    (mapv starta pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    )
  )

;(simula-um-dia-em-paralelo-com-mapv-extraida)

(defn starta-thread-de-chegada-extraida
  [hospital pessoa]
   (.start (Thread. (fn [] (chega-em-malvado! hospital pessoa)))))


(defn simula-um-dia-em-paralelo-com-partial
  []
  (let [hospital (atom (h.model/new-hospital))
        pessoas ["111", "222", "333", "444", "555", "666"]
        starta (partial starta-thread-de-chegada-extraida hospital)]

    (mapv starta pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    )
  )

;(simula-um-dia-em-paralelo-com-partial)

(defn simula-um-dia-em-paralelo-com-doseq
  []
  (let [hospital (atom (h.model/new-hospital))
        pessoas (range 6)]

    (doseq [pessoa pessoas]
      (starta-thread-de-chegada-extraida hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    )
  )

;(simula-um-dia-em-paralelo-com-doseq)

(defn simula-um-dia-em-paralelo-com-dotimes
  []
  (let [hospital (atom (h.model/new-hospital))]

    (dotimes [pessoa 6]
      (starta-thread-de-chegada-extraida hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    )
  )

(simula-um-dia-em-paralelo-com-dotimes)