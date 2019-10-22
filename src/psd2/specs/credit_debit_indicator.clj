(ns psd2.specs.credit-debit-indicator
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def credit-debit-indicator-data
  {
   })

(def credit-debit-indicator-spec
  (ds/spec
    {:name ::credit-debit-indicator
     :spec credit-debit-indicator-data}))
