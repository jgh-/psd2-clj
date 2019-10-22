(ns psd2.specs.credit-transfer-transaction
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.payment-identification :refer :all]
            [psd2.specs.execution-rule :refer :all]
            [psd2.specs.frequency-code :refer :all]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.equivalent-amount-type :refer :all]
            [psd2.specs.exchange-rate :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.intermediary-agent :refer :all]
            [psd2.specs.beneficiary :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.instruction-for-creditor-agent :refer :all]
            [psd2.specs.purpose-code :refer :all]
            [psd2.specs.remittance-information :refer :all]
            [psd2.specs.transaction-individual-status-code :refer :all]
            [psd2.specs.status-reason-information :refer :all]
            )
  (:import (java.io File)))


(def credit-transfer-transaction-data
  {
   (ds/req :paymentId) payment-identification-spec
   (ds/opt :requestedExecutionDate) inst?
   (ds/opt :endDate) inst?
   (ds/opt :executionRule) execution-rule-spec
   (ds/opt :frequency) frequency-code-spec
   (ds/opt :instructedAmount) amount-type-spec
   (ds/opt :equivalentAmount) equivalent-amount-type-spec
   (ds/opt :exchangeRateInformation) exchange-rate-spec
   (ds/opt :ultimateDebtor) party-identification-spec
   (ds/opt :intermediaryAgent) intermediary-agent-spec
   (ds/opt :beneficiary) beneficiary-spec
   (ds/opt :ultimateCreditor) party-identification-spec
   (ds/opt :instructionForCreditorAgent) (s/coll-of instruction-for-creditor-agent-spec)
   (ds/opt :purpose) purpose-code-spec
   (ds/opt :regulatoryReportingCodes) (s/coll-of string?)
   (ds/opt :remittanceInformation) remittance-information-spec
   (ds/opt :transactionStatus) transaction-individual-status-code-spec
   (ds/opt :statusReasonInformation) status-reason-information-spec
   })

(def credit-transfer-transaction-spec
  (ds/spec
    {:name ::credit-transfer-transaction
     :spec credit-transfer-transaction-data}))
