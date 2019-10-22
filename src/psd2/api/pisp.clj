(ns psd2.api.pisp
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


(defn-spec payment-request-confirmation-post-with-http-info any?
  "Confirmation of a payment request or a modification request using a standard PSU authentication (PISP)
  ### Description
The PISP confirms one of the following requests or modifications
- payment request on behalf of a merchant
- transfer request on behalf of the account's owner
- standing-order request on behalf of the account's owner
The ASPSP answers with a status of the relevant request and the subsequent Credit Transfer.

### Prerequisites
-  The TPP has been registered by the Registration Authority for the PISP role
-  The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
-  The TPP has previously posted a Request which has been saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP has answered with a location link to the saved Payment Request (cf. § 4.5.4)
  -  The TPP has retrieved the saved request in order to get the relevant resource Ids (cf. § 4.6).
-  The TPP and the ASPSP have successfully processed a mutual check and authentication 
-  The TPP has presented its \"OAUTH2 Client Credential\" access token 
  
### Business flow
Once the PSU has been authenticated using a standard procedure (non OAUTH2), it is the due to the PISP to confirm the Request to the ASPSP in order to complete the process flow.
The ASPSP must wait for confirmation before executing the subsequent Credit Tranfer."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, ] (payment-request-confirmation-post-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource)
   (call-api "/payment-requests/{paymentRequestResourceId}/confirmation" :post
             {:path-params   {"paymentRequestResourceId" paymentRequestResourceId }
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :body-param    confirmation-resource
              :content-types ["application/json"]
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["clientCredentials"]})))

(defn-spec payment-request-confirmation-post hal-payment-request-spec
  "Confirmation of a payment request or a modification request using a standard PSU authentication (PISP)
  ### Description
The PISP confirms one of the following requests or modifications
- payment request on behalf of a merchant
- transfer request on behalf of the account's owner
- standing-order request on behalf of the account's owner
The ASPSP answers with a status of the relevant request and the subsequent Credit Transfer.

### Prerequisites
-  The TPP has been registered by the Registration Authority for the PISP role
-  The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
-  The TPP has previously posted a Request which has been saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP has answered with a location link to the saved Payment Request (cf. § 4.5.4)
  -  The TPP has retrieved the saved request in order to get the relevant resource Ids (cf. § 4.6).
-  The TPP and the ASPSP have successfully processed a mutual check and authentication 
-  The TPP has presented its \"OAUTH2 Client Credential\" access token 
  
### Business flow
Once the PSU has been authenticated using a standard procedure (non OAUTH2), it is the due to the PISP to confirm the Request to the ASPSP in order to complete the process flow.
The ASPSP must wait for confirmation before executing the subsequent Credit Tranfer."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, ] (payment-request-confirmation-post Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, optional-params any?]
   (let [res (:data (payment-request-confirmation-post-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-payment-request-spec res st/string-transformer)
        res))))


(defn-spec payment-request-o-confirmation-post-with-http-info any?
  "Confirmation of a payment request or a modification request using an OAUTH2 Authorization code grant (PISP)
  ### Description
The PISP confirms one of the following requests or modifications:
- payment request on behalf of a merchant
- transfer request on behalf of the account's owner
- standing-order request on behalf of the account's owner
The ASPSP answers with a status of the relevant request and the subsequent Credit Transfer.

### Prerequisites
-  The TPP has been registered by the Registration Authority for the PISP role
-  The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
-  The TPP has previously posted a Request which has been saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP has answered with a location link to the saved Payment Request (cf. § 4.5.4)
  -  The TPP has retrieved the saved request in order to get the relevant resource Ids (cf. § 4.6).
-  The PSU has been authenticated by the ASPSP through an OAUTH2 authorization code grant flow (REDIRECT approach) and the PISP got the relevant token
-  The TPP and the ASPSP have successfully processed a mutual check and authentication 
-  The TPP has presented its \"OAUTH2 Authorization Code\" access token 
  
### Business flow
Once the PSU has been authenticated through an OAUTH2 authorization code grant flow (REDIRECT approach), it is the due to the PISP to confirm the Request to the ASPSP in order to complete the process flow.
The ASPSP must wait for confirmation before executing the subsequent Credit Tranfer."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, ] (payment-request-o-confirmation-post-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource)
   (call-api "/payment-requests/{paymentRequestResourceId}/o-confirmation" :post
             {:path-params   {"paymentRequestResourceId" paymentRequestResourceId }
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :body-param    confirmation-resource
              :content-types ["application/json"]
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["accessCode"]})))

(defn-spec payment-request-o-confirmation-post hal-payment-request-spec
  "Confirmation of a payment request or a modification request using an OAUTH2 Authorization code grant (PISP)
  ### Description
The PISP confirms one of the following requests or modifications:
- payment request on behalf of a merchant
- transfer request on behalf of the account's owner
- standing-order request on behalf of the account's owner
The ASPSP answers with a status of the relevant request and the subsequent Credit Transfer.

### Prerequisites
-  The TPP has been registered by the Registration Authority for the PISP role
-  The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
-  The TPP has previously posted a Request which has been saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP has answered with a location link to the saved Payment Request (cf. § 4.5.4)
  -  The TPP has retrieved the saved request in order to get the relevant resource Ids (cf. § 4.6).
-  The PSU has been authenticated by the ASPSP through an OAUTH2 authorization code grant flow (REDIRECT approach) and the PISP got the relevant token
-  The TPP and the ASPSP have successfully processed a mutual check and authentication 
-  The TPP has presented its \"OAUTH2 Authorization Code\" access token 
  
### Business flow
Once the PSU has been authenticated through an OAUTH2 authorization code grant flow (REDIRECT approach), it is the due to the PISP to confirm the Request to the ASPSP in order to complete the process flow.
The ASPSP must wait for confirmation before executing the subsequent Credit Tranfer."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, ] (payment-request-o-confirmation-post Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, confirmation-resource confirmation-resource, optional-params any?]
   (let [res (:data (payment-request-o-confirmation-post-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID confirmation-resource optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-payment-request-spec res st/string-transformer)
        res))))


(defn-spec payment-request-put-with-http-info any?
  "Modification of a Payment/Transfer Request (PISP)
  ### Description
The PISP sent a Payment/Transfer Request through a POST command.
The ASPSP registered the Payment/Transfer Request, updated if necessary the relevant identifiers in order to avoid duplicates and returned the location of the updated Request.
The PISP got the Payment/Transfer Request that has been updated with the resource identifiers, and eventually the status of the Payment/Transfer Request and the status of the subsequent credit transfer.
The PISP request for the payment cancellation (global cancellation) or for some payment instructions cancellation (partial cancellation)
No other modification of the Payment/Transfer Request is allowed.
### Prerequisites
- The TPP was registered by the Registration Authority for the PISP role
- The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
- The TPP previously posted a Payment/Transfer Request which was saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP answered with a location link to the saved Payment/Transfer Request (cf. § 4.5.4)
  - The PISP retrieved the saved Payment/Transfer Request (cf. § 4.5.4)
- The TPP and the ASPSP successfully processed a mutual check and authentication 
- The TPP presented its \"OAUTH2 Client Credential\" access token.
- The TPP presented the payment/transfer request.
- The PSU was successfully authenticated.

### Business flow
#### Payment/Transfer request cancellation circumstances
The cancellation of a Payment/Transfer request might be triggered by the PISP upon request of the PSU.
It can also be triggered by the PISP itself in case of error or fraud detection.
Since the consequence of the cancellation will be a rejection of the Payment/Transfer request globally or limited to some of its instructions, the modification of the payment request will focus on setting the relevant status to the value \"CANC\".
This \"CANC\" status must however be explained through a reason code that can be set with the following values:

| Reason | description |
| ------ | ----------- |
| DS02 | The PSU ordered the cancellation. |
| DUPL | The PISP requests the cancellation for a duplication of a previous Payment/Transfer request |
| FRAD | The PISP requests the cancellation for fraudulent origin of the Payment/Transfer request |
| TECH | The PISP requests the cancellation for a technical issue on its side |

#### Payment/Transfer request cancellation level
- Case of a payment with multiple instructions or a standing order, the PISP asks to cancel the whole Payment/Transfer or Standing Order Request including all non-executed payment instructions by setting the [paymentInformationStatus] and the relevant [statusReasonInformation]  at payment level.
- Case of a payment with multiple instructions, the PISP asks to cancel one or several payment instructions by setting the [transactionStatus] and the relevant [statusReasonInformation] at each relevant instruction level.

Since the modification request needs a PSU authentication before committing, the modification request includes:
- The specification of the authentication approaches that are supported by the PISP (any combination of \"REDIRECT\", \"EMBEDDED-1-FACTOR\" and \"DECOUPLED\" values).
- In case of possible REDIRECT or DECOUPLED authentication approach, one or two call-back URLs to be used by the ASPSP at the finalisation of the authentication and consent process :
  - The first call-back URL will be called by the ASPSP if the Transfer Request is processed without any error or rejection by the PSU
  - The second call-back URL is to be used by the ASPSP in case of processing error or rejection by the PSU. Since this second URL is optional, the PISP might not provide it. In this case, the ASPSP will use the same URL for any processing result.
  - Both call-back URLS must be used in a TLS-secured request.
- In case of possible \"EMBEDDED-1-FACTOR\" or \"DECOUPLED\" approaches, a PSU identifier that can be processed by the ASPSP for PSU recognition.
  
- The ASPSP saves the updated Payment/Transfer Request and answers to the PISP. The answer embeds 
  - The specification of the chosen authentication approach taking into account both the PISP and the PSU capabilities.
  - In case of chosen REDIRECT authentication approach, the URL to be used by the PISP for redirecting the PSU in order to perform an authentication.
  
Case of the PSU neither gives nor denies his/her consent, the Cancellation Request shall expire and is then rejected to the PISP. The expiration delay is specified by each ASPSP.
If any modification of the payment request other than cancellation is applied by the PISP, the ASPSP must rejest the request with HTTP403 without modifying the payment request resource."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, ] (payment-request-put-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID payment-request-resource nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization paymentRequestResourceId Signature X-Request-ID payment-request-resource)
   (call-api "/payment-requests/{paymentRequestResourceId}" :put
             {:path-params   {"paymentRequestResourceId" paymentRequestResourceId }
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :body-param    payment-request-resource
              :content-types ["application/json"]
              :accepts       ["*/*"]
              :auth-names    ["clientCredentials"]})))

(defn-spec payment-request-put hal-payment-request-creation-spec
  "Modification of a Payment/Transfer Request (PISP)
  ### Description
The PISP sent a Payment/Transfer Request through a POST command.
The ASPSP registered the Payment/Transfer Request, updated if necessary the relevant identifiers in order to avoid duplicates and returned the location of the updated Request.
The PISP got the Payment/Transfer Request that has been updated with the resource identifiers, and eventually the status of the Payment/Transfer Request and the status of the subsequent credit transfer.
The PISP request for the payment cancellation (global cancellation) or for some payment instructions cancellation (partial cancellation)
No other modification of the Payment/Transfer Request is allowed.
### Prerequisites
- The TPP was registered by the Registration Authority for the PISP role
- The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
- The TPP previously posted a Payment/Transfer Request which was saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP answered with a location link to the saved Payment/Transfer Request (cf. § 4.5.4)
  - The PISP retrieved the saved Payment/Transfer Request (cf. § 4.5.4)
- The TPP and the ASPSP successfully processed a mutual check and authentication 
- The TPP presented its \"OAUTH2 Client Credential\" access token.
- The TPP presented the payment/transfer request.
- The PSU was successfully authenticated.

### Business flow
#### Payment/Transfer request cancellation circumstances
The cancellation of a Payment/Transfer request might be triggered by the PISP upon request of the PSU.
It can also be triggered by the PISP itself in case of error or fraud detection.
Since the consequence of the cancellation will be a rejection of the Payment/Transfer request globally or limited to some of its instructions, the modification of the payment request will focus on setting the relevant status to the value \"CANC\".
This \"CANC\" status must however be explained through a reason code that can be set with the following values:

| Reason | description |
| ------ | ----------- |
| DS02 | The PSU ordered the cancellation. |
| DUPL | The PISP requests the cancellation for a duplication of a previous Payment/Transfer request |
| FRAD | The PISP requests the cancellation for fraudulent origin of the Payment/Transfer request |
| TECH | The PISP requests the cancellation for a technical issue on its side |

#### Payment/Transfer request cancellation level
- Case of a payment with multiple instructions or a standing order, the PISP asks to cancel the whole Payment/Transfer or Standing Order Request including all non-executed payment instructions by setting the [paymentInformationStatus] and the relevant [statusReasonInformation]  at payment level.
- Case of a payment with multiple instructions, the PISP asks to cancel one or several payment instructions by setting the [transactionStatus] and the relevant [statusReasonInformation] at each relevant instruction level.

Since the modification request needs a PSU authentication before committing, the modification request includes:
- The specification of the authentication approaches that are supported by the PISP (any combination of \"REDIRECT\", \"EMBEDDED-1-FACTOR\" and \"DECOUPLED\" values).
- In case of possible REDIRECT or DECOUPLED authentication approach, one or two call-back URLs to be used by the ASPSP at the finalisation of the authentication and consent process :
  - The first call-back URL will be called by the ASPSP if the Transfer Request is processed without any error or rejection by the PSU
  - The second call-back URL is to be used by the ASPSP in case of processing error or rejection by the PSU. Since this second URL is optional, the PISP might not provide it. In this case, the ASPSP will use the same URL for any processing result.
  - Both call-back URLS must be used in a TLS-secured request.
- In case of possible \"EMBEDDED-1-FACTOR\" or \"DECOUPLED\" approaches, a PSU identifier that can be processed by the ASPSP for PSU recognition.
  
- The ASPSP saves the updated Payment/Transfer Request and answers to the PISP. The answer embeds 
  - The specification of the chosen authentication approach taking into account both the PISP and the PSU capabilities.
  - In case of chosen REDIRECT authentication approach, the URL to be used by the PISP for redirecting the PSU in order to perform an authentication.
  
Case of the PSU neither gives nor denies his/her consent, the Cancellation Request shall expire and is then rejected to the PISP. The expiration delay is specified by each ASPSP.
If any modification of the payment request other than cancellation is applied by the PISP, the ASPSP must rejest the request with HTTP403 without modifying the payment request resource."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, ] (payment-request-put Authorization paymentRequestResourceId Signature X-Request-ID payment-request-resource nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, optional-params any?]
   (let [res (:data (payment-request-put-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID payment-request-resource optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-payment-request-creation-spec res st/string-transformer)
        res))))


(defn-spec payment-requests-get-with-http-info any?
  "Retrieval of a payment request (PISP)
  ### Description
The following use cases can be applied:
- retrieval of a payment request on behalf of a merchant
- retrieval of a transfer request on behalf of the account's owner
- retrieval of a standing-order request on behalf of the account's owner

The PISP has previously sent a Request through a POST command.
- The ASPSP has registered the Request, updated if necessary the relevant identifiers in order to avoid duplicates and returned the location of the updated Request.
- The PISP gets the Request that has been updated with the resource identifiers, and eventually the status of the Payment/Transfer Request and the status of the subsequent credit transfer.

### Prerequisites
- The TPP has been registered by the Registration Authority for the PISP role
- The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
- The TPP has previously posted a Request which has been saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP has answered with a location link to the saved Payment/Transfer Request (cf. § 4.5.4)
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its \"OAUTH2 Client Credential\" access token

### Business flow
The PISP asks to retrieve the Payment/Transfer Request that has been saved by the ASPSP. The PISP uses the location link provided by the ASPSP in response of the posting of this request.
The ASPSP returns the previously posted Payment/Transfer Request which is enriched with:
- The resource identifiers given by the ASPSP
- The status information of the Payment Request and of the subsequent credit transfer
The status information must be available during at least 30 calendar days after the posting of the Payment Request. However, the ASPSP may increase this availability duration, based on its own rules."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, ] (payment-requests-get-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest]} (s/map-of keyword? any?)]
   (check-required-params Authorization paymentRequestResourceId Signature X-Request-ID)
   (call-api "/payment-requests/{paymentRequestResourceId}" :get
             {:path-params   {"paymentRequestResourceId" paymentRequestResourceId }
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {}
              :form-params   {}
              :content-types []
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["clientCredentials"]})))

(defn-spec payment-requests-get hal-payment-request-spec
  "Retrieval of a payment request (PISP)
  ### Description
The following use cases can be applied:
- retrieval of a payment request on behalf of a merchant
- retrieval of a transfer request on behalf of the account's owner
- retrieval of a standing-order request on behalf of the account's owner

The PISP has previously sent a Request through a POST command.
- The ASPSP has registered the Request, updated if necessary the relevant identifiers in order to avoid duplicates and returned the location of the updated Request.
- The PISP gets the Request that has been updated with the resource identifiers, and eventually the status of the Payment/Transfer Request and the status of the subsequent credit transfer.

### Prerequisites
- The TPP has been registered by the Registration Authority for the PISP role
- The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
- The TPP has previously posted a Request which has been saved by the ASPSP (cf. § 4.5.3)
  - The ASPSP has answered with a location link to the saved Payment/Transfer Request (cf. § 4.5.4)
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its \"OAUTH2 Client Credential\" access token

### Business flow
The PISP asks to retrieve the Payment/Transfer Request that has been saved by the ASPSP. The PISP uses the location link provided by the ASPSP in response of the posting of this request.
The ASPSP returns the previously posted Payment/Transfer Request which is enriched with:
- The resource identifiers given by the ASPSP
- The status information of the Payment Request and of the subsequent credit transfer
The status information must be available during at least 30 calendar days after the posting of the Payment Request. However, the ASPSP may increase this availability duration, based on its own rules."
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, ] (payment-requests-get Authorization paymentRequestResourceId Signature X-Request-ID nil))
  ([Authorization string?, paymentRequestResourceId string?, Signature string?, X-Request-ID string?, optional-params any?]
   (let [res (:data (payment-requests-get-with-http-info Authorization paymentRequestResourceId Signature X-Request-ID optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-payment-request-spec res st/string-transformer)
        res))))


(defn-spec payment-requests-post-with-http-info any?
  "Payment request initiation (PISP)
  ### Description
The following use cases can be applied:
- payment request on behalf of a merchant
- transfer request on behalf of the account's owner
- standing-order request on behalf of the account's owner
#### Data content
A payment request or a transfer request might embed several payment instructions having
- one single execution date or multiple execution dates
  - case of one single execution date, this date must be set at the payment level
  - case of multiple execution dates, those dates must be set at each payment instruction level
- one single beneficiary or multiple beneficiaries
  - case of one single beneficiary, this beneficiary must be set at the payment level
  - case of multiple beneficiaries, those beneficiaries must be set at each payment instruction level
Having at the same time multiple beneficiaries and multiple execution date might not be a relevant business case, although it is technically allowed.
Each implementation will have to specify which business use cases are actually supported.       
A standing order request must embed one single payment instruction and must address one single beneficiary.
- The beneficiary must be set at the payment level
- The standing order specific characteristics (start date, periodicity...) must be set at the instruction level
                   
Payment request can rely for execution on different payment instruments:
- SEPA Credit Transfer (SCT)
- Domestic Credit Transfer in a non-Euro-currency
- International payment
The following table indicates how to use the different fields, depending on the payment instrument:

| Structure | SEPA payments | Domestic payments in non-euro currency | International payments |
| --------- | ------------- | -------------------------------------- | ---------------------- |
| PaymentTypeInformation/InstructionPriority (payment level) | \"HIGH\" for high-priority SCT, \"NORM\" for other SCT, Ignored for SCTInst | \"HIGH\" for high-priority CT, \"NORM\" or ignored for other CT | \"HIGH\" for high-priority payments, \"NORM\" or ignored for other payments |
| PaymentTypeInformation/ServiceLevel (payment level) | \"SEPA\" for SCT and SCTInst | ignored | ignored |
| PaymentTypeInformation/CategoryPurpose (payment level) | \"CASH\" for transfer request, \"DVPM\" for payment request on behalf of a merchant || \"CORT\" for generic international payments, \"INTC\" for transfers between two branches within the same company, \"TREA\" for treasury transfers |
| PaymentTypeInformation/LocalInstrument (payment level) | \"INST\" pour les SCTInst, otherwise ignored | Ignored or valued with ISO20022 external code ||
| RequestedExecutionDate (either at payment or transaction level) | Mandatory (indicates the date on debit on the ordering party account) |||
| EndToEndIdentification (at transaction level) | Mandatory | Optional ||
| UltimateDebtor (at transaction level) | Optional |||
| UltimateCreditor (at transaction level) | Optional |||
| InstructedAmount (at transaction level) | Mandatory || Mandatory and exclusive use of one of these structures |
| EquivalentAmount (at transaction level) | Not used || Mandatory and exclusive use of one of these structures |
| ChargeBearer (at transaction level) | \"SLEV\" for SCT and SCTInst | \"SLEV\" or \"SHAR\" | \"CRED\", \"DEBT\" or \"SHAR\" |
| Purpose (at transaction level) | Optional |||
| RegulatoryReportingCode (at transaction level) | Not used | Mandatory (possibly multiple values) |
| InstructionForCreditorAgent (at transaction level) | Not used || Optional (possibly multiple values) |
| RemittanceInformation | Mandatory. Structured or unstructured, depending on the local rules and constraints |||
| Debtor (at payment level) | Mandatory, 2 address lines only | Mandatory, 4 address lines only | Mandatory. Complete strustured address can be used. |
| DebtorAccount (at payment level) | Optional | Optional. Account currency may be specified ||
| DebtorAgent (at payment level) | Optional |||
| Creditor (either at payment or transaction level) | Mandatory, 2 address lines only | Mandatory, 4 address lines only | Mandatory. Complete strustured address can be used. Date and place of birth must be specified |
| CreditorAccount (either at payment or transaction level) | Mandatory | Mandatory. Account currency may be specified ||
| CreditorAgent (either at payment or transaction level) | Optional |||
| ClearingSystemId et ClearingSystemMemberId (either at payment or transaction level) | Not used || Optional |
| IntermediaryAgent et IntermediaryAgentAccount (either at payment or transaction level) | Not used | Optional ||
  
#### Prerequisites for all use cases
- The TPP has been registered by the Registration Authority for the PISP role
- The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its \"OAUTH2 Client Credential\" access token

#### Business flow
##### Payment Request use case
The PISP forwards a payment request on behalf of a merchant.
The PSU buys some goods or services on an e-commerce website held by a merchant. Among other payment method, the merchant suggests the use of a PISP service. As there is obviously a contract between the merchant and the PISP, there is no need for the ASPSP to check the existence of such a contract between the PSU and this PISP to initiate the process.
Case of the PSU that chooses to use the PISP service:
- The merchant forwards the requested payment characteristics to the PISP and redirects the PSU to the PISP portal.
- The PISP requests from the PSU which ASPSP will be used.
- The PISP prepares the Payment Request and sends this request to the ASPSP.
- The Request can embed several payment instructions having different requested execution date.
- The beneficiary, as being the merchant, is set at the payment level.
     
  ##### Transfer Request use case
  The PISP forwards a transfer request on behalf of the owner of the account.
  - The PSU provides the PISP with all information needed for the transfer.
  - The PISP prepares the Transfer Request and sends this request to the relevant ASPSP that holds the debtor account.
  - The Request can embed several payment instructions having different beneficiaries.
  - The requested execution date, as being the same for all instructions, is set at the payment level.
     
  ##### Standing Order Request use case
  The PISP forwards a Standing Order request on behalf of the owner of the account.
  - The PSU provides the PISP with all information needed for the Standing Order.
  - The PISP prepares the Standing Order Request and sends this request to the relevant ASPSP that holds the debtor account.
  - The Request embeds one single payment instruction with
    - The requested execution date of the first occurrence
    - The requested execution frequency of the payment in order to compute further execution dates
    - An execution rule to handle cases when the computed execution dates cannot be processed (e.g. bank holydays)
    - An optional end date for closing the standing Order"
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, ] (payment-requests-post-with-http-info Authorization Signature X-Request-ID payment-request-resource nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, {:keys [PSU-IP-Address PSU-IP-Port PSU-HTTP-Method PSU-Date PSU-GEO-Location PSU-User-Agent PSU-Referer PSU-Accept PSU-Accept-Charset PSU-Accept-Encoding PSU-Accept-Language PSU-Device-ID Digest ui_locales]} (s/map-of keyword? any?)]
   (check-required-params Authorization Signature X-Request-ID payment-request-resource)
   (call-api "/payment-requests" :post
             {:path-params   {}
              :header-params {"Authorization" Authorization "PSU-IP-Address" PSU-IP-Address "PSU-IP-Port" PSU-IP-Port "PSU-HTTP-Method" PSU-HTTP-Method "PSU-Date" PSU-Date "PSU-GEO-Location" PSU-GEO-Location "PSU-User-Agent" PSU-User-Agent "PSU-Referer" PSU-Referer "PSU-Accept" PSU-Accept "PSU-Accept-Charset" PSU-Accept-Charset "PSU-Accept-Encoding" PSU-Accept-Encoding "PSU-Accept-Language" PSU-Accept-Language "PSU-Device-ID" PSU-Device-ID "Digest" Digest "Signature" Signature "X-Request-ID" X-Request-ID }
              :query-params  {"ui_locales" ui_locales }
              :form-params   {}
              :body-param    payment-request-resource
              :content-types ["application/json"]
              :accepts       ["application/hal+json; charset=utf-8"]
              :auth-names    ["clientCredentials"]})))

(defn-spec payment-requests-post hal-payment-request-creation-spec
  "Payment request initiation (PISP)
  ### Description
The following use cases can be applied:
- payment request on behalf of a merchant
- transfer request on behalf of the account's owner
- standing-order request on behalf of the account's owner
#### Data content
A payment request or a transfer request might embed several payment instructions having
- one single execution date or multiple execution dates
  - case of one single execution date, this date must be set at the payment level
  - case of multiple execution dates, those dates must be set at each payment instruction level
- one single beneficiary or multiple beneficiaries
  - case of one single beneficiary, this beneficiary must be set at the payment level
  - case of multiple beneficiaries, those beneficiaries must be set at each payment instruction level
Having at the same time multiple beneficiaries and multiple execution date might not be a relevant business case, although it is technically allowed.
Each implementation will have to specify which business use cases are actually supported.       
A standing order request must embed one single payment instruction and must address one single beneficiary.
- The beneficiary must be set at the payment level
- The standing order specific characteristics (start date, periodicity...) must be set at the instruction level
                   
Payment request can rely for execution on different payment instruments:
- SEPA Credit Transfer (SCT)
- Domestic Credit Transfer in a non-Euro-currency
- International payment
The following table indicates how to use the different fields, depending on the payment instrument:

| Structure | SEPA payments | Domestic payments in non-euro currency | International payments |
| --------- | ------------- | -------------------------------------- | ---------------------- |
| PaymentTypeInformation/InstructionPriority (payment level) | \"HIGH\" for high-priority SCT, \"NORM\" for other SCT, Ignored for SCTInst | \"HIGH\" for high-priority CT, \"NORM\" or ignored for other CT | \"HIGH\" for high-priority payments, \"NORM\" or ignored for other payments |
| PaymentTypeInformation/ServiceLevel (payment level) | \"SEPA\" for SCT and SCTInst | ignored | ignored |
| PaymentTypeInformation/CategoryPurpose (payment level) | \"CASH\" for transfer request, \"DVPM\" for payment request on behalf of a merchant || \"CORT\" for generic international payments, \"INTC\" for transfers between two branches within the same company, \"TREA\" for treasury transfers |
| PaymentTypeInformation/LocalInstrument (payment level) | \"INST\" pour les SCTInst, otherwise ignored | Ignored or valued with ISO20022 external code ||
| RequestedExecutionDate (either at payment or transaction level) | Mandatory (indicates the date on debit on the ordering party account) |||
| EndToEndIdentification (at transaction level) | Mandatory | Optional ||
| UltimateDebtor (at transaction level) | Optional |||
| UltimateCreditor (at transaction level) | Optional |||
| InstructedAmount (at transaction level) | Mandatory || Mandatory and exclusive use of one of these structures |
| EquivalentAmount (at transaction level) | Not used || Mandatory and exclusive use of one of these structures |
| ChargeBearer (at transaction level) | \"SLEV\" for SCT and SCTInst | \"SLEV\" or \"SHAR\" | \"CRED\", \"DEBT\" or \"SHAR\" |
| Purpose (at transaction level) | Optional |||
| RegulatoryReportingCode (at transaction level) | Not used | Mandatory (possibly multiple values) |
| InstructionForCreditorAgent (at transaction level) | Not used || Optional (possibly multiple values) |
| RemittanceInformation | Mandatory. Structured or unstructured, depending on the local rules and constraints |||
| Debtor (at payment level) | Mandatory, 2 address lines only | Mandatory, 4 address lines only | Mandatory. Complete strustured address can be used. |
| DebtorAccount (at payment level) | Optional | Optional. Account currency may be specified ||
| DebtorAgent (at payment level) | Optional |||
| Creditor (either at payment or transaction level) | Mandatory, 2 address lines only | Mandatory, 4 address lines only | Mandatory. Complete strustured address can be used. Date and place of birth must be specified |
| CreditorAccount (either at payment or transaction level) | Mandatory | Mandatory. Account currency may be specified ||
| CreditorAgent (either at payment or transaction level) | Optional |||
| ClearingSystemId et ClearingSystemMemberId (either at payment or transaction level) | Not used || Optional |
| IntermediaryAgent et IntermediaryAgentAccount (either at payment or transaction level) | Not used | Optional ||
  
#### Prerequisites for all use cases
- The TPP has been registered by the Registration Authority for the PISP role
- The TPP was provided with an OAUTH2 \"Client Credential\" access token by the ASPSP (cf. § 3.4.2).
- The TPP and the ASPSP have successfully processed a mutual check and authentication 
- The TPP has presented its \"OAUTH2 Client Credential\" access token

#### Business flow
##### Payment Request use case
The PISP forwards a payment request on behalf of a merchant.
The PSU buys some goods or services on an e-commerce website held by a merchant. Among other payment method, the merchant suggests the use of a PISP service. As there is obviously a contract between the merchant and the PISP, there is no need for the ASPSP to check the existence of such a contract between the PSU and this PISP to initiate the process.
Case of the PSU that chooses to use the PISP service:
- The merchant forwards the requested payment characteristics to the PISP and redirects the PSU to the PISP portal.
- The PISP requests from the PSU which ASPSP will be used.
- The PISP prepares the Payment Request and sends this request to the ASPSP.
- The Request can embed several payment instructions having different requested execution date.
- The beneficiary, as being the merchant, is set at the payment level.
     
  ##### Transfer Request use case
  The PISP forwards a transfer request on behalf of the owner of the account.
  - The PSU provides the PISP with all information needed for the transfer.
  - The PISP prepares the Transfer Request and sends this request to the relevant ASPSP that holds the debtor account.
  - The Request can embed several payment instructions having different beneficiaries.
  - The requested execution date, as being the same for all instructions, is set at the payment level.
     
  ##### Standing Order Request use case
  The PISP forwards a Standing Order request on behalf of the owner of the account.
  - The PSU provides the PISP with all information needed for the Standing Order.
  - The PISP prepares the Standing Order Request and sends this request to the relevant ASPSP that holds the debtor account.
  - The Request embeds one single payment instruction with
    - The requested execution date of the first occurrence
    - The requested execution frequency of the payment in order to compute further execution dates
    - An execution rule to handle cases when the computed execution dates cannot be processed (e.g. bank holydays)
    - An optional end date for closing the standing Order"
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, ] (payment-requests-post Authorization Signature X-Request-ID payment-request-resource nil))
  ([Authorization string?, Signature string?, X-Request-ID string?, payment-request-resource payment-request-resource, optional-params any?]
   (let [res (:data (payment-requests-post-with-http-info Authorization Signature X-Request-ID payment-request-resource optional-params))]
     (if (:decode-models *api-context*)
        (st/decode hal-payment-request-creation-spec res st/string-transformer)
        res))))


