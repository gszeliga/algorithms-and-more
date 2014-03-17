package com.gzeliga.playground.algorithms.amazon;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import scala.actors.threadpool.Arrays;

public class Movie {

	private final int movieId;
	private final float rating;
	private List<Movie> similarMovies; // Similarity is bidirectional

	public Movie(int movieId, float rating) {
		this.movieId = movieId;
		this.rating = rating;
		similarMovies = new ArrayList<Movie>();
	}

	public int getId() {
		return movieId;
	}

	public float getRating() {
		return rating;
	}

	public void addSimilarMovie(Movie movie) {
		similarMovies.add(movie);
		movie.similarMovies.add(this);
	}

	public List<Movie> getSimilarMovies() {
		return similarMovies;
	}

	/*
	 * Implement a function to return top rated movies in the network of movies
	 * reachable from the current movie eg: A(Rating 1.2) / \ B(2.4) C(3.6) \ /
	 * D(4.8) In the above example edges represent similarity and the number is
	 * rating. getMovieRecommendations(A,2) should return C and D (sorting order
	 * doesn't matter so it can also return D and C)
	 * getMovieRecommendations(A,4) should return A, B, C, D (it can also return
	 * these in any order eg: B,C,D,A) getMovieRecommendations(A,1) should
	 * return D. Note distance from A to D doesn't matter, return the highest
	 * rated.
	 * 
	 * @param movie
	 * 
	 * @param numTopRatedSimilarMovies number of movies we want to return
	 * 
	 * @return List of top rated similar movies
	 */

	public static List<Movie> getMovieRecommendations(Movie movie,
			int numTopRatedSimilarMovies) {
		/*
		 * simple evaluation queue, I don't care about the rating since I need
		 * to navigate the whole graph to search for the highest rating.
		 */
		LinkedList<Movie> toBeVisited = new LinkedList<Movie>();

		BitSet visited = new BitSet();

		// This is more space-efficient. Using a priority queue would've been
		// easier but It would hold in memory the whole graph just
		// to poll 'numTopRatedSimilarMovies' elements
		Movie[] top = new Movie[numTopRatedSimilarMovies];

		// We start by all movie siblings
		toBeVisited.addAll(movie.getSimilarMovies());

		Movie current;

		// While there's stuff to be evaluated
		while (!toBeVisited.isEmpty()) {

			current = toBeVisited.removeFirst();

			// If it's not on the list then we mark it and add it to the rated
			// queue
			if (!visited.get(current.getId())) {
				visited.set(current.getId());

				Movie topMovie;
				Movie reference = current;

				for (int i = 0; i < numTopRatedSimilarMovies; i++) {

					topMovie = top[i];

					//If position is empty then we just need to add the movie
					if (topMovie == null) {
						top[i] = reference;
						break;
					} else if (topMovie.rating < reference.rating) {
						//swap movies and use smaller as the new reference
						top[i] = reference;
						reference = topMovie;
					}

				}
			}

			// If any of the siblings does not exist on the rated set, then we
			// add it to for later evaluation
			for (Movie sibling : current.getSimilarMovies()) {
				if (!visited.get(sibling.getId())) {
					toBeVisited.add(sibling);
				}
			}

		}
		
		//It will return null values if requested numTop is higher that available movies
		return Arrays.asList(top);
	}

	@Override
	public String toString() {
		return "Movie(" + movieId + "," + rating + ")";
	}

}
