(ns psd2.specs.generic-link
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def generic-link-data
  {
   (ds/req :href) string?
   (ds/opt :templated) boolean?
   })

(def generic-link-spec
  (ds/spec
    {:name ::generic-link
     :spec generic-link-data}))
