(ns psd2.specs.related-parties
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.financial-institution-identification :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.account-identification :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.financial-institution-identification :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.account-identification :refer :all]
            [psd2.specs.party-identification :refer :all]
            )
  (:import (java.io File)))


(def related-parties-data
  {
   (ds/opt :initiatingParty) party-identification-spec
   (ds/opt :debtorAgent) financial-institution-identification-spec
   (ds/opt :debtor) party-identification-spec
   (ds/opt :debtorAccount) account-identification-spec
   (ds/opt :ultimateDebtor) party-identification-spec
   (ds/opt :creditorAgent) financial-institution-identification-spec
   (ds/opt :creditor) party-identification-spec
   (ds/opt :creditorAccount) account-identification-spec
   (ds/opt :ultimateCreditor) party-identification-spec
   })

(def related-parties-spec
  (ds/spec
    {:name ::related-parties
     :spec related-parties-data}))
