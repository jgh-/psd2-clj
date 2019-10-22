(ns psd2.specs.transaction
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.credit-debit-indicator :refer :all]
            [psd2.specs.amount-and-currency-exchange :refer :all]
            [psd2.specs.transaction-status :refer :all]
            [psd2.specs.bank-transaction-code :refer :all]
            [psd2.specs.charges :refer :all]
            [psd2.specs.related-parties :refer :all]
            [psd2.specs.remittance-information :refer :all]
            )
  (:import (java.io File)))


(def transaction-data
  {
   (ds/opt :resourceId) string?
   (ds/opt :entryReference) string?
   (ds/req :transactionAmount) amount-type-spec
   (ds/req :creditDebitIndicator) credit-debit-indicator-spec
   (ds/opt :transactionAmountDetails) amount-and-currency-exchange-spec
   (ds/req :status) transaction-status-spec
   (ds/opt :expectedBookingDate) inst?
   (ds/opt :bookingDate) inst?
   (ds/opt :valueDate) inst?
   (ds/opt :transactionDate) inst?
   (ds/opt :bankTransactionCode) bank-transaction-code-spec
   (ds/opt :charges) charges-spec
   (ds/opt :relatedParties) related-parties-spec
   (ds/opt :remittanceInformation) remittance-information-spec
   (ds/opt :additionalTransactionInformation) string?
   })

(def transaction-spec
  (ds/spec
    {:name ::transaction
     :spec transaction-data}))
