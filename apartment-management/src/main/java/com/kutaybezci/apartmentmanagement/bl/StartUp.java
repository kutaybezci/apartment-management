package com.kutaybezci.apartmentmanagement.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StartUp implements CommandLineRunner {
    @Autowired
    private UserBO userBO;

    @Override
    public void run(String... args) throws Exception {
	if (userBO.createSystemUser()) {
	    log.info("System created");
	}

    }

}
