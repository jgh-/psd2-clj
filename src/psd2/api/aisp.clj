(ns psd2.api.aisp
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


(defn-spec accounts-balances-get-with-http-info any?
  "Retrieval of an account balances report (AISP)
  ### Description
This call returns a set of balances for a given PSU account that is specified by the AISP through an account resource Identification
### Prerequisites         
- The TPP has been registered by the Registration Authority for the AISP role
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
  - At this step, the ASPSP has delivered an OAUTH2 &ldquo;Authorization Code&rdquo; or &ldquo;Resource Owner Password&rdquo; access token to the TPP (cf. &sect; 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 &ldquo;Authorization Code&rdquo; or &ldquo;Resource Owner Password&rdquo; access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. &sect; 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
- The TPP has previously retrieved the list of available accounts for the PSU
### Business flow
The AISP requests the ASPSP on one of the PSU&rsquo;s accounts.
The ASPSP answers by providing a list of balances on this account.
  - The ASPSP must provide at least the accounting balance on the account.
  - The ASPSP can provide other balance restitutions, e.g. instant balance, as well, if possible.
  - Actually, from the PSD2 perspective, any other balances that are provided through the Web-Banking service of the ASPSP must also be provided by this ASPSP through the API."
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, ] (accounts-balances-get-with-http-info Authorization accountResourceId Signature X-Request-ID nil))
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization accountResourceId Signature X-Request-ID)
   (call-api "/accounts/{accountResourceId}/balances" :get
             {:path-params   {"accountResourceId" accountResourceId }
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :content-types []
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["accessCode" "resourceOwnerIdentification"]})))

(defn-spec accounts-balances-get hal-balances-spec
  "Retrieval of an account balances report (AISP)
  ### Description
This call returns a set of balances for a given PSU account that is specified by the AISP through an account resource Identification
### Prerequisites         
- The TPP has been registered by the Registration Authority for the AISP role
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
  - At this step, the ASPSP has delivered an OAUTH2 &ldquo;Authorization Code&rdquo; or &ldquo;Resource Owner Password&rdquo; access token to the TPP (cf. &sect; 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 &ldquo;Authorization Code&rdquo; or &ldquo;Resource Owner Password&rdquo; access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. &sect; 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
- The TPP has previously retrieved the list of available accounts for the PSU
### Business flow
The AISP requests the ASPSP on one of the PSU&rsquo;s accounts.
The ASPSP answers by providing a list of balances on this account.
  - The ASPSP must provide at least the accounting balance on the account.
  - The ASPSP can provide other balance restitutions, e.g. instant balance, as well, if possible.
  - Actually, from the PSD2 perspective, any other balances that are provided through the Web-Banking service of the ASPSP must also be provided by this ASPSP through the API."
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, ] (accounts-balances-get Authorization accountResourceId Signature X-Request-ID nil))
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, optional-params any?]
   (let [res (:data (accounts-balances-get-with-http-info Authorization accountResourceId Signature X-Request-ID optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-balances-spec res st/string-transformer)
        res))))


(defn-spec accounts-get-with-http-info any?
  "Retrieval of the PSU accounts (AISP)
  ### Description
This call returns all payment accounts that are relevant the PSU on behalf of whom the AISP is connected.
Thanks to HYPERMEDIA, each account is returned with the links aiming to ease access to the relevant transactions and balances.
The result may be subject to pagination (i.e. retrieving a partial result in case of having too many results) through a set of pages by the ASPSP. Thereafter, the AISP may ask for the first, next, previous or last page of results.

### Prerequisites
  
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP            
    - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
  
### Business Flow
The TPP sends a request to the ASPSP for retrieving the list of the PSU payment accounts.
The ASPSP computes the relevant PSU accounts and builds the answer as an accounts list. 
The result may be subject to pagination in order to avoid an excessive result set. 
Each payment account will be provided with its characteristics."
  ([Authorization string?, Signature string?, X-Request-ID string?, ] (accounts-get-with-http-info Authorization Signature X-Request-ID nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization Signature X-Request-ID)
   (call-api "/accounts" :get
             {:path-params   {}
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :content-types []
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["accessCode" "resourceOwnerIdentification"]})))

(defn-spec accounts-get hal-accounts-spec
  "Retrieval of the PSU accounts (AISP)
  ### Description
This call returns all payment accounts that are relevant the PSU on behalf of whom the AISP is connected.
Thanks to HYPERMEDIA, each account is returned with the links aiming to ease access to the relevant transactions and balances.
The result may be subject to pagination (i.e. retrieving a partial result in case of having too many results) through a set of pages by the ASPSP. Thereafter, the AISP may ask for the first, next, previous or last page of results.

### Prerequisites
  
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP            
    - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
  
### Business Flow
The TPP sends a request to the ASPSP for retrieving the list of the PSU payment accounts.
The ASPSP computes the relevant PSU accounts and builds the answer as an accounts list. 
The result may be subject to pagination in order to avoid an excessive result set. 
Each payment account will be provided with its characteristics."
  ([Authorization string?, Signature string?, X-Request-ID string?, ] (accounts-get Authorization Signature X-Request-ID nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, optional-params any?]
   (let [res (:data (accounts-get-with-http-info Authorization Signature X-Request-ID optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-accounts-spec res st/string-transformer)
        res))))


(defn-spec accounts-transactions-get-with-http-info any?
  "Retrieval of an account transaction set (AISP)
  ### Description
This call returns transactions for an account for a given PSU account that is specified by the AISP through an account resource identification.
The request may use some filter parameter in order to restrict the query 
  - on a given imputation date range
  - past a given incremental technical identification
The result may be subject to pagination (i.e. retrieving a partial result in case of having too many results) through a set of pages by the ASPSP. Thereafter, the AISP may ask for the first, next, previous or last page of results.
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
  - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) is any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
- The TPP has previously retrieved the list of available accounts for the PSU
### Business flow
The AISP requests the ASPSP on one of the PSU’s accounts. It may specify some selection criteria.
The ASPSP answers by a set of transactions that matches the query. The result may be subject to pagination in order to avoid an excessive result set.
The default transaction set, in the absence of filter query parameter, has to be specified and documented by the implementation."
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, ] (accounts-transactions-get-with-http-info Authorization accountResourceId Signature X-Request-ID nil))
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, {:keys [dateFrom dateTo entryReferenceFrom entryReferenceto PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization accountResourceId Signature X-Request-ID)
   (call-api "/accounts/{accountResourceId}/transactions" :get
             {:path-params   {"accountResourceId" accountResourceId }
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {"dateFrom" dateFrom "dateTo" dateTo "entryReferenceFrom" entryReferenceFrom "entryReferenceto" entryReferenceto }
              :form-params   {}
              :content-types []
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["accessCode" "resourceOwnerIdentification"]})))

(defn-spec accounts-transactions-get hal-transactions-spec
  "Retrieval of an account transaction set (AISP)
  ### Description
This call returns transactions for an account for a given PSU account that is specified by the AISP through an account resource identification.
The request may use some filter parameter in order to restrict the query 
  - on a given imputation date range
  - past a given incremental technical identification
The result may be subject to pagination (i.e. retrieving a partial result in case of having too many results) through a set of pages by the ASPSP. Thereafter, the AISP may ask for the first, next, previous or last page of results.
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
  - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) is any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
- The TPP has previously retrieved the list of available accounts for the PSU
### Business flow
The AISP requests the ASPSP on one of the PSU’s accounts. It may specify some selection criteria.
The ASPSP answers by a set of transactions that matches the query. The result may be subject to pagination in order to avoid an excessive result set.
The default transaction set, in the absence of filter query parameter, has to be specified and documented by the implementation."
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, ] (accounts-transactions-get Authorization accountResourceId Signature X-Request-ID nil))
  ([Authorization string?, accountResourceId string?, Signature string?, X-Request-ID string?, optional-params any?]
   (let [res (:data (accounts-transactions-get-with-http-info Authorization accountResourceId Signature X-Request-ID optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-transactions-spec res st/string-transformer)
        res))))


(defn-spec consents-put-with-http-info any?
  "Forwarding the PSU consent (AISP)
  ### Description
In the mixed detailed consent on accounts
- the AISP captures the consent of the PSU
- then it forwards this consent to the ASPSP
This consent replaces any prior consent that was previously sent by the AISP.
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
  - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
### Business Flow
The PSU specifies to the AISP which of his/her accounts will be accessible and which functionalities should be available.
The AISP forwards these settings to the ASPSP.
The ASPSP answers by HTTP201 return code."
  ([Authorization string?, Signature string?, X-Request-ID string?, access access, ] (consents-put-with-http-info Authorization Signature X-Request-ID access nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, access access, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization Signature X-Request-ID access)
   (call-api "/consents" :put
             {:path-params   {}
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :body-param    access
              :content-types ["application/json"]
              :accepts       ["application/json; charset=utf-8"]
              :auth-names    ["accessCode" "resourceOwnerIdentification"]})))

(defn-spec consents-put any?
  "Forwarding the PSU consent (AISP)
  ### Description
In the mixed detailed consent on accounts
- the AISP captures the consent of the PSU
- then it forwards this consent to the ASPSP
This consent replaces any prior consent that was previously sent by the AISP.
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
  - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
### Business Flow
The PSU specifies to the AISP which of his/her accounts will be accessible and which functionalities should be available.
The AISP forwards these settings to the ASPSP.
The ASPSP answers by HTTP201 return code."
  ([Authorization string?, Signature string?, X-Request-ID string?, access access, ] (consents-put Authorization Signature X-Request-ID access nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, access access, optional-params any?]
   (let [res (:data (consents-put-with-http-info Authorization Signature X-Request-ID access optional-params))]
     (if (:decode-models *api-context*)
        (st/decode any? res st/string-transformer)
        res))))


(defn-spec end-user-identity-get-with-http-info any?
  "Retrieval of the identity of the end-user (AISP)
  ### Description
This call returns the identity of the PSU (end-user).
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
    - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
### Business Flow
  The AISP asks for the identity of the PSU.
  The ASPSP answers with the identity, i.e. first and last names of the end-user."
  ([Authorization string?, Signature string?, X-Request-ID string?, ] (end-user-identity-get-with-http-info Authorization Signature X-Request-ID nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization Signature X-Request-ID)
   (call-api "/end-user-identity" :get
             {:path-params   {}
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :content-types []
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["accessCode" "resourceOwnerIdentification"]})))

(defn-spec end-user-identity-get hal-end-user-identity-spec
  "Retrieval of the identity of the end-user (AISP)
  ### Description
This call returns the identity of the PSU (end-user).
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
    - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
### Business Flow
  The AISP asks for the identity of the PSU.
  The ASPSP answers with the identity, i.e. first and last names of the end-user."
  ([Authorization string?, Signature string?, X-Request-ID string?, ] (end-user-identity-get Authorization Signature X-Request-ID nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, optional-params any?]
   (let [res (:data (end-user-identity-get-with-http-info Authorization Signature X-Request-ID optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-end-user-identity-spec res st/string-transformer)
        res))))


(defn-spec trusted-beneficiaries-get-with-http-info any?
  "Retrieval of the trusted beneficiaries list (AISP)
  ### Description
This call returns all trusted beneficiaries that have been set by the PSU.
Those beneficiaries can benefit from an SCA exemption during payment initiation.
The result may be subject to pagination (i.e. retrieving a partial result in case of having too many results) through a set of pages by the ASPSP. Thereafter, the AISP may ask for the first, next, previous or last page of results.
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
    - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
### Business Flow
The AISP asks for the trusted beneficiaries list.
The ASPSP answers with a list of beneficiary details structure."
  ([Authorization string?, Signature string?, X-Request-ID string?, ] (trusted-beneficiaries-get-with-http-info Authorization Signature X-Request-ID nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization Signature X-Request-ID)
   (call-api "/trusted-beneficiaries" :get
             {:path-params   {}
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :content-types []
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["accessCode" "resourceOwnerIdentification"]})))

(defn-spec trusted-beneficiaries-get hal-beneficiaries-spec
  "Retrieval of the trusted beneficiaries list (AISP)
  ### Description
This call returns all trusted beneficiaries that have been set by the PSU.
Those beneficiaries can benefit from an SCA exemption during payment initiation.
The result may be subject to pagination (i.e. retrieving a partial result in case of having too many results) through a set of pages by the ASPSP. Thereafter, the AISP may ask for the first, next, previous or last page of results.
### Prerequisites
- The TPP has been registered by the Registration Authority for the AISP role.
- The TPP and the PSU have a contract that has been enrolled by the ASPSP
    - At this step, the ASPSP has delivered an OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token to the TPP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication
- The TPP has presented its OAUTH2 \"Authorization Code\" or \"Resource Owner Password\" access token which allows the ASPSP to identify the relevant PSU and retrieve the linked PSU context (cf. § 3.4.2) if any.
- The ASPSP takes into account the access token that establishes the link between the PSU and the AISP.
### Business Flow
The AISP asks for the trusted beneficiaries list.
The ASPSP answers with a list of beneficiary details structure."
  ([Authorization string?, Signature string?, X-Request-ID string?, ] (trusted-beneficiaries-get Authorization Signature X-Request-ID nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, optional-params any?]
   (let [res (:data (trusted-beneficiaries-get-with-http-info Authorization Signature X-Request-ID optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-beneficiaries-spec res st/string-transformer)
        res))))


