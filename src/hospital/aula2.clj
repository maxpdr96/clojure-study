(ns hospital.aula2
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

; simbolo que qualquer thread que acessar esse namespace vai ter acesso a ele como valor padrão
(def nome "Max")


; deref pode ser escrito com @
(defn testa-atomao []
  (let [hospital-teste (atom {:espera h.model/empty-queue})]
    (println hospital-teste)
    (pprint hospital-teste)
    (pprint (deref hospital-teste))
    (pprint @hospital-teste)

    (pprint (assoc @hospital-teste :laboratorio1 h.model/empty-queue))
    (pprint @hospital-teste)

    ;maneira de alterar conteudo dentro de um atomo
    (swap! hospital-teste assoc :laboratorio1 h.model/empty-queue)
    (pprint @hospital-teste)

    (swap! hospital-teste assoc :laboratorio2 h.model/empty-queue)
    (pprint @hospital-teste)

    ;update tradicional imutavel, com deref, que nao trara efeito
    (update @hospital-teste :laboratorio1 conj "111")

    ;indo para swap
    (swap! hospital-teste update :laboratorio1 conj "111")
    (pprint hospital-teste)

    )

)

;(testa-atomao)

(defn chega-em-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado-logando :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/new-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    )

  )

(simula-um-dia-em-paralelo)