(ns hospital.core
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

(let [hospital-hidari (h.model/new-hospital)]
  (pprint hospital-hidari))