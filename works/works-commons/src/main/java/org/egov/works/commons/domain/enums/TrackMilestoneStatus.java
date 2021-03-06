package org.egov.works.commons.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets TrackMilestoneStatus
 */
public enum TrackMilestoneStatus {

	APPROVED("APPROVED"),

	CANCELLED("CANCELLED");

	private String value;

	TrackMilestoneStatus(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static TrackMilestoneStatus fromValue(String text) {
		for (TrackMilestoneStatus b : TrackMilestoneStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
