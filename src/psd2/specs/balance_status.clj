(ns psd2.specs.balance-status
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def balance-status-data
  {
   })

(def balance-status-spec
  (ds/spec
    {:name ::balance-status
     :spec balance-status-data}))
