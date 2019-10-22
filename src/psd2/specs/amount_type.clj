(ns psd2.specs.amount-type
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def amount-type-data
  {
   (ds/req :amount) float?
   (ds/req :currency) string?
   })

(def amount-type-spec
  (ds/spec
    {:name ::amount-type
     :spec amount-type-data}))
