package org.egov.egf.voucher.domain.repository;

import org.egov.common.domain.model.Pagination;
import org.egov.common.web.contract.CommonRequest;
import org.egov.egf.voucher.domain.model.BudgetDetail;
import org.egov.egf.voucher.domain.model.BudgetDetailSearch;
import org.egov.egf.voucher.persistence.entity.BudgetDetailEntity;
import org.egov.egf.voucher.persistence.queue.MastersQueueRepository;
import org.egov.egf.voucher.persistence.repository.BudgetDetailJdbcRepository;
import org.egov.egf.voucher.web.contract.BudgetDetailContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BudgetDetailRepository {

	@Autowired
	private BudgetDetailJdbcRepository budgetDetailJdbcRepository;
	@Autowired
	private MastersQueueRepository budgetDetailQueueRepository;

	public BudgetDetail findById(BudgetDetail budgetDetail) {
		return budgetDetailJdbcRepository.findById(new BudgetDetailEntity().toEntity(budgetDetail)).toDomain();

	}
	@Transactional
	public BudgetDetail save(BudgetDetail budgetDetail) {
		return budgetDetailJdbcRepository.create(new BudgetDetailEntity().toEntity(budgetDetail)).toDomain();
	}
	@Transactional
	public BudgetDetail update(BudgetDetail entity) {
		return budgetDetailJdbcRepository.update(new BudgetDetailEntity().toEntity(entity)).toDomain();
	}

	public void add(CommonRequest<BudgetDetailContract> request) {
		budgetDetailQueueRepository.add(request);
	}

	public Pagination<BudgetDetail> search(BudgetDetailSearch domain) {

		return budgetDetailJdbcRepository.search(domain);

	}

}