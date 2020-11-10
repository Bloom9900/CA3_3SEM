package dto;

import java.util.ArrayList;

public class WeatherDTO {
    private Object data;

    public WeatherDTO(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
