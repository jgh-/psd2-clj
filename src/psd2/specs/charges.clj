(ns psd2.specs.charges
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.charges-record :refer :all]
            )
  (:import (java.io File)))


(def charges-data
  {
   (ds/opt :totalChargesAndTaxAmount) amount-type-spec
   (ds/opt :record) (s/coll-of charges-record-spec)
   })

(def charges-spec
  (ds/spec
    {:name ::charges
     :spec charges-data}))
