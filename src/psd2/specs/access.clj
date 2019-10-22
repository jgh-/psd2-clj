(ns psd2.specs.access
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.account-identification :refer :all]
            [psd2.specs.account-identification :refer :all]
            )
  (:import (java.io File)))


(def access-data
  {
   (ds/req :balances) (s/coll-of account-identification-spec)
   (ds/req :transactions) (s/coll-of account-identification-spec)
   (ds/req :trustedBeneficiaries) boolean?
   (ds/req :psuIdentity) boolean?
   })

(def access-spec
  (ds/spec
    {:name ::access
     :spec access-data}))
