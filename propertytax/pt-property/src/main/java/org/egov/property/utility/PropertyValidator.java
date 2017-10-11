package org.egov.property.utility;

import org.egov.models.*;
import org.egov.property.config.PropertiesManager;
import org.egov.property.exception.*;
import org.egov.property.repository.BoundaryRepository;
import org.egov.property.repository.CalculatorRepository;
import org.egov.property.repository.PropertyMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This Service to validate the property attributes
 * 
 * @author Pavan Kumar Kamma
 *
 */

@Service
public class PropertyValidator {

	@Autowired
	private PropertiesManager propertiesManager;

	@Autowired
	ResponseInfoFactory responseInfoFactory;

	@Autowired
	BoundaryRepository boundaryRepository;

	@Autowired
	CalculatorRepository calculatorRepository;

	@Autowired
	private PropertyMasterRepository propertyMasterRepository;

	/**
	 * Description : This validates the property boundary
	 * 
	 * @param property
	 * @throws InvalidPropertyBoundaryException
	 */
	public void validatePropertyBoundary(Property property, RequestInfo requestInfo)
			throws InvalidPropertyBoundaryException {

		List<String> fields = getAllBoundaries();
		// TODO location service gives provision to search by multiple ids, no
		// need to do multiple calls for each boundary id
		for (String field : fields) {
			if (!field.equalsIgnoreCase(propertiesManager.getGuidanceValueBoundary())) {
				validateBoundaryFields(property, field, requestInfo);
			} else {
				//TODO Below two IF conditions can also be merged
				if (property.getBoundary() != null) {
					if (!property.getChannel().toString().equalsIgnoreCase(propertiesManager.getChannelType())) {
						String guidanceBoundary = property.getBoundary().getGuidanceValueBoundary();
						if (!StringUtils.isEmpty(guidanceBoundary)) {
							//TODO tenantId is hardcoded to null why? multi-tenant validation will not happen
							Boolean isExists = propertyMasterRepository.checkWhetherRecordExits(null, guidanceBoundary,
									ConstantUtility.GUIDANCEVALUEBOUNDARY_TABLE_NAME, null);

							if (!isExists) {
								throw new InvalidCodeException(propertiesManager.getInvalidGuidanceValueBoundaryId(),
										requestInfo);
							}

						} else {
							throw new InvalidCodeException(propertiesManager.getInvalidGuidanceValueBoundary(),
									requestInfo);
						}
					} 
				}

			}

		}
	}

	/**
	 * Description : This validates the each boundary field of the property
	 * 
	 * @param property
	 * @param field
	 * @return true
	 * @throws InvalidPropertyBoundaryException
	 */
	public Boolean validateBoundaryFields(Property property, String field, RequestInfo requestInfo)
			throws InvalidPropertyBoundaryException {

		//TODO when 3 boundary values are passed only admin boundary get's validated with this logic
		PropertyLocation propertyLocation = property.getBoundary();
		String code = null;
		if (field.equalsIgnoreCase(propertiesManager.getRevenueBoundary())) {
			if (propertyLocation.getRevenueBoundary() != null) {
				code = propertyLocation.getRevenueBoundary().getCode();
			}
		} else if (field.equalsIgnoreCase(propertiesManager.getLocationBoundary())) {
			if (propertyLocation.getLocationBoundary() != null) {
				code = propertyLocation.getRevenueBoundary().getCode();
			}
		} else if (field.equalsIgnoreCase(propertiesManager.getAdminBoundary())) {
			if (propertyLocation.getAdminBoundary() != null) {
				code = propertyLocation.getRevenueBoundary().getCode();
			}
		}
		if (!StringUtils.isEmpty(code))
			return boundaryRepository.isBoundaryExists(property, requestInfo, code);
		else
			return true;

	}

	/**
	 * Description : This validates acknowledgement number and workflow details
	 * 
	 * @param property
	 * @param requestInfo
	 * @throws AttributeNotFoundException
	 * @author Yosadhara
	 */
	public void validateWorkflowDeatails(Property property, RequestInfo requestInfo) throws AttributeNotFoundException {

		String acknowledgementNo = property.getPropertyDetail().getApplicationNo();

		if (acknowledgementNo == null) {
			throw new AttributeNotFoundException(propertiesManager.getAcknowledgementNotfound(), requestInfo);

		} else {
			WorkFlowDetails workflowDetails = property.getPropertyDetail().getWorkFlowDetails();

			if (workflowDetails.getAction() == null) {
				throw new InvalidUpdatePropertyException(propertiesManager.getWorkflowActionNotfound(), requestInfo);

			} else if (workflowDetails.getAssignee() == null) {
				throw new InvalidUpdatePropertyException(propertiesManager.getWorkflowAssigneeNotfound(), requestInfo);

			} else if (workflowDetails.getDepartment() == null) {
				throw new InvalidUpdatePropertyException(propertiesManager.getWorkflowDepartmentNotfound(),
						requestInfo);

			} else if (workflowDetails.getDesignation() == null) {
				throw new InvalidUpdatePropertyException(propertiesManager.getWorkflowDesignationNotfound(),
						requestInfo);

			} else if (workflowDetails.getStatus() == null) {
				throw new InvalidUpdatePropertyException(propertiesManager.getWorkflowStatusNotfound(), requestInfo);

			}
		}
	}

	/**
	 * Description : This is to get the searchType fields of the target class
	 * 
	 * @param target
	 * @param searchType
	 * @return result
	 */
	public List<String> getFieldsOfType(Class<?> target, Class<?> searchType) {
		Field[] fields = target.getDeclaredFields();
		List<String> result = new ArrayList<String>();
		for (Field field : fields) {
			if (field.getType().equals(searchType)) {
				result.add(field.getName());
			}
		}
		return result;
	}

	/**
	 * Description : This is to get the BoundaryType fields of the
	 * PropertyLocation class
	 * 
	 * @author Pavan Kumar Kamma
	 * @return List<String>
	 */
	public List<String> getAllBoundaries() {
		return getFieldsOfType(PropertyLocation.class, Boundary.class);
	}

	/**
	 * Description : Checks whether Property Type already exists or not
	 * 
	 * @param property
	 * @param requestInfo
	 */
	//TODO This method can be further reduced to small logical methods for validation
	public void validatePropertyMasterData(Property property, RequestInfo requestInfo) {

		//TODO Below code can be refactored Long count = property.getOwners().stream().filter(User::getIsPrimaryOwner).collect(Collectors.counting());
		for (User owner : property.getOwners()) {
			Boolean isValid = false;
            if (owner.getIsPrimaryOwner() != null) {
            	if(owner.getIsPrimaryOwner()){
            		isValid=true;
                	break;
            	}
            }

            if(!isValid){
            	throw new InvalidCodeException(propertiesManager.getInvalidOwners(), requestInfo);
            }
		}
		PropertyDetail propertyDetail = property.getPropertyDetail();
		if (propertyDetail.getPropertyType() != null) {
			if (propertyDetail.getPropertyType().equalsIgnoreCase(propertiesManager.getVacantLand())) {
				calculatorRepository.getTaxPeriods(property.getTenantId(), property.getOccupancyDate(), requestInfo);
				calculatorRepository.getTaxRates(property.getTenantId(), property.getOccupancyDate(), requestInfo);
			}
		}

		if (propertyDetail.getSubUsage() != null) {
			Boolean subUsageExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					propertyDetail.getSubUsage(), ConstantUtility.USAGE_TYPE_TABLE_NAME, null);
			if (!subUsageExists) {
				throw new InvalidCodeException(propertiesManager.getInvalidPropertySubUsageCode(), requestInfo);
			}
		}

		if (propertyDetail.getPropertyType() != null) {
			Boolean isExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					property.getPropertyDetail().getPropertyType(), ConstantUtility.PROPERTY_TYPE_TABLE_NAME, null);

			if (!isExists) {
				throw new InvalidCodeException(propertiesManager.getInvalidPropertyTypeCode(), requestInfo);
			}
		}
		if (propertyDetail.getCategory() != null) {
			Boolean isExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					propertyDetail.getCategory(), ConstantUtility.PROPERTY_TYPE_TABLE_NAME, null);

			if (!isExists) {
				throw new InvalidCodeException(propertiesManager.getInvalidCategory(), requestInfo);
			}
		}

		if (property.getPropertyDetail().getFloors() != null) {
			for (Floor floor : property.getPropertyDetail().getFloors()) {

				String boundary = null;
				if (!property.getChannel().toString().equalsIgnoreCase(propertiesManager.getChannelType())) {
					if (property.getBoundary() != null && property.getBoundary().getGuidanceValueBoundary() != null) {
						boundary = property.getBoundary().getGuidanceValueBoundary().toString().trim();
					} else {
						throw new InvalidCodeException(propertiesManager.getInvalidGuidanceValueBoundaryId(),
								requestInfo);
					}
				}
				String propertyType = null;

				if (property.getPropertyDetail() != null) {
					propertyType = property.getPropertyDetail().getPropertyType();
				} else {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyTypeCode(), requestInfo);
				}
				String validOccupancyDate = null;
				for (Unit unit : floor.getUnits()) {
					if (unit != null) {
						validOccupancyDate = unit.getOccupancyDate();
						validateUnitData(property.getTenantId(), unit, requestInfo, boundary, validOccupancyDate,
								propertyType, property);
					}

					if (unit.getUnits() != null) {
						for (Unit room : unit.getUnits()) {
							validOccupancyDate = room.getOccupancyDate();
							validateUnitData(property.getTenantId(), room, requestInfo, boundary, validOccupancyDate,
									propertyType, property);
						}
					}
				}
			}
		}

		if (property.getPropertyDetail().getDocuments() != null) {
			for (Document document : property.getPropertyDetail().getDocuments()) {

				if (document.getDocumentType() != null) {
					Boolean isDocumentTypeRecordExists = propertyMasterRepository.checkWhetherRecordExits(
							property.getTenantId(), document.getDocumentType(),
							ConstantUtility.DOCUMENT_TYPE_TABLE_NAME, null);

					if (!isDocumentTypeRecordExists) {
						throw new InvalidCodeException(propertiesManager.getInvalidDocumentTypeCode(), requestInfo);
					}
				}
			}
		}

		if (property.getPropertyDetail().getDepartment() != null) {
			Boolean isDepartmentRecordExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					property.getPropertyDetail().getDepartment(), ConstantUtility.DEPARTMENT_TABLE_NAME, null);

			if (!isDepartmentRecordExists)
				throw new InvalidCodeException(propertiesManager.getInvalidDepartmentCode(), requestInfo);
		}

		if (property.getPropertyDetail().getFloorType() != null) {
			Boolean isFloorTypeExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					property.getPropertyDetail().getFloorType(), ConstantUtility.FLOOR_TYPE_TABLE_NAME, null);

			if (!isFloorTypeExists)
				throw new InvalidCodeException(propertiesManager.getInvalidFloorTypeCode(), requestInfo);
		}

		if (property.getPropertyDetail().getRoofType() != null) {
			Boolean isRoofTypeExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					property.getPropertyDetail().getRoofType(), ConstantUtility.ROOF_TYPE_TABLE_NAME, null);

			if (!isRoofTypeExists)
				throw new InvalidCodeException(propertiesManager.getInvalidRoofTypeCode(), requestInfo);
		}

		if (property.getPropertyDetail().getWoodType() != null) {
			Boolean isWoodTypeExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					property.getPropertyDetail().getWoodType(), ConstantUtility.WOOD_TYPE_TABLE_NAME, null);

			if (!isWoodTypeExists)
				throw new InvalidCodeException(propertiesManager.getInvalidRoofTypeCode(), requestInfo);
		}

		if (property.getPropertyDetail().getWallType() != null) {
			Boolean isWallTypeExists = propertyMasterRepository.checkWhetherRecordExits(property.getTenantId(),
					property.getPropertyDetail().getWallType(), ConstantUtility.WALL_TYPE_TABLE_NAME, null);

			if (!isWallTypeExists)
				throw new InvalidCodeException(propertiesManager.getInvalidWallTypeCode(), requestInfo);
		}

		if (property.getPropertyDetail().getPropertyType().equalsIgnoreCase(propertiesManager.getVacantLand())) {
			if (property.getVacantLand() == null)
				throw new InvalidVacantLandException(propertiesManager.getInvalidPropertyVacantland(), requestInfo);

		} else if (property.getPropertyDetail().getFloors() == null
				|| property.getPropertyDetail().getFloors().size() <= 0) {

			throw new InvalidFloorException(propertiesManager.getInvalidPropertyFloor(), requestInfo);
		}
	}

	public void validateUpicNo(Property property, RequestInfo requestInfo) {
		String oldUpicNo = property.getOldUpicNumber();
		int count = propertyMasterRepository.checkOldUpicNumber(oldUpicNo);
		if (count > 0)
			throw new InvalidCodeException(propertiesManager.getInvalidOldUpicCode(), requestInfo);
	}

	/**
	 * Description : Method for validating the unit details
	 * 
	 * @param tenantId
	 * @param unit
	 * @param requestInfo
	 */
	//TODO This method can be further reduced to small logical methods for validation
	private void validateUnitData(String tenantId, Unit unit, RequestInfo requestInfo, String boundary,
			String validOccupancyDate, String propertyType, Property property) {
		if (!property.getChannel().toString().equalsIgnoreCase(propertiesManager.getChannelType())) {
			if (unit != null) {
				calculatorRepository.getTaxRates(tenantId, unit.getOccupancyDate(), requestInfo);
				calculatorRepository.getTaxPeriods(tenantId, unit.getOccupancyDate(), requestInfo);
				
				
			}
			if (unit.getUsage() != null) {
				Boolean usageExists = propertyMasterRepository.checkWhetherRecordExits(tenantId, unit.getUsage(),
						ConstantUtility.USAGE_TYPE_TABLE_NAME, null);
				if (!usageExists) {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyUsageCode(), requestInfo);
				}
			}

			if (unit.getSubUsage() != null) {
				Boolean subUsageExists = propertyMasterRepository.checkWhetherRecordExits(tenantId, unit.getSubUsage(),
						ConstantUtility.USAGE_TYPE_TABLE_NAME, null);
				if (!subUsageExists) {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertySubUsageCode(), requestInfo);
				}
			}

			if (unit.getOccupancyType() != null) {
				Boolean occupancyTypeExists = propertyMasterRepository.checkWhetherRecordExits(tenantId,
						unit.getOccupancyType(), ConstantUtility.OCCUPANCY_TABLE_NAME, null);

				if (!occupancyTypeExists) {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyOccupancyCode(), requestInfo);
				}
			}

			if (unit.getAge() != null) {
				Boolean ageExists = propertyMasterRepository.checkWhetherRecordExits(tenantId, unit.getAge(),
						ConstantUtility.DEPRECIATION_TABLE_NAME, null);

				if (!ageExists) {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyAgeCode(), requestInfo);
				}
			} else {
				Integer diffValue = 0;
				List<Object> preparedStatementValues = new ArrayList<Object>();
				Integer occupancyYear = TimeStampUtil.getYear(unit.getOccupancyDate());
				Calendar cal = Calendar.getInstance();
				Integer currentYear = cal.getInstance().get(Calendar.YEAR);
				diffValue = currentYear - occupancyYear;
				String code = propertyMasterRepository.getAge(tenantId, diffValue,
						ConstantUtility.DEPRECIATION_TABLE_NAME, preparedStatementValues);
				//TODO below two if conditions can be put in one two if conditions not required
				//TODO Many places where ever two if conditions are there and can be merged into one
				if (code != null) {
					if (!code.isEmpty()) {
						unit.setAge(code);
					}
				} else {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyAgeCode(), requestInfo);
				}

			}

			if (unit.getStructure() != null) {
				Boolean structureExists = propertyMasterRepository.checkWhetherRecordExits(tenantId,
						unit.getStructure(), ConstantUtility.STRUCTURE_CLASS_TABLE_NAME, null);

				if (!structureExists) {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyStructureCode(), requestInfo);
				}
			}

			RequestInfoWrapper requestInfoWrapper = new RequestInfoWrapper();
			requestInfoWrapper.setRequestInfo(requestInfo);

			if (unit.getUsage() != null) {
				calculatorRepository.isGuidanceExists(tenantId, unit, requestInfoWrapper, boundary);
			} else {
				throw new InvalidCodeException(propertiesManager.getInvalidPropertyUsageCode(), requestInfo);
			}
			if (validOccupancyDate != null)

			{
				if (unit.getOccupancyType() != null) {
					calculatorRepository.isFactorExists(tenantId, unit.getOccupancyType(), requestInfoWrapper,
							validOccupancyDate, propertiesManager.getPropertyFactorOccupancy());
				} else {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyOccupancyCode(), requestInfo);
				}

				if (unit.getUsage() != null) {
					calculatorRepository.isFactorExists(tenantId, unit.getUsage(), requestInfoWrapper,
							validOccupancyDate, propertiesManager.getPropertyFactorUsage());
				} else {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyUsageCode(), requestInfo);
				}

				if (unit.getStructure() != null) {
					calculatorRepository.isFactorExists(tenantId, unit.getStructure(), requestInfoWrapper,
							validOccupancyDate, propertiesManager.getPropertyFactorStructure());
				} else {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyStructureCode(), requestInfo);
				}

				if (unit.getAge() != null) {
					calculatorRepository.isFactorExists(tenantId, unit.getAge(), requestInfoWrapper, validOccupancyDate,
							propertiesManager.getPropertyFactorAge());
				} else {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyAgeCode(), requestInfo);
				}

				if (propertyType != null) {
					calculatorRepository.isFactorExists(tenantId, propertyType, requestInfoWrapper, validOccupancyDate,
							propertiesManager.getPropertyFactorPropertytype());
				} else {
					throw new InvalidCodeException(propertiesManager.getInvalidPropertyTypeCode(), requestInfo);
				}
			} else {
				throw new InvalidCodeException(propertiesManager.getInvalidInputOccupancydate(), requestInfo);
			}
		}

	}
}
