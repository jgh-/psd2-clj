(ns psd2.specs.typed-amount
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            )
  (:import (java.io File)))


(def typed-amount-data
  {
   (ds/opt :type) string?
   (ds/req :amount) amount-type-spec
   })

(def typed-amount-spec
  (ds/spec
    {:name ::typed-amount
     :spec typed-amount-data}))
