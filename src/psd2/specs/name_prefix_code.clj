(ns psd2.specs.name-prefix-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def name-prefix-code-data
  {
   })

(def name-prefix-code-spec
  (ds/spec
    {:name ::name-prefix-code
     :spec name-prefix-code-data}))
