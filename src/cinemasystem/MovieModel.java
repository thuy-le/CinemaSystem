/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author PC
 */
public class MovieModel extends Observable{
    
    private List<Movie> movies = new ArrayList<Movie>();
    
//    MovieModel(final List<Movie> movies){
//        this.movies = movies;
//    }
    
    public void addMovie(List<Movie> m){
        this.movies = m;
        setChanged();
        notifyObservers(movies);
    }
    
    public int deleteMovie(List<Movie> m, int id){
        this.movies = m;
        int result = 0;
        for (Movie movie : movies) {
            if(movie.getMovieID() == id){
                movies.remove(movie);
                System.out.println("From the model: " + id + movie.getName());
                result++;
                setChanged();
                notifyObservers(movies);
                break;
            }
        }        
        return result;
        
    }
    
    public void editMovie(int id, String name, int duration, List<ShowingTime> showingTime, List<String> period, String picture, MovieCategory movieCategory){
        for (Movie movie : movies) {
            if(movie.getMovieID() == id){
                movie.setName(name);
                movie.setDuration(duration);
                movie.setShowingTime(showingTime);
                movie.setPeriod(period);
                movie.setPicture(picture);
                movie.setMovieCategory(movieCategory);
                setChanged();
                notifyObservers(movies);
            }
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    
    
    
}
