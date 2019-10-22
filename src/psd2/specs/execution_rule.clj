(ns psd2.specs.execution-rule
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def execution-rule-data
  {
   })

(def execution-rule-spec
  (ds/spec
    {:name ::execution-rule
     :spec execution-rule-data}))
