(ns psd2.specs.hal-balances
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.balance-resource :refer :all]
            [psd2.specs.balances-links :refer :all]
            )
  (:import (java.io File)))


(def hal-balances-data
  {
   (ds/req :balances) (s/coll-of balance-resource-spec)
   (ds/req :_links) balances-links-spec
   })

(def hal-balances-spec
  (ds/spec
    {:name ::hal-balances
     :spec hal-balances-data}))
