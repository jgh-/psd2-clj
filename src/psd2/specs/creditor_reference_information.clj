(ns psd2.specs.creditor-reference-information
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.code-and-issuer :refer :all]
            )
  (:import (java.io File)))


(def creditor-reference-information-data
  {
   (ds/opt :type) code-and-issuer-spec
   (ds/opt :reference) string?
   })

(def creditor-reference-information-spec
  (ds/spec
    {:name ::creditor-reference-information
     :spec creditor-reference-information-data}))
