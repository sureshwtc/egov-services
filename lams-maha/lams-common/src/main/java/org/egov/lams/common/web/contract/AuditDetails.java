package org.egov.lams.common.web.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

/**
 * Collection of audit related fields used by most models Author : Ramki
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditDetails {
	@JsonProperty("createdBy")
	private String createdBy = null;

	@JsonProperty("lastModifiedBy")
	private String lastModifiedBy = null;

	@JsonProperty("createdTime")
	private Long createdTime = null;

	@JsonProperty("lastModifiedTime")
	private Long lastModifiedTime = null;

}
