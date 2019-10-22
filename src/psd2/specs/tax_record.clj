(ns psd2.specs.tax-record
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.tax-period :refer :all]
            [psd2.specs.tax-amount :refer :all]
            )
  (:import (java.io File)))


(def tax-record-data
  {
   (ds/opt :type) string?
   (ds/opt :category) string?
   (ds/opt :categoryDetails) string?
   (ds/opt :debtorStatus) string?
   (ds/opt :certificateIdentification) string?
   (ds/opt :formsCode) string?
   (ds/opt :period) tax-period-spec
   (ds/opt :taxAmount) tax-amount-spec
   (ds/opt :additionalInformation) string?
   })

(def tax-record-spec
  (ds/spec
    {:name ::tax-record
     :spec tax-record-data}))
