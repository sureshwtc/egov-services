var dat = {
	"wc.create": {
		"numCols": 12/3,
		"url": "/wcms/masters/sourcetypes/_create",
		"tenantIdRequired": true,
		"idJsonPath": "SourceTypes[0].code",
		"objectName": "SourceTypes",
		"groups": [
			{
				"label": "wc.create.sourceType.title",
				"name": "createSourceType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "SourceTypes[0].name",
							"label": "wc.create.sourceType",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "description",
							"jsonPath": "SourceTypes[0].description",
							"label": "wc.create.description",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,250}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
						{
							"name": "Capacity",
							"jsonPath": "SourceTypes[0].sourceCapacity",
							"label": "wc.create.capacityOfSource",
							"pattern": "^[1-9]\\d{0,5}(\\.\\d{0,3})*(,\\d+)?$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Reservation",
							"jsonPath": "SourceTypes[0].ulbReserved",
							"label": "wc.create.reservationForUlb",
							"pattern": "^[1-9]\\d{0,5}(\\.\\d{0,3})*(,\\d+)?$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Active",
							"jsonPath": "SourceTypes[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"defaultValue":true,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.search": {
		"numCols": 12/3,
		"url": "/wcms/masters/sourcetypes/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "SourceType",
		"groups": [
			{
				"label": "wc.search.sourceType.title",
				"name": "searchSourceType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "name",
							"label": "wc.create.sourceType",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "Active",
							"jsonPath": "active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "wc.create.code"},{label: "wc.create.sourceType"}, {label: "wc.search.result.description"}, {label: "wc.search.result.active"}],
			"values": ["code","name", "description", "active"],
			"resultPath": "SourceTypes",
			"rowClickUrlUpdate": "/update/wc/waterSourceType/{id}",
			"rowClickUrlView": "/view/wc/waterSourceType/{id}"
			}
	},
	"wc.view": {
		"numCols": 12/3,
		"url": "/wcms/masters/sourcetypes/_search?ids={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "SourceType",
		"groups": [
			{
				"label": "wc.view.sourceType.title",
				"name": "viewSourceType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "SourceTypes[0].name",
							"label": "wc.create.sourceType",
							"pattern": "",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "description",
							"jsonPath": "SourceTypes[0].description",
							"label": "wc.create.description",
							"pattern": "",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Capacity",
							"jsonPath": "SourceTypes[0].sourceCapacity",
							"label": "wc.create.capacityOfSource",
							"pattern": "^[1-9]\\d{0,5}(\\.\\d{0,3})*(,\\d+)?$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Reservation",
							"jsonPath": "SourceTypes[0].ulbReserved",
							"label": "wc.create.reservationForUlb",
							"pattern": "^[1-9]\\d{0,5}(\\.\\d{0,3})*(,\\d+)?$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Active",
							"jsonPath": "SourceTypes[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"wc.update": {
		"numCols": 12/3,
		"searchUrl": "/wcms/masters/sourcetypes/_search?ids={id}",
		"url":"/wcms/masters/sourcetypes/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "SourceTypes",
		"groups": [
			{
				"label": "wc.update.sourceType.title",
				"name": "createSourceType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "SourceTypes[0].name",
							"label": "wc.create.sourceType",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 100"
						},
						{
							"name": "description",
							"jsonPath": "SourceTypes[0].description",
							"label": "wc.create.description",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,250}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length is more than 250"
						},
						{
							"name": "Capacity",
							"jsonPath": "SourceTypes[0].sourceCapacity",
							"label": "wc.create.capacityOfSource",
							"pattern": "^[1-9]\\d{0,5}(\\.\\d{0,3})*(,\\d+)?$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Reservation",
							"jsonPath": "SourceTypes[0].ulbReserved",
							"label": "wc.create.reservationForUlb",
							"pattern": "^[1-9]\\d{0,5}(\\.\\d{0,3})*(,\\d+)?$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "Active",
							"jsonPath": "SourceTypes[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	}
}

export default dat;
