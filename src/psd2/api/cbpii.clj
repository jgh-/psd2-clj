(ns psd2.api.cbpii
  (:require [psd2.core :refer [call-api check-required-params with-collection-format *api-context*]]
            [clojure.spec.alpha :as s]
            [spec-tools.core :as st]
            [orchestra.core :refer [defn-spec]]
            [psd2.specs.charges-record :refer :all]
            [psd2.specs.execution-rule :refer :all]
            [psd2.specs.hal-balances :refer :all]
            [psd2.specs.local-instrument-code :refer :all]
            [psd2.specs.charge-bearer-code :refer :all]
            [psd2.specs.supplementary-data :refer :all]
            [psd2.specs.related-parties :refer :all]
            [psd2.specs.account-identification :refer :all]
            [psd2.specs.booking-information :refer :all]
            [psd2.specs.document-line-identification :refer :all]
            [psd2.specs.amount-and-currency-exchange-details :refer :all]
            [psd2.specs.generic-identification :refer :all]
            [psd2.specs.payment-request-links :refer :all]
            [psd2.specs.payment-identification :refer :all]
            [psd2.specs.tax-party :refer :all]
            [psd2.specs.priority-code :refer :all]
            [psd2.specs.remittance-information :refer :all]
            [psd2.specs.payment-request-resource-creation-links :refer :all]
            [psd2.specs.tax-record :refer :all]
            [psd2.specs.charges :refer :all]
            [psd2.specs.amount-and-currency-exchange :refer :all]
            [psd2.specs.tax-charges :refer :all]
            [psd2.specs.hal-transactions :refer :all]
            [psd2.specs.hal-beneficiaries :refer :all]
            [psd2.specs.tax-period :refer :all]
            [psd2.specs.payment-coverage-request-resource :refer :all]
            [psd2.specs.applied-authentication-approach :refer :all]
            [psd2.specs.date-and-place-of-birth :refer :all]
            [psd2.specs.access :refer :all]
            [psd2.specs.psu-context-links :refer :all]
            [psd2.specs.balance-status :refer :all]
            [psd2.specs.remittance-amount :refer :all]
            [psd2.specs.tax-amount :refer :all]
            [psd2.specs.tax-record-period-code :refer :all]
            [psd2.specs.requested-execution-date :refer :all]
            [psd2.specs.title-and-name :refer :all]
            [psd2.specs.percentage-rate :refer :all]
            [psd2.specs.equivalent-amount-type :refer :all]
            [psd2.specs.instruction-for-creditor-agent :refer :all]
            [psd2.specs.psu-status-type :refer :all]
            [psd2.specs.intermediary-agent :refer :all]
            [psd2.specs.regulatory-reporting-code :refer :all]
            [psd2.specs.end-date :refer :all]
            [psd2.specs.beneficiaries-links :refer :all]
            [psd2.specs.party-identification :refer :all]
            [psd2.specs.tax-information :refer :all]
            [psd2.specs.transaction-status :refer :all]
            [psd2.specs.currency-code :refer :all]
            [psd2.specs.balance-resource :refer :all]
            [psd2.specs.transaction :refer :all]
            [psd2.specs.frequency-code :refer :all]
            [psd2.specs.payment-request-resource :refer :all]
            [psd2.specs.creation-date-time :refer :all]
            [psd2.specs.financial-institution-identification :refer :all]
            [psd2.specs.name-prefix-code :refer :all]
            [psd2.specs.transactions-links :refer :all]
            [psd2.specs.account-resource :refer :all]
            [psd2.specs.service-level-code :refer :all]
            [psd2.specs.code-and-issuer :refer :all]
            [psd2.specs.hal-accounts :refer :all]
            [psd2.specs.creditor-reference-information :refer :all]
            [psd2.specs.bank-transaction-code :refer :all]
            [psd2.specs.credit-debit-indicator :refer :all]
            [psd2.specs.payment-coverage-report-links :refer :all]
            [psd2.specs.hal-payment-request :refer :all]
            [psd2.specs.amount-type :refer :all]
            [psd2.specs.transaction-individual-status-code :refer :all]
            [psd2.specs.generic-link :refer :all]
            [psd2.specs.hal-payment-request-creation :refer :all]
            [psd2.specs.error-model :refer :all]
            [psd2.specs.nonce :refer :all]
            [psd2.specs.resource-id :refer :all]
            [psd2.specs.tax-record-details :refer :all]
            [psd2.specs.document-adjustment :refer :all]
            [psd2.specs.beneficiary :refer :all]
            [psd2.specs.payment-information-status-code :refer :all]
            [psd2.specs.hal-end-user-identity :refer :all]
            [psd2.specs.referred-document-information :refer :all]
            [psd2.specs.confirmation-resource :refer :all]
            [psd2.specs.purpose-code :refer :all]
            [psd2.specs.exchange-rate :refer :all]
            [psd2.specs.postal-address :refer :all]
            [psd2.specs.batch-booking-indicator :refer :all]
            [psd2.specs.balances-links :refer :all]
            [psd2.specs.clearing-system-member-identification :refer :all]
            [psd2.specs.payment-information-id :refer :all]
            [psd2.specs.status-reason-information :refer :all]
            [psd2.specs.hal-payment-coverage-report :refer :all]
            [psd2.specs.payment-type-information :refer :all]
            [psd2.specs.credit-transfer-transaction :refer :all]
            [psd2.specs.account-links :refer :all]
            [psd2.specs.line-detail :refer :all]
            [psd2.specs.category-purpose-code :refer :all]
            [psd2.specs.end-user-identity-links :refer :all]
            [psd2.specs.structured-remittance-information :refer :all]
            [psd2.specs.typed-amount :refer :all]
            [psd2.specs.funds-availability-information :refer :all]
            )
  (:import (java.io File)))


(defn-spec funds-confirmations-post-with-http-info any?
  "Payment coverage check request (CBPII)
  ### Description
The CBPII can ask an ASPSP to check if a given amount can be covered by the liquidity that is available on a PSU cash account or payment card.
### Prerequisites
- The TPP has been registered by the Registration Authority for the CBPII role
- The TPP and the PSU have a contract that has been registered by the ASPSP
  - At this step, the ASPSP has delivered an \"Authorization Code\", a \"Resource Owner Password\" or a \"Client Credential\" OAUTH2 access token to the TPP (cf. § 3.4.2).
  - Each ASPSP has to implement either the \"Authorization Code\"/\"Resource Owner Password\" or the \"Client Credential\" OAUTH2 access token model.
  - Doing this, it will edit the [security] section on this path in order to specify which model it has chosen
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its OAUTH2 \"Authorization Code\", \"Resource Owner Password\" or \"Client Credential\" access token which allows the ASPSP to identify the relevant PSU.
### Business flow
The CBPII requests the ASPSP for a payment coverage check against either a bank account or a card primary identifier.
The ASPSP answers with a structure embedding the original request and the result as a Boolean."
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-coverage-request-resource payment-coverage-request-resource, ] (funds-confirmations-post-with-http-info Authorization Signature X-Request-ID payment-coverage-request-resource nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-coverage-request-resource payment-coverage-request-resource, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization Signature X-Request-ID payment-coverage-request-resource)
   (call-api "/funds-confirmations" :post
             {:path-params   {}
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :body-param    payment-coverage-request-resource
              :content-types ["application/json"]
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["accessCode" "clientCredentials" "resourceOwnerIdentification"]})))

(defn-spec funds-confirmations-post hal-payment-coverage-report-spec
  "Payment coverage check request (CBPII)
  ### Description
The CBPII can ask an ASPSP to check if a given amount can be covered by the liquidity that is available on a PSU cash account or payment card.
### Prerequisites
- The TPP has been registered by the Registration Authority for the CBPII role
- The TPP and the PSU have a contract that has been registered by the ASPSP
  - At this step, the ASPSP has delivered an \"Authorization Code\", a \"Resource Owner Password\" or a \"Client Credential\" OAUTH2 access token to the TPP (cf. § 3.4.2).
  - Each ASPSP has to implement either the \"Authorization Code\"/\"Resource Owner Password\" or the \"Client Credential\" OAUTH2 access token model.
  - Doing this, it will edit the [security] section on this path in order to specify which model it has chosen
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its OAUTH2 \"Authorization Code\", \"Resource Owner Password\" or \"Client Credential\" access token which allows the ASPSP to identify the relevant PSU.
### Business flow
The CBPII requests the ASPSP for a payment coverage check against either a bank account or a card primary identifier.
The ASPSP answers with a structure embedding the original request and the result as a Boolean."
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-coverage-request-resource payment-coverage-request-resource, ] (funds-confirmations-post Authorization Signature X-Request-ID payment-coverage-request-resource nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-coverage-request-resource payment-coverage-request-resource, optional-params any?]
   (let [res (:data (funds-confirmations-post-with-http-info Authorization Signature X-Request-ID payment-coverage-request-resource optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-payment-coverage-report-spec res st/string-transformer)
        res))))


