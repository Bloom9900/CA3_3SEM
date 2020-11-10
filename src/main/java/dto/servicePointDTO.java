package dto;

public class servicePointDTO {
    private String servicePointId;
    private String name;
    private String routeDistance;
    private visitingAddressDTO visitingAddress;

    public servicePointDTO(String servicePointId, String name, String routeDistance, visitingAddressDTO visitingAddress) {
        this.servicePointId = servicePointId;
        this.name = name;
        this.routeDistance = routeDistance;
        this.visitingAddress = visitingAddress;
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

    public visitingAddressDTO getVisitingAddress() {
        return visitingAddress;
    }

    public void setVisitingAddress(visitingAddressDTO visitingAddress) {
        this.visitingAddress = visitingAddress;
    }
}
