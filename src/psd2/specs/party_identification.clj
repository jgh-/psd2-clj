(ns psd2.specs.party-identification
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.date-and-place-of-birth :refer :all]
            [psd2.specs.postal-address :refer :all]
            [psd2.specs.generic-identification :refer :all]
            [psd2.specs.generic-identification :refer :all]
            )
  (:import (java.io File)))


(def party-identification-data
  {
   (ds/req :name) string?
   (ds/opt :dateAndPlaceOfBirth) date-and-place-of-birth-spec
   (ds/opt :postalAddress) postal-address-spec
   (ds/opt :organisationId) generic-identification-spec
   (ds/opt :privateId) generic-identification-spec
   })

(def party-identification-spec
  (ds/spec
    {:name ::party-identification
     :spec party-identification-data}))
