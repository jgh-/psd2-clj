(ns psd2.specs.payment-request-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def payment-request-links-data
  {
   (ds/opt :request) generic-link-spec
   (ds/opt :confirmation) generic-link-spec
   })

(def payment-request-links-spec
  (ds/spec
    {:name ::payment-request-links
     :spec payment-request-links-data}))
