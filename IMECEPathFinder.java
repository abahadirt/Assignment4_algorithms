import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IMECEPathFinder{
	  public int[][] grid;
	  public int height, width;
	  public int maxFlyingHeight;
	  public double fuelCostPerUnit, climbingCostPerUnit;
	  public int max;
	  public int min;
	  public int[][] scaled;
	  public IMECEPathFinder(String filename, int rows, int cols, int maxFlyingHeight, double fuelCostPerUnit, double climbingCostPerUnit){

		  grid = new int[rows][cols];
		  this.height = rows;
		  this.width = cols;
		  this.maxFlyingHeight = maxFlyingHeight;
		  this.fuelCostPerUnit = fuelCostPerUnit;
		  this.climbingCostPerUnit = climbingCostPerUnit;

			// TODO: fill the grid variable using data from filename
		 
		  this.max= Integer.MIN_VALUE;
		  this.min= Integer.MAX_VALUE;
		  Scanner sc;
		  try {
	            sc = new Scanner(new File(filename));
	            for (int i = 0; i < height; i++) {
	                for (int j = 0; j < width; j++) {
	                    if (sc.hasNextInt()) {
	                    	grid[i][j] = sc.nextInt();
	                    	if(grid[i][j]>max) {
	                    		this.max=grid[i][j];
	                    	}
	                    	if(grid[i][j]<min) {
	                    		this.min=grid[i][j];
	                    	}
	                    }
	                }
	            }    
	        } 
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	       
	  }


	  /**
	   * Draws the grid using the given Graphics object.
	   * Colors should be grayscale values 0-255, scaled based on min/max elevation values in the grid
	   */
	  public void drawGrayscaleMap(Graphics g){

		  // TODO: draw the grid, delete the sample drawing with random color values given below

		  
		  for (int i = 0; i < grid.length; i++)
		  {
			  for (int j = 0; j < grid[0].length; j++) {
				 
				  int value = (grid[i][j]-min)*255/(max-min);	  
				  g.setColor(new Color(value, value, value));	  
				  g.fillRect(j, i, 1, 1);
				  
			  }
		  }
		  
		  
		  
		  
		  
	  }

	/**
	 * Get the most cost-efficient path from the source Point start to the destination Point end
	 * using Dijkstra's algorithm on pixels.
	 * @return the List of Points on the most cost-efficient path from start to end
	 */
	public List<Point> getMostEfficientPath(Point start, Point end) {

		List<Point> path = new ArrayList<>();

		// TODO: Your code goes here
		// TODO: Implement the Mission 0 algorithm here

		return path;
	}

	/**
	 * Calculate the most cost-efficient path from source to destination.
	 * @return the total cost of this most cost-efficient path when traveling from source to destination
	 */
	public double getMostEfficientPathCost(List<Point> path){
		double totalCost = 0.0;

		// TODO: Your code goes here, use the output from the getMostEfficientPath() method

		return totalCost;
	}


	/**
	 * Draw the most cost-efficient path on top of the grayscale map from source to destination.
	 */
	public void drawMostEfficientPath(Graphics g, List<Point> path){
		// TODO: Your code goes here, use the output from the getMostEfficientPath() method
	}

	/**
	 * Find an escape path from source towards East such that it has the lowest elevation change.
	 * Choose a forward step out of 3 possible forward locations, using greedy method described in the assignment instructions.
	 * @return the list of Points on the path
	 */
	public List<Point> getLowestElevationEscapePath(Point start){
		List<Point> pathPointsList = new ArrayList<>();
		
		// TODO: Your code goes here
		// TODO: Implement the Mission 1 greedy approach here

		int x= start.x;
		int y= start.y;
		int difa;
		int difb;
		int difc;
		//starti ekledim
		pathPointsList.add(new Point(x,y));
		
		while(x!=grid[0].length-1) {
			difa= Math.abs(grid[y][x]-grid[y-1][x+1]);
			difb= Math.abs(	grid[y][x]-grid[y][x+1]);
			difc= Math.abs(	grid[y][x]-grid[y+1][x+1]);
			if(difb<=difa && difb<=difc) {
				x++;
			}
			else if(difa<=difc) {
				y--;
				x++;
			}
			else {
				y++;
				x++;
			}
			
			Point p = new Point(x,y);
			pathPointsList.add(p);
		}
		
		return pathPointsList;
	}


	/**
	 * Calculate the escape path from source towards East such that it has the lowest elevation change.
	 * @return the total change in elevation for the entire path
	 */
	public int getLowestElevationEscapePathCost(List<Point> pathPointsList){
		int totalChange = 0;
		
		// TODO: Your code goes here, use the output from the getLowestElevationEscapePath() method
		int i =0;
		while(i<pathPointsList.size()-1) {
			totalChange+= Math.abs(grid[pathPointsList.get(i).y][pathPointsList.get(i).x]-grid[pathPointsList.get(i+1).y][pathPointsList.get(i+1).x]);
		i++;
		}
		
		
		
		
	
		return totalChange;
	}


	/**
	 * Draw the escape path from source towards East on top of the grayscale map such that it has the lowest elevation change.
	 */
	public void drawLowestElevationEscapePath(Graphics g, List<Point> pathPointsList){
		// TODO: Your code goes here, use the output from the getLowestElevationEscapePath() method
		for(int i = 0; i < pathPointsList.size(); i++) {
  
			  g.setColor(new Color(255, 255, 0));	  
			  g.fillRect(pathPointsList.get(i).x, pathPointsList.get(i).y, 1, 1);
		
		}
		
		 
		
	}
	public void createFile(String f) {
		try {
		      FileWriter myWriter = new FileWriter(f);
		      String space;
		      for (int i = 0; i < grid.length; i++)
			  {
				  space="";
		    	  for (int j = 0; j < grid[0].length; j++) {
					  myWriter.write(space+(grid[i][j]-min)*255/(max-min));
					  space=" ";	  
				  }
				  
				  myWriter.write("\n");
			  }
		      
		      
		      
		      
		      myWriter.close();
		      
		    } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}


}
