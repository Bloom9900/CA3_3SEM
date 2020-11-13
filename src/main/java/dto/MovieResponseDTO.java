
package dto;

public class MovieResponseDTO {
    
    private Object results;

    public MovieResponseDTO(Object movie) {
        this.results = movie;
    }

    public Object getMovie() {
        return results;
    }

    public void setMovie(Object movie) {
        this.results = movie;
    }

    
    
}
