(ns hospitals.core
  (:use clojure.pprint))

(defn adiciona-paciente
  "Os pacientes são um mapa da seguinte forma {15 {paciente 15}, 25 {paciente 25} }"
  [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "Paciente não possui id" {:paciente paciente}))))

(defn testa-uso-de-pacientes
  []
  (let [pacientes {}
        max {:id 23, :nome "Max", :nascimento "23/02/1996"}
        miu {:nome "Miu", :nascimento "19/06/1999"}]
    (pprint (adiciona-paciente pacientes max))
    (pprint (adiciona-paciente pacientes miu))))

;(testa-uso-de-pacientes)

(defrecord Paciente [id, nome, nascimento])

(pprint (->Paciente 15 "Max" "23/02/1996"))                 ;obrigatório passar todos parametros
(pprint (Paciente. 15 "Max" "23/02/1996"))                  ; "." chama construtor

(map->Paciente {:id 15, :nome "Max", :nascimento "23/02/1996"}) ;opcional passar todo parametro

(let [max (->Paciente 15 "Max" "23/02/1996")]
  (println (:id max))
  (println (vals max)))

(pprint (assoc (Paciente. nil "Max" "23/02/1996") :id 23))