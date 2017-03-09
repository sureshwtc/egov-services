package org.egov.lams.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.lams.model.RentIncrementType;
import org.egov.lams.model.enums.NatureOfAllotment;
import org.egov.lams.model.enums.PaymentCycle;
import org.egov.lams.model.enums.Status;
import org.egov.lams.service.GetRentIncrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LamsMasterController {

	@Autowired
	GetRentIncrementService getRentIncrementService;

	@RequestMapping(value = "/getstatus")
	public Map<Status, Status> getSatusEnum() {
		Map<Status, Status> status = new HashMap<>();
		for (Status key : Status.values()) {
			status.put(key, Status.valueOf(key.toString()));
		}
		return status;
	}

	@RequestMapping(value = "/getpaymentcycle")
	public Map<PaymentCycle, PaymentCycle> getPayementCycleEnum() {
		Map<PaymentCycle, PaymentCycle> payementCycle = new HashMap<>();
		for (PaymentCycle key : PaymentCycle.values()) {
			payementCycle.put(key, PaymentCycle.valueOf(key.toString()));
		}
		return payementCycle;
	}

	@RequestMapping(value = "/getnatureofallotment")
	public Map<NatureOfAllotment, NatureOfAllotment> getNatureOfAllotmentEnum() {
		Map<NatureOfAllotment, NatureOfAllotment> natureOfAllotment = new HashMap<>();
		for (NatureOfAllotment key : NatureOfAllotment.values()) {
			natureOfAllotment.put(key, NatureOfAllotment.valueOf(key.toString()));
		}
		return natureOfAllotment;
	}

	@RequestMapping(value = "/getrentincrements")
	public List<RentIncrementType> rentIncrementService() {
		return getRentIncrementService.getRentIncrements();
	}
}
