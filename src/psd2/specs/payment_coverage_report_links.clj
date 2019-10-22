(ns psd2.specs.payment-coverage-report-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def payment-coverage-report-links-data
  {
   (ds/req :self) generic-link-spec
   })

(def payment-coverage-report-links-spec
  (ds/spec
    {:name ::payment-coverage-report-links
     :spec payment-coverage-report-links-data}))
