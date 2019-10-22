(ns psd2.specs.charge-bearer-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def charge-bearer-code-data
  {
   })

(def charge-bearer-code-spec
  (ds/spec
    {:name ::charge-bearer-code
     :spec charge-bearer-code-data}))
