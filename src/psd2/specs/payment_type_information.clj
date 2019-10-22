(ns psd2.specs.payment-type-information
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.priority-code :refer :all]
            [psd2.specs.service-level-code :refer :all]
            [psd2.specs.category-purpose-code :refer :all]
            )
  (:import (java.io File)))


(def payment-type-information-data
  {
   (ds/opt :instructionPriority) priority-code-spec
   (ds/opt :serviceLevel) service-level-code-spec
   (ds/opt :localInstrument) string?
   (ds/opt :categoryPurpose) category-purpose-code-spec
   })

(def payment-type-information-spec
  (ds/spec
    {:name ::payment-type-information
     :spec payment-type-information-data}))
