(ns psd2.specs.tax-period
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.tax-record-period-code :refer :all]
            )
  (:import (java.io File)))


(def tax-period-data
  {
   (ds/opt :year) string?
   (ds/opt :type) tax-record-period-code-spec
   (ds/opt :fromDate) inst?
   (ds/opt :toDate) inst?
   })

(def tax-period-spec
  (ds/spec
    {:name ::tax-period
     :spec tax-period-data}))
