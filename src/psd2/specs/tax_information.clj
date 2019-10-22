(ns psd2.specs.tax-information
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.tax-party :refer :all]
            [psd2.specs.tax-party :refer :all]
            [psd2.specs.tax-party :refer :all]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.tax-record :refer :all]
            )
  (:import (java.io File)))


(def tax-information-data
  {
   (ds/opt :creditor) tax-party-spec
   (ds/opt :debtor) tax-party-spec
   (ds/opt :ultimateDebtor) tax-party-spec
   (ds/opt :administrationZone) string?
   (ds/opt :referenceNumber) string?
   (ds/opt :method) string?
   (ds/opt :totalTaxableBaseAmount) amount-type-spec
   (ds/opt :totalTaxAmount) amount-type-spec
   (ds/opt :date) inst?
   (ds/opt :sequenceNumber) float?
   (ds/opt :record) (s/coll-of tax-record-spec)
   })

(def tax-information-spec
  (ds/spec
    {:name ::tax-information
     :spec tax-information-data}))
