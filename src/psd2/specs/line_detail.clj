(ns psd2.specs.line-detail
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.document-line-identification :refer :all]
            [psd2.specs.remittance-amount :refer :all]
            )
  (:import (java.io File)))


(def line-detail-data
  {
   (ds/opt :identification) document-line-identification-spec
   (ds/opt :description) string?
   (ds/opt :amount) remittance-amount-spec
   })

(def line-detail-spec
  (ds/spec
    {:name ::line-detail
     :spec line-detail-data}))
