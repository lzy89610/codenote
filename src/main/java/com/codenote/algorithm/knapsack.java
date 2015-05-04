package com.codenote.algorithm;

public class knapsack
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int n=3;
		int C=10;
		
		int[] v = {5, 4, 3};
		int[] w = {20, 10, 12};
		int[][] d = new int[4][11];
		int[] x = new int[4];
		
		for(int i=0; i<=n; ++i)
		{
			for(int j=0; j<=C; ++j)
			{
				if(i == 0)
				{
					d[i][j] = 0;
					continue;
				}
				if(j < v[i-1])
				{
					d[i][j] = d[i-1][j];
				}
				else
				{
					d[i][j] = max(d[i-1][j], d[i-1][j-v[i-1]]+w[i-1]);
				}
			}
		}
		
		for(int i=0; i<=n; ++i)
		{
			for(int j=0; j<=C; ++j)
			{
				System.out.print(d[i][j] + "\t");
			}
			System.out.println(" ");
		}
		System.out.println(d[n][C]);
		
		int j = C;
        for(int i=n; i>0; --i)
        {
            if(d[i][j] > d[i-1][j])
            {
                x[i-1] = 1;
                j = j - v[i-1];
            }
        }
        
        for(int i=0; i<n; ++i)  
        {
        	System.out.print(x[i] + " ");
        }
	}
	
	public static int max(int firstNum, int secondNum)
	{
		return firstNum>secondNum ? firstNum : secondNum;
	}

}
