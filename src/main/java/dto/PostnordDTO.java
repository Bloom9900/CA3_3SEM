package dto;

public class PostnordDTO {
    private Object servicePointInformationResponse;

    public PostnordDTO(Object servicePointInformationResponse) {
        this.servicePointInformationResponse = servicePointInformationResponse;
    }

    public Object getServicePointInformationResponse() {
        return servicePointInformationResponse;
    }

    public void setServicePointInformationResponse(Object servicePointInformationResponse) {
        this.servicePointInformationResponse = servicePointInformationResponse;
    }
}
