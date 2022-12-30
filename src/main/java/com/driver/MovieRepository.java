package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MovieRepository {
    private Map<String,Movie> Moviemap;
    private Map<String,Director> Directormap;
    private Map<String,List<String>> DirectorMovieMap;

    public MovieRepository() {
        Moviemap = new HashMap<>();
        Directormap = new HashMap<>();
        DirectorMovieMap = new HashMap<>();
    }
    public void saveMovie(Movie movie){
       Moviemap.put(movie.getName(), movie);
    }
    public void saveDirector(Director director){
        Directormap.put(director.getName(), director);
    }
    public void saveMovieDirectorPair(String movie, String director){
         if(Moviemap.containsKey(movie) && Directormap.containsKey(director)){
             List<String> currmoviesBydirector = new ArrayList<>();
             if(DirectorMovieMap.containsKey(director)){
                 currmoviesBydirector = DirectorMovieMap.get(director);
                 currmoviesBydirector.add(movie);
                 DirectorMovieMap.put(director,currmoviesBydirector);
             }
         }
    }
    public Movie findMovie(String name){
        return Moviemap.get(name);
    }
    public Director findDirector(String name){
        return Directormap.get(name);
    }
    public List<String> findMoviesFromDirector(String director){
      List<String> movies = new ArrayList<>();
        if(DirectorMovieMap.containsKey(director)) movies = DirectorMovieMap.get(director);
        return movies;
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(Moviemap.keySet());
    }
   public void deleteDirector(String director){
        if(DirectorMovieMap.containsKey(director)){
        List<String> movies = new ArrayList<>();
        movies = DirectorMovieMap.get(director);
        for(String Movies:movies){
            if(Moviemap.containsKey(Movies)){
                Moviemap.remove(Movies);
            }
        }
        DirectorMovieMap.remove(director);
        }
        if(Directormap.containsKey(director)){
            Directormap.remove(director);
        }
    }
    public void deleteAllDirector(){
        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        Directormap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: DirectorMovieMap.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: DirectorMovieMap.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(Moviemap.containsKey(movie)){
               Moviemap.remove(movie);
            }
        }
        //clearing the pair.
      DirectorMovieMap = new HashMap<>();
    }


}
