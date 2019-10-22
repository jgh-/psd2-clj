(ns psd2.specs.generic-identification
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def generic-identification-data
  {
   (ds/req :identification) string?
   (ds/req :schemeName) string?
   (ds/opt :issuer) string?
   })

(def generic-identification-spec
  (ds/spec
    {:name ::generic-identification
     :spec generic-identification-data}))
