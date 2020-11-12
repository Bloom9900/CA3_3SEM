
package dto;

public class MovieDTO {
    
    private Object results;

    public MovieDTO(Object movie) {
        this.results = movie;
    }

    public Object getMovie() {
        return results;
    }

    public void setMovie(Object movie) {
        this.results = movie;
    }

    
    
}
