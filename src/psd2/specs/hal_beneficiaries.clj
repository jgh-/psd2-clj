(ns psd2.specs.hal-beneficiaries
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.beneficiary :refer :all]
            [psd2.specs.beneficiaries-links :refer :all]
            )
  (:import (java.io File)))


(def hal-beneficiaries-data
  {
   (ds/req :beneficiaries) (s/coll-of beneficiary-spec)
   (ds/req :_links) beneficiaries-links-spec
   })

(def hal-beneficiaries-spec
  (ds/spec
    {:name ::hal-beneficiaries
     :spec hal-beneficiaries-data}))
