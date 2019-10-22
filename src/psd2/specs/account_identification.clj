(ns psd2.specs.account-identification
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-identification :refer :all]
            )
  (:import (java.io File)))


(def account-identification-data
  {
   (ds/opt :iban) string?
   (ds/opt :other) generic-identification-spec
   (ds/opt :currency) string?
   })

(def account-identification-spec
  (ds/spec
    {:name ::account-identification
     :spec account-identification-data}))
