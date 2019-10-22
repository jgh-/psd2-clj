(ns psd2.specs.beneficiary
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.financial-institution-identification :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.account-identification :refer :all]
            )
  (:import (java.io File)))


(def beneficiary-data
  {
   (ds/opt :id) string?
   (ds/opt :isTrusted) boolean?
   (ds/opt :creditorAgent) financial-institution-identification-spec
   (ds/req :creditor) party-identification-spec
   (ds/opt :creditorAccount) account-identification-spec
   })

(def beneficiary-spec
  (ds/spec
    {:name ::beneficiary
     :spec beneficiary-data}))
