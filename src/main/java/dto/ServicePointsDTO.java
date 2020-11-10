package dto;

public class ServicePointsDTO {
    private PostnordDTO postnord;
    private WeatherDTO weather;

    public ServicePointsDTO(PostnordDTO postnord, WeatherDTO weather) {
        this.postnord = postnord;
        this.weather = weather;
    }

    public PostnordDTO getPostnord() {
        return postnord;
    }

    public void setPostnord(PostnordDTO postnord) {
        this.postnord = postnord;
    }

    public WeatherDTO getWeather() {
        return weather;
    }

    public void setWeather(WeatherDTO weather) {
        this.weather = weather;
    }
}
