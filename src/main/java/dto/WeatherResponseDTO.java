package dto;

import java.util.ArrayList;

public class WeatherResponseDTO {
    private Object data;

    public WeatherResponseDTO(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
