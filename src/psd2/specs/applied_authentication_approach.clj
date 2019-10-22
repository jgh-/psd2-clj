(ns psd2.specs.applied-authentication-approach
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def applied-authentication-approach-data
  {
   })

(def applied-authentication-approach-spec
  (ds/spec
    {:name ::applied-authentication-approach
     :spec applied-authentication-approach-data}))
