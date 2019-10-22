(ns psd2.specs.service-level-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def service-level-code-data
  {
   })

(def service-level-code-spec
  (ds/spec
    {:name ::service-level-code
     :spec service-level-code-data}))
