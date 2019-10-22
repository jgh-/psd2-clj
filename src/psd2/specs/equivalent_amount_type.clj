(ns psd2.specs.equivalent-amount-type
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def equivalent-amount-type-data
  {
   (ds/req :amount) float?
   (ds/req :currency) string?
   (ds/req :currencyOfTransfer) string?
   })

(def equivalent-amount-type-spec
  (ds/spec
    {:name ::equivalent-amount-type
     :spec equivalent-amount-type-data}))
