# mobiquity
# Package Challenge

The goal of this application is to provide an optimal solution for a packaging company that wants to determine which things to put into the package so that the total weight is less than or equal to the package limit and the total cost is as large as possible


# Algorithm

In data structures and algorithm this problem is known as the Knapsack problem which has several ways of approach. The tricky bit is getting the indexes of the items that match the max value and minimum weight which eliminates the option of using recursion by brute force and for this reason the best approach is to use dynamic programming. 

## Design pattern & Strategy
The project approach makes use of the strategy pattern where we identify the aspects of the application that vary and separate them from what stays the same. Basically we need to take the parts that vary and encapsulate them, so that later you can alter or extend the parts that vary without affecting those that do not change

The parts that can change include the input file method where in this project we have `IFileReader` which is an interface that allows for multiple file reading. In this example we can have more than one file type to process and get the input data for us to process 

		-> com.mobiquity.filereaders
	
	                        `IFileReader`                                    

|----------------------|-------------------------------|-----------------------------|
|`TextFileReader`|		`'XmlFileReader'`     |`CsvFileReader`     |

We have the non changing algorithm separate from this 

		-> com.mobiquity.processors
	
	       `IProcess` -->     interface defining call to execute 
	       `Knapsack` --> implements execute issues call to knapsack algorithm                              



