(ns psd2.specs.bank-transaction-code
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def bank-transaction-code-data
  {
   (ds/req :domain) string?
   (ds/req :family) string?
   (ds/req :subFamily) string?
   (ds/opt :code) string?
   (ds/opt :issuer) string?
   })

(def bank-transaction-code-spec
  (ds/spec
    {:name ::bank-transaction-code
     :spec bank-transaction-code-data}))
