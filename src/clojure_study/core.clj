(ns clojure-study.core)


(defn conta
  [total-ate-agora elementos]
  (if (seq elementos)
  (recur (inc total-ate-agora) (rest elementos))
  total-ate-agora))

(println (conta 0 ["Dani", "Gui", "Carlos", "Paulo", "Lucia", "Ana"]))

(defn minha-funcao
  ([parametro1] (println "p1" parametro1))
   ([parametro1 parametro2] (println "p2" parametro1 parametro2)))

(minha-funcao 1)
(minha-funcao 1 2)

(defn conta
  ([elementos]
   (conta 0 elementos))

  ([total-ate-agora elementos]
  (if (seq elementos)
    (recur (inc total-ate-agora) (rest elementos))
    total-ate-agora)))

(println (conta ["Dani", "Gui", "Carlos", "Paulo", "Lucia", "Ana"]))
(println (conta []))