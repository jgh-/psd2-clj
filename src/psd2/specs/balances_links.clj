(ns psd2.specs.balances-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def balances-links-data
  {
   (ds/req :self) generic-link-spec
   (ds/opt :parent-list) generic-link-spec
   (ds/opt :transactions) generic-link-spec
   })

(def balances-links-spec
  (ds/spec
    {:name ::balances-links
     :spec balances-links-data}))
