package org.egov.tl.commons.web.contract;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LicenseSearchContract {

	@NotNull
	private String tenantId;

	private String active;

	private Integer[] ids;

	private String applicationNumber;

	private String licenseNumber;

	private String oldLicenseNumber;

	private String mobileNumber;

	private String aadhaarNumber;

	private String emailId;

	private String propertyAssesmentNo;

	private String adminWard;

	private String locality;

	private String ownerName;

	private String tradeTitle;

	private String tradeType;

	private String tradeCategory;

	private String tradeSubCategory;

	private String legacy;

	private String status;

	private String applicationStatus;

	private Integer pageSize;

	private Integer pageNumber;

	private String sort;
}