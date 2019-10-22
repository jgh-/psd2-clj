(ns psd2.specs.instruction-for-creditor-agent
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def instruction-for-creditor-agent-data
  {
   (ds/opt :code) string?
   (ds/opt :instructionInformation) string?
   })

(def instruction-for-creditor-agent-spec
  (ds/spec
    {:name ::instruction-for-creditor-agent
     :spec instruction-for-creditor-agent-data}))
