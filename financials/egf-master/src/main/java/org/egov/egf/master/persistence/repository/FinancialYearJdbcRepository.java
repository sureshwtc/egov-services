package org.egov.egf.master.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.egov.common.domain.model.Pagination;
import org.egov.common.persistence.repository.JdbcRepository;
import org.egov.egf.master.domain.model.FinancialYear;
import org.egov.egf.master.domain.model.FinancialYearSearch;
import org.egov.egf.master.persistence.entity.FinancialYearEntity;
import org.egov.egf.master.persistence.entity.FinancialYearSearchEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

@Service
public class FinancialYearJdbcRepository extends JdbcRepository {
	private static final Logger LOG = LoggerFactory.getLogger(FinancialYearJdbcRepository.class);

	static {
		LOG.debug("init financialYear");
		init(FinancialYearEntity.class);
		LOG.debug("end init financialYear");
	}

	public FinancialYearEntity create(FinancialYearEntity entity) {

		entity.setId(UUID.randomUUID().toString().replace("-", ""));
		super.create(entity);
		return entity;
	}

	public FinancialYearEntity update(FinancialYearEntity entity) {
		super.update(entity);
		return entity;

	}

	public Pagination<FinancialYear> search(FinancialYearSearch domain) {
		FinancialYearSearchEntity financialYearSearchEntity = new FinancialYearSearchEntity();
		financialYearSearchEntity.toEntity(domain);

		String searchQuery = "select :selectfields from :tablename :condition  :orderby   ";

		Map<String, Object> paramValues = new HashMap<>();
		StringBuffer params = new StringBuffer();
		String orderBy = "";

		searchQuery = searchQuery.replace(":tablename", FinancialYearEntity.TABLE_NAME);

		searchQuery = searchQuery.replace(":selectfields", " * ");

		// implement jdbc specfic search
if( financialYearSearchEntity.getId()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "id =: id");
paramValues.put("id" ,financialYearSearchEntity.getId());} 
if( financialYearSearchEntity.getFinYearRange()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "finYearRange =: finYearRange");
paramValues.put("finYearRange" ,financialYearSearchEntity.getFinYearRange());} 
if( financialYearSearchEntity.getStartingDate()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "startingDate =: startingDate");
paramValues.put("startingDate" ,financialYearSearchEntity.getStartingDate());} 
if( financialYearSearchEntity.getEndingDate()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "endingDate =: endingDate");
paramValues.put("endingDate" ,financialYearSearchEntity.getEndingDate());} 
if( financialYearSearchEntity.getActive()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "active =: active");
paramValues.put("active" ,financialYearSearchEntity.getActive());} 
if( financialYearSearchEntity.getIsActiveForPosting()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "isActiveForPosting =: isActiveForPosting");
paramValues.put("isActiveForPosting" ,financialYearSearchEntity.getIsActiveForPosting());} 
if( financialYearSearchEntity.getIsClosed()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "isClosed =: isClosed");
paramValues.put("isClosed" ,financialYearSearchEntity.getIsClosed());} 
if( financialYearSearchEntity.getTransferClosingBalance()!=null) {
if (params.length() > 0) 
params.append(" and "); 
params.append( "transferClosingBalance =: transferClosingBalance");
paramValues.put("transferClosingBalance" ,financialYearSearchEntity.getTransferClosingBalance());} 

		 

		Pagination<FinancialYear> page = new Pagination<>();
		page.setOffSet(financialYearSearchEntity.getOffset());
		page.setPageSize(financialYearSearchEntity.getPageSize());

		if (params.length() > 0) {

			searchQuery = searchQuery.replace(":condition", " where " + params.toString());

		} else {
			searchQuery = searchQuery.replace(":condition", "");
		}

		searchQuery = searchQuery.replace(":orderby", "order by id ");

		page = getPagination(searchQuery, page,paramValues);
		searchQuery = searchQuery + " :pagination";

		searchQuery = searchQuery.replace(":pagination", "limit " + financialYearSearchEntity.getPageSize() + " offset "
				+ financialYearSearchEntity.getOffset() * financialYearSearchEntity.getPageSize());

		BeanPropertyRowMapper row = new BeanPropertyRowMapper(FinancialYearEntity.class);

		List<FinancialYearEntity> financialYearEntities = namedParameterJdbcTemplate.query(searchQuery.toString(), paramValues, row);

		page.setTotalResults(financialYearEntities.size());

		List<FinancialYear> financialyears = new ArrayList<FinancialYear>();
		for (FinancialYearEntity financialYearEntity : financialYearEntities) {

			financialyears.add(financialYearEntity.toDomain());
		}
		page.setPagedData(financialyears);

		return page;
	}

	public FinancialYearEntity findById(FinancialYearEntity entity) {
		List<String> list = allUniqueFields.get(entity.getClass().getSimpleName());

		final List<Object> preparedStatementValues = new ArrayList<>();

		for (String s : list) {
			preparedStatementValues.add(getValue(getField(entity, s), entity));
		}

		List<FinancialYearEntity> financialyears = jdbcTemplate.query(getByIdQuery.get(entity.getClass().getSimpleName()),
				preparedStatementValues.toArray(), new BeanPropertyRowMapper<FinancialYearEntity>());
		if (financialyears.isEmpty()) {
			return null;
		} else {
			return financialyears.get(0);
		}

	}

}