package com.gzeliga.playground.algorithms.amazon;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMovies {

	@Test
	public void test() {
		
		Movie a = new Movie(1, 1.2F);
		Movie b = new Movie(2, 2.4F);
		Movie c = new Movie(3, 3.6F);
		Movie d = new Movie(4, 4.8F);
		
		
		a.addSimilarMovie(b);
		a.addSimilarMovie(c);
		b.addSimilarMovie(d);
		c.addSimilarMovie(d);
		
		
		System.out.println(Movie.getMovieRecommendations(a, 3));
		
	}

}
