(ns hospitals.logic
  (:require [hospitals.model :as h.model]))

(defn agora []
  (h.model/to-ms (java.util.Date.)))
