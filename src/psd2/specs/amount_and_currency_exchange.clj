(ns psd2.specs.amount-and-currency-exchange
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-and-currency-exchange-details :refer :all]
            [psd2.specs.amount-and-currency-exchange-details :refer :all]
            [psd2.specs.amount-and-currency-exchange-details :refer :all]
            [psd2.specs.amount-and-currency-exchange-details :refer :all]
            [psd2.specs.amount-and-currency-exchange-details :refer :all]
            )
  (:import (java.io File)))


(def amount-and-currency-exchange-data
  {
   (ds/opt :instructedAmount) amount-and-currency-exchange-details-spec
   (ds/opt :transactionAmount) amount-and-currency-exchange-details-spec
   (ds/opt :counterValueAmount) amount-and-currency-exchange-details-spec
   (ds/opt :announcedPostingAmount) amount-and-currency-exchange-details-spec
   (ds/opt :proprietaryAmount) (s/coll-of amount-and-currency-exchange-details-spec)
   })

(def amount-and-currency-exchange-spec
  (ds/spec
    {:name ::amount-and-currency-exchange
     :spec amount-and-currency-exchange-data}))
