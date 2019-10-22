(ns psd2.specs.remittance-information
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.structured-remittance-information :refer :all]
            )
  (:import (java.io File)))


(def remittance-information-data
  {
   (ds/opt :unstructured) (s/coll-of string?)
   (ds/opt :structured) (s/coll-of structured-remittance-information-spec)
   })

(def remittance-information-spec
  (ds/spec
    {:name ::remittance-information
     :spec remittance-information-data}))
