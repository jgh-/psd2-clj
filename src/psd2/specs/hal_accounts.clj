(ns psd2.specs.hal-accounts
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.account-resource :refer :all]
            [psd2.specs.psu-context-links :refer :all]
            )
  (:import (java.io File)))


(def hal-accounts-data
  {
   (ds/req :accounts) (s/coll-of account-resource-spec)
   (ds/req :_links) psu-context-links-spec
   })

(def hal-accounts-spec
  (ds/spec
    {:name ::hal-accounts
     :spec hal-accounts-data}))
