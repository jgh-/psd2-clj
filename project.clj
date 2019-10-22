(defproject psd2 "1.4.2.17"
  :description "This API intends to provide an interface between
- Account Servicing Payment Service Providers (ASPSP)
- Third Party (Payment Service) Providers (TPP)

TPP may act with different roles as described below:
- Account Information Service Providers (AISP)
- Payment Initiation Service Providers (PISP)
- Card Based Payment Instrument Issuers (CBPII)

The Payment Service User (PSU) is the owner of the accounts held by the ASPSP and gives accreditations to the TPP in order to access his accounts information or initiates payment from these accounts

The API is designed on a REST model using JSON structures.

The Richardson Maturity Model is applied on level three using HAL HYPERMEDIA links"
  :url "https://www.stet.eu/en/psd2/"
  :license {:name "Creative Commons Attribution 3.0 France (CC BY 3.0 FR)"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [metosin/spec-tools "0.7.0"]
                 [clj-http "3.8.0"]
                 [orchestra "2017.11.12-1"]
                 [cheshire "5.8.0"]])