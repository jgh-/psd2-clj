(ns psd2.specs.financial-institution-identification
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.clearing-system-member-identification :refer :all]
            [psd2.specs.postal-address :refer :all]
            )
  (:import (java.io File)))


(def financial-institution-identification-data
  {
   (ds/req :bicFi) string?
   (ds/opt :clearingSystemMemberId) clearing-system-member-identification-spec
   (ds/opt :name) string?
   (ds/opt :postalAddress) postal-address-spec
   })

(def financial-institution-identification-spec
  (ds/spec
    {:name ::financial-institution-identification
     :spec financial-institution-identification-data}))
