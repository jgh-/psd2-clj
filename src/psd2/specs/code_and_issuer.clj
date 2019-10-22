(ns psd2.specs.code-and-issuer
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def code-and-issuer-data
  {
   (ds/req :code) string?
   (ds/opt :issuer) string?
   })

(def code-and-issuer-spec
  (ds/spec
    {:name ::code-and-issuer
     :spec code-and-issuer-data}))
