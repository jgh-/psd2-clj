(ns psd2.specs.intermediary-agent
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.account-identification :refer :all]
            )
  (:import (java.io File)))


(def intermediary-agent-data
  {
   (ds/opt :agent) party-identification-spec
   (ds/opt :agentAccount) account-identification-spec
   })

(def intermediary-agent-spec
  (ds/spec
    {:name ::intermediary-agent
     :spec intermediary-agent-data}))
