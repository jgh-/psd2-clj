(ns psd2.specs.date-and-place-of-birth
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def date-and-place-of-birth-data
  {
   (ds/req :birthDate) inst?
   (ds/req :cityOfBirth) string?
   (ds/req :countryOfBirth) string?
   })

(def date-and-place-of-birth-spec
  (ds/spec
    {:name ::date-and-place-of-birth
     :spec date-and-place-of-birth-data}))
