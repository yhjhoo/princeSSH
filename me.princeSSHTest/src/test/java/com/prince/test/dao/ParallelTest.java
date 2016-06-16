package com.prince.test.dao;

import java.util.ArrayList;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
 
public class ParallelTest {
 
    /**
     * Maximum amount of numbers to check
     */
    public static final int MAX_NUMBER = 2000000000;
 
    /**
     * Returns the amount of numbers that can be divided by the divisor without remainder.
     * @param first First number to check
     * @param last Last number to check
     * @param divisor Divisor
     * @return Amount of numbers that can be divided by the divisor without remainder
     */
    public static int amountOfDivisibleBy(int first, int last, int divisor) {
 
        int amount = 0;
        for (int i = first; i <= last; i++) {
            if (i % divisor == 0) {
                amount++;
            }
        }
        return amount;
    }
 
    /**
     * Returns the amount of numbers that can be divided by the divisor without remainder (using parallel execution).
     * @param first First number to check
     * @param last Last number to check
     * @param divisor Divisor
     * @return Amount of numbers that can be divided by the divisor without remainder
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static int amountOfDivisibleByFuture(final int first, final int last, final int divisor)
            throws InterruptedException, ExecutionException {
 
        int amount = 0;
 
        // Prepare to execute and store the Futures
        int threadNum = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
 
        // Start thread for the first half of the numbers
        FutureTask<Integer> futureTask_1 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() {
                return ParallelTest.amountOfDivisibleBy(first, last / 2, divisor);
            }
        });
        taskList.add(futureTask_1);
        executor.execute(futureTask_1);
 
        // Start thread for the second half of the numbers
        FutureTask<Integer> futureTask_2 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() {
                return ParallelTest.amountOfDivisibleBy(last / 2 + 1, last, divisor);
            }
        });
        taskList.add(futureTask_2);
        executor.execute(futureTask_2);
        
        
      /*  FutureTask<List<Integer>> futureTask_3 = new FutureTask<List<Integer>>(new Callable<List<Integer>>() {
			@Override
			public List<Integer> call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
            
        });*/
 
        // Wait until all results are available and combine them at the same time
        for (int j = 0; j < threadNum; j++) {
            FutureTask<Integer> futureTask = taskList.get(j);
            amount += futureTask.get();
        }
        executor.shutdown();
 
        return amount;
    }
    
    
    /**
     * Returns the amount of numbers that can be divided by the divisor without remainder (using parallel execution).
     * @param first First number to check
     * @param last Last number to check
     * @param divisor Divisor
     * @return Amount of numbers that can be divided by the divisor without remainder
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static int amountOfDivisibleByFuture3(final int first, final int last, final int divisor)
            throws InterruptedException, ExecutionException {
 
        int amount = 0;
 
        // Prepare to execute and store the Futures
        int threadNum = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
 
        createExeCutors(threadNum, taskList, executor, first, last, divisor);
 
        // Wait until all results are available and combine them at the same time
        for (int j = 0; j < threadNum; j++) {
            FutureTask<Integer> futureTask = taskList.get(j);
            amount += futureTask.get();
        }
        executor.shutdown();
 
        return amount;
    }
    
    private static void createExeCutors(int threadNum, List<FutureTask<Integer>> taskList, ExecutorService executor, final int first, final int last, final int divisor){
    	int creament = last/threadNum;
    	for(int i=0; i< threadNum; i++){
    		final int tmpFirst;
    		if(i==0){
    			tmpFirst = first;
    		}else{
    			tmpFirst = first + i * creament + 1; 
    		}
    		final int tmpLast = tmpFirst + creament - 1;
    		 // Start thread for the second half of the numbers
            FutureTask<Integer> futureTask_2 = new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() {
                    return ParallelTest.amountOfDivisibleBy(tmpFirst, tmpLast, divisor);
                }
            });
            taskList.add(futureTask_2);
            executor.execute(futureTask_2);
    	}
    	
    	
    }
 
    /**
     * Executing the example.
     * @param args Command line arguments
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
 
        // Sequential execution
        long timeStart = Calendar.getInstance().getTimeInMillis();
        int result = ParallelTest.amountOfDivisibleBy(0, MAX_NUMBER, 3);
        long timeEnd = Calendar.getInstance().getTimeInMillis();
        long timeNeeded = timeEnd - timeStart;
        System.out.println("Result         : " + result + " calculated in " + timeNeeded + " ms");
 
        // Parallel execution
        {
        long timeStartFuture = Calendar.getInstance().getTimeInMillis();
        int resultFuture = ParallelTest.amountOfDivisibleByFuture(0, MAX_NUMBER, 3);
        long timeEndFuture = Calendar.getInstance().getTimeInMillis();
        long timeNeededFuture = timeEndFuture - timeStartFuture;
        System.out.println("Result (Future): " + resultFuture + " calculated in " + timeNeededFuture + " ms");
        }
        
        
        {
        	// Parallel execution
            long timeStartFuture = Calendar.getInstance().getTimeInMillis();
            int resultFuture = ParallelTest.amountOfDivisibleByFuture3(0, MAX_NUMBER, 3);
            long timeEndFuture = Calendar.getInstance().getTimeInMillis();
            long timeNeededFuture = timeEndFuture - timeStartFuture;
            System.out.println("Result (Future): " + resultFuture + " calculated in " + timeNeededFuture + " ms");
        }
    }	
}
