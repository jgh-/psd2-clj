(ns psd2.specs.account-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def account-links-data
  {
   (ds/opt :balances) generic-link-spec
   (ds/opt :transactions) generic-link-spec
   })

(def account-links-spec
  (ds/spec
    {:name ::account-links
     :spec account-links-data}))
