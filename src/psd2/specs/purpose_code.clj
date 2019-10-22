(ns psd2.specs.purpose-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def purpose-code-data
  {
   })

(def purpose-code-spec
  (ds/spec
    {:name ::purpose-code
     :spec purpose-code-data}))
