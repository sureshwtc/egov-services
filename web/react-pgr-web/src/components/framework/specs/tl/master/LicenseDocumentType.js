var dat = {
	"tl.create": {
		"numCols": 12/2,
		"url": "/tl-masters/documenttype/v2/_create",
		"useTimestamp": true,
		"tenantIdRequired": true,
		"objectName": "documentTypes",
		"groups": [
			{
				"label": "tl.create.groups.licensedocumenttype.title",
				"name": "createLicenseDocumentType",
				"fields": [
					{
						"name": "licenseapptype",
						"jsonPath": "documentTypes[0].applicationType",
						"label": "tl.create.groups.licensedocumenttype.licenseapptype",
						"pattern": "",
						"type": "singleValueList",
						"url": "",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"defaultValue": [
          {
            "key": "NEW",
            "value": "NEW"
          },
          {
            "key": "RENEW",
            "value": "RENEW"
          }
            ]
					},

					{
            "name": "Category",
            "jsonPath": "documentTypes[0].categoryId",
            "label": "tl.create.licensedocumenttype.groups.TradeDetails.TradeCategory",
            "pattern": "",
            "type": "singleValueList",
            "url": "/tl-masters/category/v1/_search?tenantId=default&type=category|$..id|$..name",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
            "depedants": [{
              "jsonPath": "documentTypes[0].subCategoryId",
              "type": "dropDown",
              "pattern": "/tl-masters/category/v1/_search?tenantId=default&type=subcategory&categoryId={documentTypes[0].categoryId}|$.categories.*.id|$.categories.*.name"
            }]
          },
					{
            "name": "SubCategory",
            "jsonPath": "documentTypes[0].subCategoryId",
            "label": "tl.create.licensedocumenttype.groups.TradeDetails.TradeSubCategory",
            "pattern": "",
            "type": "singleValueList",
            "url": "",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
					}
				]
			},

			{
				//"label": "tl.create.groups.licensedocumenttype.title",
				"name": "licensedocument",
				"multiple":true,
				"fields": [
					{
						"name": "name",
						"jsonPath": "documentTypes[0].name",
						"label": "tl.create.groups.licensedocumenttype.name",
						"pattern": "",
						"type": "text",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
            "name": "mandatory",
            "jsonPath": "documentTypes[0].mandatory",
            "label": "tl.create.licenses.groups.TradeDetails.mandatory",
            "pattern": "",
            "type": "checkbox",
            "url": "",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"defaultValue": false
          },
					{
            "name": "enabled",
            "jsonPath": "documentTypes[0].enabled",
            "label": "tl.create.licenses.groups.TradeDetails.enabled",
            "pattern": "",
            "type": "checkbox",
            "url": "",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"defaultValue": false
					}
					]
				}

		]
	},

  "tl.search": {
		"numCols": 12/2,
		"url": "/tl-masters/documenttype/v2/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "documentTypes",
		"groups": [
			{
				"label": "tl.search.groups.licensedocumenttype.title",
				"name": "createCategoryType",
				"fields": [
            {
              "name": "name",
              "jsonPath": "name",
              "label": "tl.search.groups.licensedocumenttype.name",
              "pattern": "",
              "type": "text",
              "isRequired": false,
              "isDisabled": false,
              "requiredErrMsg": "",
              "patternErrMsg": ""
            },
						{
							"name": "licenseapptype",
							"jsonPath": "documentTypes[0].applicationType",
							"label": "tl.search.groups.licensedocumenttype.licenseapptype",
							"pattern": "",
							"type": "singleValueList",
							"url": "",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "",
							"defaultValue": [
	          {
	            "key": "NEW",
	            "value": "NEW"
	          },
	          {
	            "key": "RENEW",
	            "value": "RENEW"
	          }
	            ]
						}
				]
			}
		],
		"result": {
			"header": [{label: "tl.create.groups.licensedocumenttype.licenseapptype"},{label: "tl.create.licensedocumenttype.groups.TradeDetails.TradeCategory"}, {label: "tl.create.licensedocumenttype.groups.TradeDetails.TradeSubCategory"}, {label: "tl.create.groups.licensedocumenttype.name"}, {label: "tl.create.licenses.groups.TradeDetails.mandatory"}, {label: "tl.create.licenses.groups.TradeDetails.enabled"}],
			"values": ["applicationType","categoryId", "subCategoryId", "name", "mandatory", "enabled"],
			"resultPath": "documentTypes",
			"rowClickUrlUpdate": "/update/tl/LicenseDocumentType/{id}",
			"rowClickUrlView": "/view/tl/LicenseDocumentType/{id}"
			}
	},

  "tl.view": {
		"numCols": 12/2,
		"url": "/tl-masters/documenttype/v2/_search?ids={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "documentTypes[0]",
		"groups": [
			{
				"label": "tl.view.groups.licensedocumenttype.title",
				"name": "viewCategoryType",
				"fields": [
          {
            "name": "name",
            "jsonPath": "documentTypes[0].name",
            "label": "tl.view.groups.licensedocumenttype.name",
            "pattern": "",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
          },
					{
            "name": "mandatory",
            "jsonPath": "documentTypes[0].mandatory",
            "label": "tl.view.groups.licensedocumenttype.mandatory",
            "pattern": "",
            "type": "checkbox",
            "url": "",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "", 
            "patternErrMsg": "",
					//	"defaultValue": false
          },
					{
            "name": "enabled",
            "jsonPath": "documentTypes[0].enabled",
            "label": "tl.view.groups.licensedocumenttype.enabled",
            "pattern": "",
            "type": "checkbox",
            "url": "",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						//"defaultValue": false
					}
				]
			}
		]
	},

  "tl.update": {
		"numCols": 12/2,
		"searchUrl": "/tl-masters/documenttype/v2/_search?ids={id}",
		"url":"/tl-masters/documenttype/v2/_update",
		"isResponseArray":true,
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "documentTypes[0]",
		"groups": [
			{
				"label": "tl.update.groups.licensedocumenttype.title",
				"name": "licensedocument",
				"multiple":true,
				"fields": [
					{
						"name": "name",
						"jsonPath": "documentTypes[0].name",
						"label": "tl.create.groups.licensedocumenttype.name",
						"pattern": "",
						"type": "text",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
            "name": "mandatory",
            "jsonPath": "documentTypes[0].mandatory",
            "label": "tl.create.licenses.groups.TradeDetails.mandatory",
            "pattern": "",
            "type": "checkbox",
            "url": "",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"defaultValue": false
          },
					{
            "name": "enabled",
            "jsonPath": "documentTypes[0].enabled",
            "label": "tl.create.licenses.groups.TradeDetails.enabled",
            "pattern": "",
            "type": "checkbox",
            "url": "",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"defaultValue": false
					}
					]
				}
		]
	}
}

export default dat;