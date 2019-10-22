(ns psd2.specs.transactions-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def transactions-links-data
  {
   (ds/req :self) generic-link-spec
   (ds/opt :parent-list) generic-link-spec
   (ds/opt :balances) generic-link-spec
   (ds/opt :first) generic-link-spec
   (ds/opt :last) generic-link-spec
   (ds/opt :next) generic-link-spec
   (ds/opt :prev) generic-link-spec
   })

(def transactions-links-spec
  (ds/spec
    {:name ::transactions-links
     :spec transactions-links-data}))
