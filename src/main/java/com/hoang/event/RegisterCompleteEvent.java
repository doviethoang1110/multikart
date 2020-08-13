package com.hoang.event;

import com.hoang.entities.Customer;
import org.springframework.context.ApplicationEvent;

public class RegisterCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Customer customer;
    public RegisterCompleteEvent(Customer customer,String appUrl) {
        super(customer);
        this.customer = customer;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
