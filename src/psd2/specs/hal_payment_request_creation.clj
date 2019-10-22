(ns psd2.specs.hal-payment-request-creation
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.applied-authentication-approach :refer :all]
            [psd2.specs.payment-request-resource-creation-links :refer :all]
            )
  (:import (java.io File)))


(def hal-payment-request-creation-data
  {
   (ds/opt :appliedAuthenticationApproach) applied-authentication-approach-spec
   (ds/opt :nonce) string?
   (ds/opt :_links) payment-request-resource-creation-links-spec
   })

(def hal-payment-request-creation-spec
  (ds/spec
    {:name ::hal-payment-request-creation
     :spec hal-payment-request-creation-data}))
