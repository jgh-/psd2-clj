(ns psd2.specs.amount-and-currency-exchange-details
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.amount-type :refer :all]
            )
  (:import (java.io File)))


(def amount-and-currency-exchange-details-data
  {
   (ds/opt :type) string?
   (ds/req :amount) amount-type-spec
   (ds/req :sourceCurrency) string?
   (ds/opt :targetCurrency) string?
   (ds/opt :unitCurrency) string?
   (ds/req :exchangeRate) float?
   (ds/opt :contractIdentification) string?
   (ds/opt :quotationDate) inst?
   })

(def amount-and-currency-exchange-details-spec
  (ds/spec
    {:name ::amount-and-currency-exchange-details
     :spec amount-and-currency-exchange-details-data}))
