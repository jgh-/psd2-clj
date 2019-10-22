(ns psd2.specs.payment-information-status-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def payment-information-status-code-data
  {
   })

(def payment-information-status-code-spec
  (ds/spec
    {:name ::payment-information-status-code
     :spec payment-information-status-code-data}))
