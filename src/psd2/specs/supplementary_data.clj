(ns psd2.specs.supplementary-data
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.applied-authentication-approach :refer :all]
            )
  (:import (java.io File)))


(def supplementary-data-data
  {
   (ds/opt :acceptedAuthenticationApproach) (s/coll-of string?)
   (ds/opt :appliedAuthenticationApproach) applied-authentication-approach-spec
   (ds/opt :scaHint) string?
   (ds/opt :successfulReportUrl) string?
   (ds/opt :unsuccessfulReportUrl) string?
   })

(def supplementary-data-spec
  (ds/spec
    {:name ::supplementary-data
     :spec supplementary-data-data}))
