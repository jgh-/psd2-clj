(ns psd2.specs.hal-payment-request
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.payment-request-resource :refer :all]
            [psd2.specs.payment-request-links :refer :all]
            )
  (:import (java.io File)))


(def hal-payment-request-data
  {
   (ds/req :paymentRequest) payment-request-resource-spec
   (ds/req :_links) payment-request-links-spec
   })

(def hal-payment-request-spec
  (ds/spec
    {:name ::hal-payment-request
     :spec hal-payment-request-data}))
