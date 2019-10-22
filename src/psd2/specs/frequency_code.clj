(ns psd2.specs.frequency-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def frequency-code-data
  {
   })

(def frequency-code-spec
  (ds/spec
    {:name ::frequency-code
     :spec frequency-code-data}))
