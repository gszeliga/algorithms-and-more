package com.gzeliga.playground.algorithms.amazon;

import java.io.IOException;
import java.util.*;

/**
 * Created by guillermo on 6/10/15.
 */
public class OtherAmazonStuff {

    /*
    * Given a string input "bedbathandbreakfast" o "sandwichasdf", return a true if you can split the string in token(s)
    * that are valid according to a provided dictionary. Solve, costs, etc...
    * */

    public static boolean tokenizable(String source, List<String> words) throws IOException {

        int pointer = 0;

        for(int i = 0; i < source.length();i++)
        {
            //Hash would make it constant 0(1)
            if(words.contains(source.substring(pointer, i + 1)))
            {
                pointer=i+1;
            }

            Stack<Integer> s = new Stack<>();

            while(!s.empty())
            {

            }

        }

        return pointer == source.length();
    }

    /*
    * - given an int, return a Set<Set<int>> of decompositions of the input
int into int's that sum the input. Example:
  input: 6
  output: 1+5 , 1+2+3 , 2+4
NOTE: 2+1+3 is not valid output because there's 1+2+3 already
Also, code and costs and etc..

    * */

    public Set<Set<Integer>> decompose(int value)
    {
        Set<Set<Integer>> result = new HashSet<>();

        if(value == 1 || value == 0)
        {
            result.add(new HashSet<>(value));
        }
        else
        {
            int left = 1;
            int right = value - 1;


        }

        return result;
    }

 }
