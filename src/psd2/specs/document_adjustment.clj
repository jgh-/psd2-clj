(ns psd2.specs.document-adjustment
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.credit-debit-indicator :refer :all]
            )
  (:import (java.io File)))


(def document-adjustment-data
  {
   (ds/req :amount) amount-type-spec
   (ds/opt :creditDebitIndicator) credit-debit-indicator-spec
   (ds/opt :reason) string?
   (ds/opt :additionalInformation) string?
   })

(def document-adjustment-spec
  (ds/spec
    {:name ::document-adjustment
     :spec document-adjustment-data}))
