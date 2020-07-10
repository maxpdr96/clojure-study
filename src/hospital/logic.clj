(ns hospital.logic)

(defn cabe-na-fila?
  [hospital departamento]
  (-> hospital
      (get,,, departamento)
      count,,,
      (<,,, 5)))




(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila ja está cheia" {:tentando-adicionar pessoa}))))

(defn chega-em-pausado
  [hospital departamento pessoa]
  (Thread/sleep (* (rand) 2000))
  (if (cabe-na-fila? hospital departamento)
    (do
      ;(Thread/sleep (* (rand) 2000))
      (update hospital departamento conj pessoa))
    (throw (ex-info "Fila ja está cheia" {:tentando-adicionar pessoa}))))

(defn chega-em-pausado-logando
  [hospital departamento pessoa]
  ; (println "Tentando adicionar a pessoa" pessoa)
  (Thread/sleep (* (rand) 2000))
  (if (cabe-na-fila? hospital departamento)
    (do
      ;(Thread/sleep (* (rand) 2000))
      ;(println "Dando update" pessoa)
      (update hospital departamento conj pessoa)
      )

    (throw (ex-info "Fila ja está cheia" {:tentando-adicionar pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))

(defn atende-completo
  [hospital departamento]
  {:paciente (update hospital departamento peek)
   :fila     (update hospital departamento pop)})

(defn atende-completo-chama-ambos
  [hospital departamento]
  (let [fila (get hospital departamento)
        peek-pop (juxt peek pop)
        [pessoa fila-atualizada] (peek-pop fila)
        hospital-atualizado (update hospital assoc departamento fila-atualizada)]
    {:paciente pessoa
     :hospital hospital-atualizado}))

(defn proxima
  "Retorna o proximo paciente da fila"
  [hospital departamento]
  (-> hospital
      departamento
      peek)


  (defn transfere
    "Transfere o proximo paciente da fila de para a fila para"
    [hospital de para]
    (let [pessoa (proxima hospital de)]
      (-> hospital
          (atende de)
          (chega-em para pessoa)))))