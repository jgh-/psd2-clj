(ns psd2.specs.title-and-name
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def title-and-name-data
  {
   (ds/opt :title) string?
   (ds/opt :name) string?
   })

(def title-and-name-spec
  (ds/spec
    {:name ::title-and-name
     :spec title-and-name-data}))
