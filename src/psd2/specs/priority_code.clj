(ns psd2.specs.priority-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def priority-code-data
  {
   })

(def priority-code-spec
  (ds/spec
    {:name ::priority-code
     :spec priority-code-data}))
