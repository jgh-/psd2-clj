(ns psd2.specs.payment-identification
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def payment-identification-data
  {
   (ds/opt :resourceId) string?
   (ds/req :instructionId) string?
   (ds/opt :endToEndId) string?
   })

(def payment-identification-spec
  (ds/spec
    {:name ::payment-identification
     :spec payment-identification-data}))
