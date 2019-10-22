(ns psd2.specs.payment-request-resource-creation-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def payment-request-resource-creation-links-data
  {
   (ds/opt :consentApproval) generic-link-spec
   })

(def payment-request-resource-creation-links-spec
  (ds/spec
    {:name ::payment-request-resource-creation-links
     :spec payment-request-resource-creation-links-data}))
