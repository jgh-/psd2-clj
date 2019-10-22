(ns psd2.specs.structured-remittance-information
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [psd2.specs.referred-document-information :refer :all]
            [psd2.specs.remittance-amount :refer :all]
            [psd2.specs.creditor-reference-information :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.tax-information :refer :all]
            )
  (:import (java.io File)))


(def structured-remittance-information-data
  {
   (ds/opt :referredDocumentInformation) (s/coll-of referred-document-information-spec)
   (ds/opt :referredDocumentAmount) remittance-amount-spec
   (ds/opt :creditorReferenceInformation) creditor-reference-information-spec
   (ds/opt :invoicer) party-identification-spec
   (ds/opt :invoicee) party-identification-spec
   (ds/opt :taxRemittance) tax-information-spec
   })

(def structured-remittance-information-spec
  (ds/spec
    {:name ::structured-remittance-information
     :spec structured-remittance-information-data}))
