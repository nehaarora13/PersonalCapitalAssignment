package com.pc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pc.dto.PlanDocument;
import com.pc.exceptions.BadRequestException;
import com.pc.exceptions.NoDataFoundException;
import com.pc.exceptions.ServiceException;
import com.pc.service.PersonalCapitalSearchService;

@RestController
@RequestMapping("/investments/v1")
public class SearchController {
	
	@Autowired
	private PersonalCapitalSearchService searchService;
	
	private static final Logger logger = LogManager.getLogger(SearchController.class);


	@RequestMapping(value = "/plan/name/{name}", method = { RequestMethod.GET })
    public List<PlanDocument> getPlansByName(@PathVariable String name) throws Exception {
		
        logger.debug(String.format("get search results for Plan name[%s]", name));
		if (null == name || "".equals(name)) {
            String msg = String.format("Invalid name : [%s] ", name);
            logger.error(msg);
            throw new BadRequestException(msg);
		}
        List<PlanDocument> plans;
        try {
            plans = searchService.findByName(name);
        } catch (Exception ex) {
            logger.error(String.format("An error occurred while retrieving a Plan with name [%s]: ", name), ex);
            throw new ServiceException(ex);
        }

        if (plans == null || plans.size() == 0) {
            String msg = String.format("No record found for plan name [%s] ", name);
            logger.info(msg);
            throw new NoDataFoundException(msg);
        }

        logger.info(String.format("Plans found for name [%s] ", name));
        return plans;
    }
    
    @RequestMapping(value = "/plan/{id}", method = { RequestMethod.GET })
    public PlanDocument findById(@PathVariable String id) throws Exception {
        return searchService.findById(id);
    }
    
    @RequestMapping(value = "/plan/dfename/{dfeName}", method = { RequestMethod.GET })
    public List<PlanDocument> findByDfeName(@PathVariable String dfeName) throws Exception {
		if (null == dfeName || "".equals(dfeName)) {
            String msg = String.format("Invalid Sponsor name : [%s] ", dfeName);
            logger.error(msg);
            throw new BadRequestException(msg);
		}
        List<PlanDocument> plans;
        try {
            plans = searchService.findByDfeName(dfeName);
        } catch (Exception ex) {
            logger.error(String.format("An error occurred while retrieving a Plan with DFE Name [%s]: ", dfeName), ex);
            throw new ServiceException(ex);
        }

        if (plans == null || plans.size() == 0) {
            String msg = String.format("No record found for plan dfe name [%s] ", dfeName);
            logger.info(msg);
            throw new NoDataFoundException(msg);
        }

        logger.info(String.format("Plans found for dfe name [%s] ", dfeName));
        return plans;
    }
    
    @RequestMapping(value = "/plan/sponsorstate/{dfeState}", method = { RequestMethod.GET })
    public List<PlanDocument> findByDfeState(@PathVariable String dfeState) throws Exception {
		if (null == dfeState || "".equals(dfeState)) {
            String msg = String.format("Invalid Sponsor state : [%s] ", dfeState);
            logger.error(msg);
            throw new BadRequestException(msg);
		}
        List<PlanDocument> plans;
        try {
            plans = searchService.findByDfeState(dfeState);
        } catch (Exception ex) {
            logger.error(String.format("An error occurred while retrieving a Plan with State [%s]: ", dfeState), ex);
            throw new ServiceException(ex);
        }

        if (plans == null || plans.size() == 0) {
            String msg = String.format("No record found for plan dfeState [%s] ", dfeState);
            logger.info(msg);
            throw new NoDataFoundException(msg);
        }

        logger.info(String.format("Plans found for dfeState [%s] ", dfeState));
        return plans;
    }

	public PersonalCapitalSearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(PersonalCapitalSearchService searchService) {
		this.searchService = searchService;
	}
    
    
}
