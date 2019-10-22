(ns psd2.specs.payment-request-resource
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.payment-type-information :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.account-identification :refer :all]
            [psd2.specs.financial-institution-identification :refer :all]
            [psd2.specs.beneficiary :refer :all]
            [psd2.specs.charge-bearer-code :refer :all]
            [psd2.specs.payment-information-status-code :refer :all]
            [psd2.specs.status-reason-information :refer :all]
            [psd2.specs.credit-transfer-transaction :refer :all]
            [psd2.specs.supplementary-data :refer :all]
            )
  (:import (java.io File)))


(def payment-request-resource-data
  {
   (ds/opt :resourceId) string?
   (ds/req :paymentInformationId) string?
   (ds/opt :batchBooking) boolean?
   (ds/req :creationDateTime) inst?
   (ds/req :numberOfTransactions) int?
   (ds/req :initiatingParty) party-identification-spec
   (ds/opt :acceptDebtorAccountChange) boolean?
   (ds/opt :acceptChargeHandlingChange) boolean?
   (ds/req :paymentTypeInformation) payment-type-information-spec
   (ds/opt :debtor) party-identification-spec
   (ds/opt :debtorAccount) account-identification-spec
   (ds/opt :debtorAgent) financial-institution-identification-spec
   (ds/opt :beneficiary) beneficiary-spec
   (ds/opt :chargeBearer) charge-bearer-code-spec
   (ds/opt :paymentInformationStatus) payment-information-status-code-spec
   (ds/opt :statusReasonInformation) status-reason-information-spec
   (ds/opt :fundsAvailability) boolean?
   (ds/opt :booking) boolean?
   (ds/opt :requestedExecutionDate) inst?
   (ds/req :creditTransferTransaction) (s/coll-of credit-transfer-transaction-spec)
   (ds/req :supplementaryData) supplementary-data-spec
   })

(def payment-request-resource-spec
  (ds/spec
    {:name ::payment-request-resource
     :spec payment-request-resource-data}))
