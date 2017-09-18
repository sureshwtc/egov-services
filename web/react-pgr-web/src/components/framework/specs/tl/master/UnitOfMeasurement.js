var dat = {
	"tl.create": {
		"numCols": 12/2,
		"url": "/tl-masters/uom/v1/_create",
		"tenantIdRequired": true,
		"objectName": "uoms",
		"groups": [
			{
				"label": "tl.create.UOMType.title",
				"name": "createUOMType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "uoms[0].name",
							"label": "tl.create.groups.UOMType.name",
							"pattern": "^.[a-zA-Z. ]{2,49}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Enter Valid Name (Min:3, Max:50)",
							"maxLength": "50"
						},
						{
							"name": "code",
							"jsonPath": "uoms[0].code",
							"label": "tl.create.groups.UOMType.code",
							"pattern": "^.[A-Za-z0-9]{0,19}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Enter Valid Code (Alpha-Numeric, Min:1, Max:20)",
							"maxLength": "20"
						},
            {
							"name": "Active",
							"jsonPath": "uoms[0].active",
							"label": "tl.create.groups.UOMType.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "",
							"defaultValue":true
						}
				]
			}
		]
	},

  "tl.search": {
		"numCols": 12/1,
		"url": "/tl-masters/uom/v1/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "uoms",
		"groups": [
			{
				"label": "tl.search.groups.UOMType.title",
				"name": "createCategoryType",
				"fields": [
						{
							"name": "uom",
							"jsonPath": "ids",
							"label": "tl.search.groups.UOMType.uom",
							"pattern": "",
							"type": "singleValueList",
              "url": "/tl-masters/uom/v1/_search?|$..id|$..name",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "tl.create.groups.UOMType.code"},{label: "tl.create.groups.UOMType.name"}, {label: "tl.create.groups.UOMType.active"}],
			"values": ["code","name", "active"],
			"resultPath": "uoms",
			"rowClickUrlUpdate": "/update/tl/UnitOfMeasurement/{id}",
			"rowClickUrlView": "/view/tl/UnitOfMeasurement/{id}"
			}
	},
	"tl.view": {
		"numCols": 12/2,
		"url": "/tl-masters/uom/v1/_search?ids={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "uoms[0]",
		"groups": [
			{
				"label": "tl.view.groups.UOMType.title",
				"name": "viewCategoryType",
				"fields": [
					{
						"name": "name",
						"jsonPath": "uoms[0].name",
						"label": "tl.view.groups.UOMType.name",
						"pattern": "",
						"type": "text",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "code",
						"jsonPath": "uoms[0].code",
						"label": "tl.view.groups.UOMType.code",
						"pattern": "",
						"type": "text",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
          {
            "name": "Active",
            "jsonPath": "uoms[0].active",
            "label": "tl.view.groups.UOMType.active",
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
  "tl.update": {
		"numCols": 12/2,
		"searchUrl": "/tl-masters/uom/v1/_search?ids={id}",
		"url":"/tl-masters/uom/v1/_update",
		"isResponseArray":true,
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "uoms[0]",
		"groups": [
			{
				"label": "tl.update.groups.UOMType.title",
				"name": "createCategoryType",
				"fields": [
					{
						"name": "name",
						"jsonPath": "uoms[0].name",
						"label": "tl.update.groups.UOMType.name",
						"pattern": "^.[a-zA-Z. ]{2,49}$",
						"type": "text",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "Enter Valid Name (Min:3, Max:50)",
						"maxLength": "50"
					},
					{
						"name": "code",
						"jsonPath": "uoms[0].code",
						"label": "tl.update.groups.UOMType.code",
						"pattern": "^.[A-Za-z0-9]{0,19}$",
						"type": "text",
						"isRequired": true,
						"isDisabled": true,
						"requiredErrMsg": "",
						"patternErrMsg": "Enter Valid Code (Alpha-Numeric, Min:1, Max:20)",
						"maxLength": "20"
					},
          {
            "name": "Active",
            "jsonPath": "uoms[0].active",
            "label": "tl.update.groups.UOMType.active",
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
