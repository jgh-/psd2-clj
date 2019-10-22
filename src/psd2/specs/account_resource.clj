(ns psd2.specs.account-resource
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.account-identification :refer :all]
            [psd2.specs.generic-identification :refer :all]
            [psd2.specs.balance-resource :refer :all]
            [psd2.specs.account-links :refer :all]
            )
  (:import (java.io File)))


(def account-resource-data
  {
   (ds/opt :resourceId) string?
   (ds/opt :bicFi) string?
   (ds/opt :accountId) account-identification-spec
   (ds/req :name) string?
   (ds/opt :details) string?
   (ds/opt :linkedAccount) string?
   (ds/opt :usage) string?
   (ds/opt :company) generic-identification-spec
   (ds/req :cashAccountType) string?
   (ds/opt :product) string?
   (ds/opt :balances) (s/coll-of balance-resource-spec)
   (ds/opt :psuStatus) string?
   (ds/req :_links) account-links-spec
   })

(def account-resource-spec
  (ds/spec
    {:name ::account-resource
     :spec account-resource-data}))
