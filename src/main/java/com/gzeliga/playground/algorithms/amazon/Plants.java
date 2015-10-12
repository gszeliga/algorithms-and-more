package com.gzeliga.playground.algorithms.amazon;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by guillermo on 7/10/15.
 */
public class Plants {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

/*        Scanner scanner = new Scanner(System.in);

        int available = scanner.nextInt();

        Stack<Integer> plants = new Stack<>();

        for(int i = 0; i < available; i++)
        {
            plants.push(scanner.nextInt());
        }*/

        Stack<Integer> plants = new Stack<>();

        plants.push(6);
        plants.push(5);
        plants.push(8);
        plants.push(4);
        plants.push(7);
        plants.push(10);
        plants.push(9);

        int days = -1;
        int deaths;
        Stack<Integer> survivors = new Stack<>();

        do{
            deaths = 0;
            days++;

            if(!plants.empty())
            {
                int left = plants.pop();

                while(!plants.empty()){

                    int right = plants.pop();

                    if(left <= right)
                    {
                        survivors.push(left);
                    }
                    else
                    {
                        deaths++;
                    }

                    left = right;
                }

                //survivors.push(left);

                //Exchange stacks for space efficiency
                Stack<Integer> tmp = plants;
                plants = survivors;
                survivors = tmp;

            }
        }
        while(deaths > 0);

        System.out.print(days);
    }
}
