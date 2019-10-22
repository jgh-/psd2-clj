(ns psd2.specs.error-model
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def error-model-data
  {
   (ds/opt :timestamp) inst?
   (ds/req :status) int?
   (ds/opt :error) string?
   (ds/req :message) string?
   (ds/opt :path) string?
   })

(def error-model-spec
  (ds/spec
    {:name ::error-model
     :spec error-model-data}))
