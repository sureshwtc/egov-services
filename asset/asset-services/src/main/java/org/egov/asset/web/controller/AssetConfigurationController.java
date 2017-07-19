package org.egov.asset.web.controller;

import javax.validation.Valid;

import org.egov.asset.contract.AssetConfigurationResponse;
import org.egov.asset.contract.RequestInfoWrapper;
import org.egov.asset.exception.ErrorResponse;
import org.egov.asset.model.AssetConfigurationCriteria;
import org.egov.asset.service.AssetCommonService;
import org.egov.asset.service.AssetConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assetconfigurations")
public class AssetConfigurationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetConfigurationController.class);

    @Autowired
    private AssetConfigurationService assetConfigurationService;

    @Autowired
    private AssetCommonService assetCommonService;

    @PostMapping("/_search")
    @ResponseBody
    public ResponseEntity<?> assetConfigurationSearch(@RequestBody @Valid final RequestInfoWrapper requestInfoWrapper,
            @ModelAttribute final AssetConfigurationCriteria assetConfigurationCriteria,
            final BindingResult bindingResult) {

        LOGGER.debug("assetConfigurationSearch assetConfigurationCriteria:" + assetConfigurationCriteria);
        if (bindingResult.hasErrors()) {
            final ErrorResponse errorResponse = assetCommonService.populateErrors(bindingResult);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        final AssetConfigurationResponse assetConfigurationResponse = assetConfigurationService
                .search(assetConfigurationCriteria);

        return new ResponseEntity<AssetConfigurationResponse>(assetConfigurationResponse, HttpStatus.OK);
    }

}
