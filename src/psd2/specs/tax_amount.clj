(ns psd2.specs.tax-amount
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.tax-record-details :refer :all]
            )
  (:import (java.io File)))


(def tax-amount-data
  {
   (ds/opt :rate) float?
   (ds/opt :taxableBaseAmount) amount-type-spec
   (ds/opt :totalAmount) amount-type-spec
   (ds/opt :details) (s/coll-of tax-record-details-spec)
   })

(def tax-amount-spec
  (ds/spec
    {:name ::tax-amount
     :spec tax-amount-data}))
