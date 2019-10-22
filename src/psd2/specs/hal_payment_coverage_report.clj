(ns psd2.specs.hal-payment-coverage-report
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.payment-coverage-request-resource :refer :all]
            [psd2.specs.payment-coverage-report-links :refer :all]
            )
  (:import (java.io File)))


(def hal-payment-coverage-report-data
  {
   (ds/req :request) payment-coverage-request-resource-spec
   (ds/req :result) boolean?
   (ds/req :_links) payment-coverage-report-links-spec
   })

(def hal-payment-coverage-report-spec
  (ds/spec
    {:name ::hal-payment-coverage-report
     :spec hal-payment-coverage-report-data}))
