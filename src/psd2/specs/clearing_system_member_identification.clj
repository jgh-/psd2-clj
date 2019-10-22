(ns psd2.specs.clearing-system-member-identification
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def clearing-system-member-identification-data
  {
   (ds/opt :clearingSystemId) string?
   (ds/opt :memberId) string?
   })

(def clearing-system-member-identification-spec
  (ds/spec
    {:name ::clearing-system-member-identification
     :spec clearing-system-member-identification-data}))
