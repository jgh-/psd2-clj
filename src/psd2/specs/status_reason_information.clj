(ns psd2.specs.status-reason-information
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def status-reason-information-data
  {
   })

(def status-reason-information-spec
  (ds/spec
    {:name ::status-reason-information
     :spec status-reason-information-data}))
