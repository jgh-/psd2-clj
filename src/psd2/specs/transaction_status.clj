(ns psd2.specs.transaction-status
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def transaction-status-data
  {
   })

(def transaction-status-spec
  (ds/spec
    {:name ::transaction-status
     :spec transaction-status-data}))
