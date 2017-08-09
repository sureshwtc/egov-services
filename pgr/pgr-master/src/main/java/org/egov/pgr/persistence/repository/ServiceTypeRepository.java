package org.egov.pgr.persistence.repository;

import org.egov.pgr.domain.model.ServiceTypeSearchCriteria;
import org.egov.pgr.persistence.dto.AttributeDefinition;
import org.egov.pgr.persistence.dto.ServiceType;
import org.egov.pgr.persistence.dto.ServiceTypeKeyword;
import org.egov.pgr.persistence.querybuilder.AttributeDefinitionQueryBuilder;
import org.egov.pgr.persistence.querybuilder.ServiceTypeKeywordQueryBuilder;
import org.egov.pgr.persistence.querybuilder.ServiceTypeQueryBuilder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ServiceTypeRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private ServiceTypeQueryBuilder serviceTypeQueryBuilder;

    private ServiceTypeKeywordQueryBuilder serviceTypeKeywordQueryBuilder;

    private AttributeDefinitionQueryBuilder attributeDefinitionQueryBuilder;

    private ValueDefinitionRepository valueDefinitionRepository;

    public ServiceTypeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                 ServiceTypeQueryBuilder serviceTypeQueryBuilder,
                                 ServiceTypeKeywordQueryBuilder serviceTypeKeywordQueryBuilder,
                                 AttributeDefinitionQueryBuilder attributeDefinitionQueryBuilder,
                                 ValueDefinitionRepository valueDefinitionRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.serviceTypeQueryBuilder = serviceTypeQueryBuilder;
        this.serviceTypeKeywordQueryBuilder = serviceTypeKeywordQueryBuilder;
        this.attributeDefinitionQueryBuilder = attributeDefinitionQueryBuilder;
        this.valueDefinitionRepository = valueDefinitionRepository;
    }

    public void save(ServiceType serviceType) {
        namedParameterJdbcTemplate.update(serviceTypeQueryBuilder.getInsertQuery(),
                getNamedQuery(serviceType));
    }

    public List<org.egov.pgr.domain.model.ServiceType> search(ServiceTypeSearchCriteria serviceTypeSearchCriteria) {

        List<ServiceType> serviceTypeList = getServiceList(serviceTypeQueryBuilder.buildSearchQuery(serviceTypeSearchCriteria),
                getSearchNamedQuery(serviceTypeSearchCriteria), new BeanPropertyRowMapper<>(ServiceType.class));
        filterKeywords(serviceTypeSearchCriteria, serviceTypeList);
        fetchAttributes(serviceTypeList);
        fetchValueDefinitions(serviceTypeList);

        return getServiceTypes(serviceTypeSearchCriteria, serviceTypeList);
    }

    private List<org.egov.pgr.domain.model.ServiceType> getServiceTypes(ServiceTypeSearchCriteria serviceTypeSearchCriteria, List<ServiceType> serviceTypeList) {
        return serviceTypeList.stream()
                .filter(serviceType -> serviceType.isKeywordPresent(serviceTypeSearchCriteria.getKeywords()))
                .map(ServiceType::toDomain)
                .collect(Collectors.toList());
    }

    private void fetchValueDefinitions(List<ServiceType> serviceTypeList){
        serviceTypeList.forEach(st -> valueDefinitionRepository.setValueDefinition(st));
    }

    private void fetchAttributes(List<ServiceType> serviceTypeList) {
        serviceTypeList.forEach(st -> st.setAttributeDefinitions(fetchAttributes(st)));
    }

    private void filterKeywords(ServiceTypeSearchCriteria serviceTypeSearchCriteria, List<ServiceType> serviceTypeList) {
        serviceTypeList.
                forEach(serviceType -> serviceType.setKeywords(
                        fetchKeywords(serviceType, serviceTypeSearchCriteria.getKeywords())));
    }

    private List<ServiceType> getServiceList(String sql, HashMap<String, String> searchNamedQuery, BeanPropertyRowMapper<ServiceType> rowMapper) {
        return namedParameterJdbcTemplate.query(sql,
                searchNamedQuery, rowMapper);
    }


    private List<String> fetchKeywords(ServiceType serviceType, List<String> keywords) {
        List<ServiceTypeKeyword> keywordList = namedParameterJdbcTemplate.query(serviceTypeKeywordQueryBuilder.keywordsSearchQuery(null),
                getKeywordsSearchMap(serviceType, keywords), new BeanPropertyRowMapper<>(ServiceTypeKeyword.class));

        return keywordList.stream()
                .map(ServiceTypeKeyword::getKeyword)
                .collect(Collectors.toList());
    }

    private List<AttributeDefinition> fetchAttributes(ServiceType serviceType) {
        return namedParameterJdbcTemplate.query(attributeDefinitionQueryBuilder.findByServiceCodeAndTenantId(),
                getAttributesMap(serviceType), new BeanPropertyRowMapper<>(AttributeDefinition.class));
    }

    private HashMap<String, java.io.Serializable> getNamedQuery(ServiceType serviceType) {
        HashMap<String, java.io.Serializable> parametersMap = new HashMap<String, java.io.Serializable>();
        parametersMap.put("code", serviceType.getCode());
        parametersMap.put("name", serviceType.getName());
        parametersMap.put("category", serviceType.getCategory());
        parametersMap.put("description", serviceType.getDescription());
        parametersMap.put("isactive", serviceType.getIsactive());
        parametersMap.put("isday", serviceType.getIsday());
        parametersMap.put("metadata", serviceType.getMetadata());
        parametersMap.put("slahours", serviceType.getSlaHours());
        parametersMap.put("type", serviceType.getType());
        parametersMap.put("tenantid", serviceType.getTenantId());
        parametersMap.put("hasfinancialimpact", serviceType.isHasfinancialimpact());
        parametersMap.put("createdby", serviceType.getCreatedBy());
        parametersMap.put("createddate", serviceType.getCreatedDate());
        parametersMap.put("lastmodifiedby", serviceType.getLastModifiedBy());
        parametersMap.put("lastmodifieddate", serviceType.getLastModifiedDate());
        parametersMap.put("department", serviceType.getDepartment());

        return parametersMap;
    }

    private HashMap<String, String> getSearchNamedQuery(ServiceTypeSearchCriteria serviceTypeSearchCriteria) {
        HashMap<String, String> parametersMap = new HashMap<String, String>();
        parametersMap.put("code", serviceTypeSearchCriteria.getServiceCode());
        parametersMap.put("tenantid", serviceTypeSearchCriteria.getTenantId());

        return parametersMap;
    }

    private HashMap<String, Object> getKeywordsSearchMap(ServiceType serviceType, List<String> keywords) {
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("code", serviceType.getCode());
        parametersMap.put("tenantid", serviceType.getTenantId());
        parametersMap.put("keywords", keywords);

        return parametersMap;
    }

    private HashMap<String, String> getAttributesMap(ServiceType serviceType) {
        HashMap<String, String> parametersMap = new HashMap<>();

        parametersMap.put("servicecode", serviceType.getCode());
        parametersMap.put("tenantid", serviceType.getTenantId());

        return parametersMap;
    }
}