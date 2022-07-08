(ns wanderweg-navigator.core
  (:require [clojisr.v1.r :as r :refer [r eval-r->java r->java java->r java->clj java->native-clj clj->java r->clj clj->r ->code r+ colon require-r]]
            [tech.v3.dataset :as ds]
            [clojisr.v1.applications.plotting :refer [plot->svg plot->file plot->buffered-image]]))

(require-r '[graphics :refer [plot hist]])
(require-r '[ggplot2 :refer [ggplot aes geom_point geom_polygon xlab ylab labs]])


(defn to-dataframe [l]
  (->> l
       (map (fn [[x y]] {:long x :lat y}))
       ds/->dataset
       clj->r))

(defn plot [coords]
  (-> (ggplot)
      (r/r+ (geom_polygon :data (to-dataframe coords)
                          (aes :x 'long :y 'lat)
                          :fill "#eeeeee" :color "#000000" :size 0.2))))
(defn -main [& args]
  (println (r '(+ 1 2))))

