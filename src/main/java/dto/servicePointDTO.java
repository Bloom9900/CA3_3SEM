package dto;

public class ServicePointDTO {
    private String servicePointId;
    private String name;
    private String routeDistance;
    private VisitingAddressDTO visitingAddress;

    public ServicePointDTO(String servicePointId, String name, String routeDistance, VisitingAddressDTO visitingAddress) {
        this.servicePointId = servicePointId;
        this.name = name;
        this.routeDistance = routeDistance;
        this.visitingAddress = visitingAddress;
    }
    
    public ServicePointDTO() {
        
    }

    public String getServicePointId() {
        return servicePointId;
    }

    public void setServicePointId(String servicePointId) {
        this.servicePointId = servicePointId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteDistance() {
        return routeDistance;
    }

    public void setRouteDistance(String routeDistance) {
        this.routeDistance = routeDistance;
    }

    public VisitingAddressDTO getVisitingAddress() {
        return visitingAddress;
    }

    public void setVisitingAddress(VisitingAddressDTO visitingAddress) {
        this.visitingAddress = visitingAddress;
    }
}
