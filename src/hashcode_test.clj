(ns hashcode-test
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def files ["a.in" "b.in" "c.in" "d.in" "e.in"])

(defn parse-int-line [str-line]
  (->> (str/split str-line #" " )
       (map #(Long/parseLong %))
       vec))

(defn parse-str-line [str-line]
  (str/split str-line #" "))

(defn parse-pizza [line]
  (let [[nb & ings] (parse-str-line line)]
    (vec (concat (list (Long/parseLong nb)) ings))))

(defn parse-file [file]
  (with-open [rdr (io/reader file)]
    (let [[ints & rest] (line-seq rdr)
          ints (parse-int-line ints)
          pizzas (doall (map parse-pizza rest))]
      {:ints ints
       :pizzas pizzas})))

(parse-file (io/resource "testround/b.in"))

(doseq [file files]
  (parse-file (io/resource (str "testround/" file))))
