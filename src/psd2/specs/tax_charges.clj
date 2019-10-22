(ns psd2.specs.tax-charges
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            )
  (:import (java.io File)))


(def tax-charges-data
  {
   (ds/opt :identification) string?
   (ds/opt :rate) float?
   (ds/opt :amount) amount-type-spec
   })

(def tax-charges-spec
  (ds/spec
    {:name ::tax-charges
     :spec tax-charges-data}))
