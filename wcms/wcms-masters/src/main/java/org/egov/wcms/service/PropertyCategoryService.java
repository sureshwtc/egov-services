/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.wcms.service;

import java.util.List;

import org.egov.wcms.model.ConnectionCategory;
import org.egov.wcms.model.PropertyTypeCategoryType;
import org.egov.wcms.producers.PropertyCategoryProducer;
import org.egov.wcms.repository.PropertyCategoryRepository;
import org.egov.wcms.web.contract.CategoryGetRequest;
import org.egov.wcms.web.contract.PropertyCategoryGetRequest;
import org.egov.wcms.web.contract.PropertyTypeCategoryTypeReq;
import org.egov.wcms.web.contract.PropertyTypeCategoryTypesRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class PropertyCategoryService {
	
    @Autowired
    private PropertyCategoryRepository propertyCategoryRepository;
    
    @Autowired
    PropertyCategoryProducer propertyCategoryProducer;
	
    public static final Logger logger = LoggerFactory.getLogger(PropertyCategoryService.class);

    
	 public PropertyTypeCategoryTypeReq createPropertyCategory(final String topic, final String key, final PropertyTypeCategoryTypeReq propertyCategoryRequest) {
	        
		 	final ObjectMapper mapper = new ObjectMapper();
	        String propertyCategoryValue = null;
	        try {
	            logger.info("createPropertyCategory service::" + propertyCategoryRequest);
	            propertyCategoryValue = mapper.writeValueAsString(propertyCategoryRequest);
	            logger.info("propertyCategoryValue::" + propertyCategoryValue);
	        } catch (final JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        try {
	            propertyCategoryProducer.sendMessage(topic,key,propertyCategoryValue);
	        } catch (final Exception ex) {
	            ex.printStackTrace();
	        }
	        return propertyCategoryRequest;
	    }
	 
	    public PropertyTypeCategoryTypeReq create(final PropertyTypeCategoryTypeReq propertyCategoryRequest) {
	        return propertyCategoryRepository.persistCreatePropertyCategory(propertyCategoryRequest);
	    }
	    
	    public PropertyTypeCategoryTypesRes getPropertyCategories(PropertyCategoryGetRequest propertyCategoryGetRequest) {
	        return propertyCategoryRepository.findForCriteria(propertyCategoryGetRequest);

	    }
	
	
}