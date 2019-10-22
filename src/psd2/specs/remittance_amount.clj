(ns psd2.specs.remittance-amount
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.typed-amount :refer :all]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.typed-amount :refer :all]
            [psd2.specs.document-adjustment :refer :all]
            [psd2.specs.amount-type :refer :all]
            )
  (:import (java.io File)))


(def remittance-amount-data
  {
   (ds/opt :duePayableAmount) amount-type-spec
   (ds/opt :discountAppliedAmount) typed-amount-spec
   (ds/opt :creditNoteAmount) amount-type-spec
   (ds/opt :taxAmount) typed-amount-spec
   (ds/opt :adjustmentAmountAndReason) document-adjustment-spec
   (ds/opt :remittedAmount) amount-type-spec
   })

(def remittance-amount-spec
  (ds/spec
    {:name ::remittance-amount
     :spec remittance-amount-data}))
