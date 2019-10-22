(ns psd2.specs.postal-address
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def postal-address-data
  {
   (ds/opt :addressType) string?
   (ds/opt :department) string?
   (ds/opt :subDepartment) string?
   (ds/opt :streetName) string?
   (ds/opt :buildingNumber) string?
   (ds/opt :buildingName) string?
   (ds/opt :postCode) string?
   (ds/opt :townName) string?
   (ds/opt :countrySubDivision) string?
   (ds/req :country) string?
   (ds/req :addressLine) (s/coll-of string?)
   })

(def postal-address-spec
  (ds/spec
    {:name ::postal-address
     :spec postal-address-data}))
