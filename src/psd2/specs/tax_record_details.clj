(ns psd2.specs.tax-record-details
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.tax-period :refer :all]
            [psd2.specs.amount-type :refer :all]
            )
  (:import (java.io File)))


(def tax-record-details-data
  {
   (ds/opt :period) tax-period-spec
   (ds/req :amount) amount-type-spec
   })

(def tax-record-details-spec
  (ds/spec
    {:name ::tax-record-details
     :spec tax-record-details-data}))
