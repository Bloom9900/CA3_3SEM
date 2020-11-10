package dto;

import java.util.ArrayList;

public class PostnordDTO {
    private String customerSupportPhoneNo;
    private ArrayList<servicePointDTO> servicePoints;

    public PostnordDTO(String customerSupportPhoneNo, ArrayList<servicePointDTO> servicePoints) {
        this.customerSupportPhoneNo = customerSupportPhoneNo;
        this.servicePoints = servicePoints;
    }

    public String getCustomerSupportPhoneNo() {
        return customerSupportPhoneNo;
    }

    public void setCustomerSupportPhoneNo(String customerSupportPhoneNo) {
        this.customerSupportPhoneNo = customerSupportPhoneNo;
    }

    public ArrayList<servicePointDTO> getServicePoints() {
        return servicePoints;
    }

    public void setServicePoints(ArrayList<servicePointDTO> servicePoints) {
        this.servicePoints = servicePoints;
    }
}
