(ns psd2.specs.psu-context-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def psu-context-links-data
  {
   (ds/req :self) generic-link-spec
   (ds/opt :endUserIdentity) generic-link-spec
   (ds/opt :beneficiaries) generic-link-spec
   (ds/opt :first) generic-link-spec
   (ds/opt :last) generic-link-spec
   (ds/opt :next) generic-link-spec
   (ds/opt :prev) generic-link-spec
   })

(def psu-context-links-spec
  (ds/spec
    {:name ::psu-context-links
     :spec psu-context-links-data}))
