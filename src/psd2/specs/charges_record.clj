(ns psd2.specs.charges-record
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.credit-debit-indicator :refer :all]
            [psd2.specs.code-and-issuer :refer :all]
            [psd2.specs.charge-bearer-code :refer :all]
            [psd2.specs.financial-institution-identification :refer :all]
            [psd2.specs.tax-charges :refer :all]
            )
  (:import (java.io File)))


(def charges-record-data
  {
   (ds/opt :amount) amount-type-spec
   (ds/opt :creditDebitIndicator) credit-debit-indicator-spec
   (ds/opt :chargeIncludedIndicator) boolean?
   (ds/opt :code) code-and-issuer-spec
   (ds/opt :rate) float?
   (ds/opt :bearer) charge-bearer-code-spec
   (ds/opt :agent) financial-institution-identification-spec
   (ds/opt :tax) tax-charges-spec
   })

(def charges-record-spec
  (ds/spec
    {:name ::charges-record
     :spec charges-record-data}))
