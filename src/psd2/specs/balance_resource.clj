(ns psd2.specs.balance-resource
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.balance-status :refer :all]
            )
  (:import (java.io File)))


(def balance-resource-data
  {
   (ds/req :name) string?
   (ds/req :balanceAmount) amount-type-spec
   (ds/req :balanceType) balance-status-spec
   (ds/opt :lastChangeDateTime) inst?
   (ds/opt :referenceDate) inst?
   (ds/opt :lastCommittedTransaction) string?
   })

(def balance-resource-spec
  (ds/spec
    {:name ::balance-resource
     :spec balance-resource-data}))
