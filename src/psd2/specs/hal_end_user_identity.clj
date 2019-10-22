(ns psd2.specs.hal-end-user-identity
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.name-prefix-code :refer :all]
            [psd2.specs.end-user-identity-links :refer :all]
            )
  (:import (java.io File)))


(def hal-end-user-identity-data
  {
   (ds/req :connectedPsu) string?
   (ds/opt :connectedPsuNamePrefix) name-prefix-code-spec
   (ds/opt :connectedPsuFirstName) string?
   (ds/opt :connectedPsuLastName) string?
   (ds/req :_links) end-user-identity-links-spec
   })

(def hal-end-user-identity-spec
  (ds/spec
    {:name ::hal-end-user-identity
     :spec hal-end-user-identity-data}))
