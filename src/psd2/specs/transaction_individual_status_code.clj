(ns psd2.specs.transaction-individual-status-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def transaction-individual-status-code-data
  {
   })

(def transaction-individual-status-code-spec
  (ds/spec
    {:name ::transaction-individual-status-code
     :spec transaction-individual-status-code-data}))
