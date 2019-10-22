(ns psd2.specs.referred-document-information
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.code-and-issuer :refer :all]
            [psd2.specs.line-detail :refer :all]
            )
  (:import (java.io File)))


(def referred-document-information-data
  {
   (ds/opt :type) code-and-issuer-spec
   (ds/opt :number) string?
   (ds/opt :relatedDate) inst?
   (ds/opt :lineDetails) (s/coll-of line-detail-spec)
   })

(def referred-document-information-spec
  (ds/spec
    {:name ::referred-document-information
     :spec referred-document-information-data}))
