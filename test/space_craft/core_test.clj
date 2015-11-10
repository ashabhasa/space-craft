(ns space-craft.core-test
  (:require [clojure.test :refer :all]
            [space-craft.core :refer :all]))

(deftest spherical-coordinates-test
  (let [pos {:x 1.0 :y 2.0 :z 3.0}]
    (testing "roundtrip"
      (is (= pos (-> pos cartesian->spherical spherical->cartesian)))))
  (testing "spherical->cartesian"
    (is (= (spherical->cartesian {:r     2
                                  :phi   0
                                  :theta 0})
           {:x 0.0 :y 0.0 :z 2.0}))))


(deftest makes-orbit
  (let [trajectory (->> (atlas-v)
                        prepare
                        (trajectory 1))]
    (when (crashed? trajectory)
      (println "Crashed at" (crash-time trajectory) "seconds")

      (println "Maximum altitude" (apoapsis trajectory)
               "meters at" (apoapsis-time trajectory) "seconds")
      )
    (is (not (crashed? trajectory)))))