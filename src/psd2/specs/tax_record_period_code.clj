(ns psd2.specs.tax-record-period-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def tax-record-period-code-data
  {
   })

(def tax-record-period-code-spec
  (ds/spec
    {:name ::tax-record-period-code
     :spec tax-record-period-code-data}))
