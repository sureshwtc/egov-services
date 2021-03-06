package org.egov.works.estimate.web.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.egov.works.commons.domain.model.AuditDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * An Object holds the basic data for a Multi Year Estimate
 */
@ApiModel(description = "An Object holds the basic data for a Multi Year Estimate")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-24T10:20:21.690Z")

public class MultiYearEstimate {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("tenantId")
	private String tenantId = null;

	@JsonProperty("financialYear")
	private String financialYear = null;

	@JsonProperty("detailedEstimate")
	private DetailedEstimate detailedEstimate = null;

	@JsonProperty("percentage")
	private Integer percentage = null;

	@JsonProperty("auditDetails")
	private AuditDetails auditDetails = null;

	public MultiYearEstimate id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Unique Identifier of the Multi Year Estimate
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "Unique Identifier of the Multi Year Estimate")

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MultiYearEstimate tenantId(String tenantId) {
		this.tenantId = tenantId;
		return this;
	}

	/**
	 * Tenant id of the Multi Year Estimate
	 * 
	 * @return tenantId
	 **/
	@ApiModelProperty(required = true, value = "Tenant id of the Multi Year Estimate")
	@NotNull

	@Size(min = 4, max = 128)
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public MultiYearEstimate financialYear(String financialYear) {
		this.financialYear = financialYear;
		return this;
	}

	/**
	 * Financial Year of the Multi Year Estimate
	 * 
	 * @return financialYear
	 **/
	@ApiModelProperty(required = true, value = "Financial Year of the Multi Year Estimate")
	@NotNull

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public MultiYearEstimate detailedEstimate(DetailedEstimate detailedEstimate) {
		this.detailedEstimate = detailedEstimate;
		return this;
	}

	/**
	 * Get detailedEstimate
	 * 
	 * @return detailedEstimate
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public DetailedEstimate getDetailedEstimate() {
		return detailedEstimate;
	}

	public void setDetailedEstimate(DetailedEstimate detailedEstimate) {
		this.detailedEstimate = detailedEstimate;
	}

	public MultiYearEstimate percentage(Integer percentage) {
		this.percentage = percentage;
		return this;
	}

	/**
	 * Percentage for the Multi Year Estimate
	 * 
	 * @return percentage
	 **/
	@ApiModelProperty(required = true, value = "Percentage for the Multi Year Estimate")
	@NotNull

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public MultiYearEstimate auditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
		return this;
	}

	/**
	 * Get auditDetails
	 * 
	 * @return auditDetails
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public AuditDetails getAuditDetails() {
		return auditDetails;
	}

	public void setAuditDetails(AuditDetails auditDetails) {
		this.auditDetails = auditDetails;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MultiYearEstimate multiYearEstimate = (MultiYearEstimate) o;
		return Objects.equals(this.id, multiYearEstimate.id)
				&& Objects.equals(this.tenantId, multiYearEstimate.tenantId)
				&& Objects.equals(this.financialYear, multiYearEstimate.financialYear)
				&& Objects.equals(this.detailedEstimate, multiYearEstimate.detailedEstimate)
				&& Objects.equals(this.percentage, multiYearEstimate.percentage)
				&& Objects.equals(this.auditDetails, multiYearEstimate.auditDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tenantId, financialYear, detailedEstimate, percentage, auditDetails);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MultiYearEstimate {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
		sb.append("    financialYear: ").append(toIndentedString(financialYear)).append("\n");
		sb.append("    detailedEstimate: ").append(toIndentedString(detailedEstimate)).append("\n");
		sb.append("    percentage: ").append(toIndentedString(percentage)).append("\n");
		sb.append("    auditDetails: ").append(toIndentedString(auditDetails)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
