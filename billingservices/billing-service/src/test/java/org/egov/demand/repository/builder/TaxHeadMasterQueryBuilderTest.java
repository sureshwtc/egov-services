package org.egov.demand.repository.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.egov.demand.TestConfiguration;
import org.egov.demand.config.ApplicationProperties;
import org.egov.demand.model.TaxHeadMasterCriteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(TaxHeadMasterQueryBuilder.class)
@Import(TestConfiguration.class)
public class TaxHeadMasterQueryBuilderTest {

	@MockBean
	private ApplicationProperties applicationProperties;
	
	@MockBean
	private TaxHeadMasterCriteria taxHeadMasterCriteria;
	
	@InjectMocks
	private TaxHeadMasterQueryBuilder taxHeadMasterQueryBuilder;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getQueryTest() {
		List<Object> preparedStatementValues = new ArrayList<>();
		TaxHeadMasterCriteria taxHeadMasterCriteriaQuery = TaxHeadMasterCriteria.builder().tenantId("ap.kurnool").build();
		Mockito.doReturn("500").when(applicationProperties).commonsSearchPageSizeDefault();
		String queryWithTenantId = "select * from egbillingservices_taxheadmaster WHERE tenantId = ? ORDER BY name LIMIT ? OFFSET ?";
		assertEquals(queryWithTenantId,
				taxHeadMasterQueryBuilder.getQuery(taxHeadMasterCriteriaQuery, preparedStatementValues));

		List<Object> expectedPreparedStatementValues = new ArrayList<>();
		expectedPreparedStatementValues.add("ap.kurnool");
		expectedPreparedStatementValues.add(Long.valueOf("500"));
		expectedPreparedStatementValues.add(Long.valueOf("0"));
		assertTrue(preparedStatementValues.equals(expectedPreparedStatementValues));
	}
	
	@Test
	public void getInsertQuery() {
		String queryWithTenantId = "INSERT INTO public.egbillingservices_taxheadmaster(id, tenantid, category,"
				+ "service, name, code, glcode, isdebit,isactualdemand, createdby, createdtime,"
				+ "lastmodifiedby, lastmodifiedtime,taxperiod) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?);";
		assertEquals(queryWithTenantId, taxHeadMasterQueryBuilder.getInsertQuery());
	}
}