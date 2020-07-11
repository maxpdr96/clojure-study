(ns hospitals.aula2
  (:use clojure.pprint)
  )

(defrecord PacienteParticular [id, nome, nascimento])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, plano])

;(defrecord PacienteParticular [id, nome, nascimento]
;  Cobravel
;  (deve-assinar-pre-autorizacao? [paciente, procedimento, valor]
;    (>= valor 50)))

(defprotocol Cobravel (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente, procedimento, valor]
    (>= valor 50)))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente, procedimento, valor]
    (let [plano (:plano paciente)]
      (not (some #(= % procedimento) plano)))))

(let [particular (->PacienteParticular 30, "Max", "23/2/1996")
      plano (->PacientePlanoDeSaude 15, "Max", "23/02/1996", [:raio-x, :ultrasom])]
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 500))
  (pprint (deve-assinar-pre-autorizacao? plano, :raio-x, 4353454))
  )

(defprotocol Dateable
  (to-ms [this]))

(extend-type java.lang.Number
  Dateable
  (to-ms [this] this))

(extend-type java.util.Date
  Dateable
  (to-ms [this] (.getTime this)))

(extend-type java.util.Calendar
  Dateable
  (to-ms [this] (to-ms (.getTime this))))

(pprint (to-ms (java.util.Date.)))

(pprint (to-ms (java.util.GregorianCalendar.)))