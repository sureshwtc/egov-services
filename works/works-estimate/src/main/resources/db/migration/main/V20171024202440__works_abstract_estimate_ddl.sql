CREATE TABLE egw_abstractestimate
(
  id character varying(256),
  tenantId character varying(256) NOT NULL,
  abstractestimatenumber character varying(20),
  subject character varying(100) NOT NULL,
  fund character varying(50),
  referencetype character varying(128),
  referencenumber character varying(100) NOT NULL,
  function character varying(50),
  description character varying(1024) NOT NULL,
  budgethead character varying(50),
  scheme character varying(50),
  subscheme character varying(50),
  dateofproposal bigint NOT NULL,
  department character varying(50) NOT NULL,
  adminsanctionnumber character varying(50),
  adminsanctiondate bigint,
  adminsanctionby character varying(50),
  status character varying(50) NOT NULL,
  beneficiary character varying(100) NOT NULL,
  modeofallotment character varying(100) NOT NULL,
  typeofwork character varying(50) NOT NULL,
  natureofwork character varying(50) NOT NULL,
  subtypeofwork character varying(50),
  ward character varying(50) NOT NULL,
  technicalsanctionnumber character varying(50),
  technicalsanctiondate bigint,
  technicalsanctionby character varying(50),
  locality character varying(50),
  workcategory character varying(100) NOT NULL,
  councilresolutionnumber character varying(100),
  councilresolutiondate bigint,
  workordercreated boolean DEFAULT false,
  billscreated boolean DEFAULT false,
  spilloverflag boolean DEFAULT false,
  cancellationreason character varying(512),
  cancellationremarks character varying(512),
  detailedEstimateCreated boolean DEFAULT false,
  stateId character varying(50),
  asset character varying(50),
  implementationPeriod bigint,
  fundAvailable boolean DEFAULT false,
  fundSanctioningAuthority character varying(50),
  pmcRequired boolean DEFAULT false,
  pmcType character varying(50),
  pmcName character varying(50),
  createdby bigint NOT NULL,
  createddate bigint NOT NULL,
  lastmodifiedby bigint,
  lastmodifieddate bigint,
  CONSTRAINT tenantid_abstractestimatenumber_unique UNIQUE (tenantId, abstractestimatenumber)
  );


CREATE TABLE egw_abstractestimate_details
(
  id character varying(256),
  tenantId character varying(256) NOT NULL,
  abstractEstimate character varying(50),
  nameofwork character varying(1024) NOT NULL,
  estimateamount double precision NOT NULL,
  estimatenumber character varying(50) NOT NULL,
  createdby bigint NOT NULL,
  createddate bigint NOT NULL,
  lastmodifiedby bigint,
  lastmodifieddate bigint,
  grossAmountBilled double precision,
  projectCode character varying(50),
  CONSTRAINT tenantid_estimatenumber_unique UNIQUE (tenantId, estimatenumber)
);


CREATE TABLE egw_abstractestimate_appropriation
(
  id character varying(256),
  tenantId character varying(250) NOT NULL,
  abstractEstimateDetails character varying(50),
  detailedEstimate character varying(50),
  budgetusage character varying(50),
  createdby bigint NOT NULL,
  createddate bigint NOT NULL,
  lastmodifiedby bigint,
  lastmodifieddate bigint,
  CONSTRAINT tenantid_detailedEstimate_unique UNIQUE (tenantId, detailedEstimate)
);

CREATE TABLE egw_abstractestimate_photographs
(
  id character varying(256),
  tenantId character varying(250) NOT NULL,
  abstractEstimateDetails character varying(50),
  latitude double precision, 
  longitude double precision, 
  description character varying(1024),
  dateOfCapture bigint,
  fileStoreMapper character varying(50) NOT NULL,
  workProgress character varying(50) NOT NULL,
  detailedEstimate character varying(50),
  createdby bigint NOT NULL,
  createddate bigint NOT NULL,
  lastmodifiedby bigint,
  lastmodifieddate bigint,
  CONSTRAINT tenantid_estimatedetails_unique UNIQUE (tenantId, abstractEstimateDetails)
);

CREATE TABLE egw_projectcode
(
  id character varying(256),
  tenantId character varying(256) NOT NULL,
  code character varying(100) NOT NULL,
  detailedEstimate character varying(50), 
  description character varying(1024),
  name character varying(100),
  status character varying(50),
  completionDate bigint,
  projectValue double precision DEFAULT 0,
  createdby bigint NOT NULL,
  createddate bigint NOT NULL,
  lastmodifiedby bigint,
  lastmodifieddate bigint,
  CONSTRAINT tenantid_code_unique UNIQUE (tenantId, code)
);
