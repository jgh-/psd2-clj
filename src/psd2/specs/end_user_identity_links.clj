(ns psd2.specs.end-user-identity-links
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.generic-link :refer :all]
            )
  (:import (java.io File)))


(def end-user-identity-links-data
  {
   (ds/req :self) generic-link-spec
   (ds/opt :parent-list) generic-link-spec
   })

(def end-user-identity-links-spec
  (ds/spec
    {:name ::end-user-identity-links
     :spec end-user-identity-links-data}))
