(ns psd2.specs.payment-coverage-request-resource
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.account-identification :refer :all]
            )
  (:import (java.io File)))


(def payment-coverage-request-resource-data
  {
   (ds/req :paymentCoverageRequestId) string?
   (ds/opt :payee) string?
   (ds/req :instructedAmount) amount-type-spec
   (ds/req :accountId) account-identification-spec
   })

(def payment-coverage-request-resource-spec
  (ds/spec
    {:name ::payment-coverage-request-resource
     :spec payment-coverage-request-resource-data}))
