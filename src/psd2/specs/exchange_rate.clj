(ns psd2.specs.exchange-rate
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def exchange-rate-data
  {
   (ds/opt :unitCurrency) string?
   (ds/opt :exchangeRate) float?
   (ds/opt :rateType) string?
   (ds/opt :contractIdentification) string?
   })

(def exchange-rate-spec
  (ds/spec
    {:name ::exchange-rate
     :spec exchange-rate-data}))
