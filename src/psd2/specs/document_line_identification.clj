(ns psd2.specs.document-line-identification
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.code-and-issuer :refer :all]
            )
  (:import (java.io File)))


(def document-line-identification-data
  {
   (ds/opt :type) code-and-issuer-spec
   (ds/opt :number) string?
   (ds/opt :relatedDate) inst?
   })

(def document-line-identification-spec
  (ds/spec
    {:name ::document-line-identification
     :spec document-line-identification-data}))
