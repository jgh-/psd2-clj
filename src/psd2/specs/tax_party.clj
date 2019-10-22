(ns psd2.specs.tax-party
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.title-and-name :refer :all]
            )
  (:import (java.io File)))


(def tax-party-data
  {
   (ds/opt :taxIdentification) string?
   (ds/opt :registrationIdentification) string?
   (ds/opt :taxType) string?
   (ds/opt :authorisation) title-and-name-spec
   })

(def tax-party-spec
  (ds/spec
    {:name ::tax-party
     :spec tax-party-data}))
