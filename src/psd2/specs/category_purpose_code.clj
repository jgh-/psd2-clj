(ns psd2.specs.category-purpose-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def category-purpose-code-data
  {
   })

(def category-purpose-code-spec
  (ds/spec
    {:name ::category-purpose-code
     :spec category-purpose-code-data}))
