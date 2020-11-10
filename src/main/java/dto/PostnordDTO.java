package dto;

import java.util.ArrayList;

public class PostnordDTO {
    private PostnordDTO servicePointInformationResponse;
    private String customerSupportPhoneNo;
    private ServicePointDTO servicePoints;

    public PostnordDTO(String customerSupportPhoneNo, ServicePointDTO servicePoints) {
        this.customerSupportPhoneNo = customerSupportPhoneNo;
        this.servicePoints = servicePoints;
    }
    
    public PostnordDTO() {
    }

    public String getCustomerSupportPhoneNo() {
        return customerSupportPhoneNo;
    }

    public void setCustomerSupportPhoneNo(String customerSupportPhoneNo) {
        this.customerSupportPhoneNo = customerSupportPhoneNo;
    }

    public ServicePointDTO getServicePoints() {
        return servicePoints;
    }

    public void setServicePoints(ServicePointDTO servicePoints) {
        this.servicePoints = servicePoints;
    }
}
