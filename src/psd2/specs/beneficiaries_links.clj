(ns psd2.specs.beneficiaries-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def beneficiaries-links-data
  {
   (ds/req :self) generic-link-spec
   (ds/opt :parent-list) generic-link-spec
   (ds/opt :first) generic-link-spec
   (ds/opt :last) generic-link-spec
   (ds/opt :next) generic-link-spec
   (ds/opt :prev) generic-link-spec
   })

(def beneficiaries-links-spec
  (ds/spec
    {:name ::beneficiaries-links
     :spec beneficiaries-links-data}))
