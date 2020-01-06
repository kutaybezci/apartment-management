package com.kutaybezci.apartmentmanagement.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartUp implements CommandLineRunner {
    @Autowired
    private PersonBO userBO;

    @Override
    public void run(String... args) throws Exception {
	userBO.doCreateDefaultUsers();
    }

}
