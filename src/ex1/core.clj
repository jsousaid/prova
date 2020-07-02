(ns ex1.core
  (:gen-class))

(defn- multiplode-de-5?
  [n]
  (zero? (mod n 5)))

(defn- proximo-multiplo-de-5
  [n]
  (if (multiplode-de-5? (inc n))
    (inc n)
    (recur (inc n))))

(defn- diferenca-menor-que-3?
  [n]
  (let [proximo-multiplo (proximo-multiplo-de-5 n)]
    {:menor? (< (- proximo-multiplo n) 3) :proximo proximo-multiplo}))

(defn- get-quantidade-notas
  []
  (println "digite quantidade de notas:")
  (loop [quantidade (read-string (do (flush) (read-line)))]
    (if (and (number? quantidade) (>= quantidade 0) (<= quantidade 60))
      quantidade
      (recur (get-quantidade-notas)))))

(defn- get-nota
  []
  (loop [nota-digitada (read-string (do (flush) (read-line)))]
    (if (and (number? nota-digitada) (>= nota-digitada 0) (<= nota-digitada 100))
      nota-digitada
      (recur (get-nota)))))

(defn- get-notas
  [quantidade]
  (println "digite as " quantidade " notas")
  (doall (repeatedly quantidade get-nota)))

(defn get-nova-nota
  [nota]
  (if (< nota 38)
    nota
    (let [{:keys [menor? proximo]} (diferenca-menor-que-3? nota)]
      (if menor?
        proximo
        nota))))

(defn -main
  [& args]
  (let [quantidade-de-notas (get-quantidade-notas)
        notas (get-notas quantidade-de-notas)
        novas-notas (map get-nova-nota notas)]
    (println "Saida:")
    (doseq [nova-nota novas-notas]
      (prn nova-nota))))