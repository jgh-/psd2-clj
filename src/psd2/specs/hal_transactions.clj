(ns psd2.specs.hal-transactions
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.transaction :refer :all]
            [psd2.specs.transactions-links :refer :all]
            )
  (:import (java.io File)))


(def hal-transactions-data
  {
   (ds/req :transactions) (s/coll-of transaction-spec)
   (ds/req :_links) transactions-links-spec
   })

(def hal-transactions-spec
  (ds/spec
    {:name ::hal-transactions
     :spec hal-transactions-data}))
