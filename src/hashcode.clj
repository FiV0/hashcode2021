(ns hashcode
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def files ["a.in" "b.in" "c.in" "d.in" "e.in"])

(defn parse-int-line [str-line]
  (->> (str/split str-line #" " )
       (map #(Long/parseLong %))
       vec))

(defn parse-str-line [str-line]
  (str/split str-line #" "))

(defn parse-file [file]
  (with-open [rdr (io/reader file)]
    (let [data (doall (line-seq rdr))]
      data)))

(doseq [file files]
  (parse-file (io/resource file)))
