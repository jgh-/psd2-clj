(ns psd2.specs.confirmation-resource
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def confirmation-resource-data
  {
   (ds/opt :nonce) string?
   (ds/opt :psuAuthenticationFactor) string?
   })

(def confirmation-resource-spec
  (ds/spec
    {:name ::confirmation-resource
     :spec confirmation-resource-data}))
