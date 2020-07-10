(ns clojure-study.basic)

;BASIC

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
   (let [desconto 0.10]
      (* valor-bruto (- 1 desconto))
   ))

(println (valor-descontado 100))

; PREDICATE

(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando versao IF")
  (if (> valor-bruto 100)
    true
    false))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente > que 100"
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))

(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando versao when")
  (when (> valor-bruto 100)
    true))

(println (valor-descontado 1000))

(defn aplica-desconto?
  [valor-bruto]
  (println "Chamando versao >")
  (> valor-bruto 100))

(println (valor-descontado 1000))

(defn mais-caro-que-100?
  [valor-bruto]
  (println "Invocando mais-caro-que-100:")
  (> valor-bruto 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se deve aplicar desconto"
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado mais-caro-que-100? 1000))

; Função anonima
(println "\nFunção anonima / Lambda:")
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 1000))
(println (valor-descontado (fn [v] (> v 100)) 1000))
(println (valor-descontado #(> %1 100) 1000))               ; 2 parametro = %2, 3 %3 ...
(println (valor-descontado #(> % 100) 1000))

; VETORES
(println "\nVetores:")
(def precos [30 700 1000])
(println "Valor padrão nil:" (get precos 3))
(println "Valor padrão 0:" (get precos 3 0))
(println "Valor padrão 0, mas existe:" (get precos 2 0))

(println (conj precos 5))                                   ; Adiciona valor no final, mas nao insere no vetor

(println (+ 5 1))
(println (inc 5))                                           ; Soma 1
(println (update precos 0 inc))                             ; Update valor na posicao 0 do vetor
(println (update precos 1 dec))                             ; Update valor na posicao 1 do vetor


(doseq [x [-1 0 1]
        y [1 2 3]]
  (println (* x y)))