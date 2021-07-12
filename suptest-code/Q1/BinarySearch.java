/**
 * Skeleton code for Binary Search.
 * You are required to implement the binary search method using proper recursion.
 *
 * The given code is provided to assist you to complete the required tasks. But the 
 * given code is often incomplete. You have to read and understand the given code 
 * carefully, before you can apply the code properly. You might need to implement 
 * additional procedures, such as error checking and handling, in order to apply the 
 * code properly.
 */

public class BinarySearch<T extends Comparable<T>>{

    /*
	 * Given a sorted 3D matrix A (sorted in every coordinate in ascending order) and a target key, implement 
	 * the binary search method to find and return the Element with the key that matches the target 
	 * within the range [minX, maxX]x[minY, maxY]x[minZ, maxZ], otherwise return null.
     * You must use binary search with proper recursion in the columns and rows of A simultaneously.      
	 * 
     * @param A is a sorted 3D array, such that 
	 *    A[x][y][0].key < ...< A[x][y][n-1].key and A[0][y][z].key < ...< A[n-1][y][z].key 
	 *       and A[x][0][z].key < ...< A[x][n-1][z].key, for all x, y, z in {0,...,n-1}.
     * @param minX is the minimum index in the first coordinate to be searched in A
     * @param maxX is the maximum index in the first coordinate to be searched in A
     * @param minY is the minimum index in the second coordinate to be searched in A
     * @param maxY is the maximum index in the second coordinate to be searched in A
     * @param minZ is the minimum index in the third coordinate to be searched in A
     * @param maxZ is the maximum index in the third coordinate to be searched in A	 
     * @param target is the target key
     * @return the object with the matched key if exist, otherwise return null.
     */
    public Element<T> search(Element<T>[][][] A, int minX, int maxX, int minY, int maxY, int minZ, int maxZ, T target){
        tracker.calltracking(minX,maxX,minY,maxY,minZ,maxZ); //Do not modify this method. Otherwise, your answers may not be marked correctly
        // TODO: Complete this method
        // START YOUR CODE
		if (minX > maxX || minY > maxY || minZ > maxZ) return null;

		if (target.compareTo(A[minX][minY][minZ].key) < 0 || target.compareTo(A[maxX][maxY][maxZ].key) > 0)
			return null;

		int midX = (maxX + minX) / 2;
		int midY = (maxY + minY) / 2;
		int midZ = (maxZ + minZ) / 2;
		Element<T> element1;
		Element<T> element2;
		Element<T> element3;

		if (target.compareTo(A[midX][midY][midZ].key) == 0) {
			return A[midX][midY][midZ];
		} else if (target.compareTo(A[midX][midY][midZ].key) < 0) {
			// lower right corner discarded
			element1 = search(A, minX, maxX, minY, maxY, minZ, midZ-1, target);
			element2 = search(A, minX, maxX, minY, midY-1, minZ, maxZ, target);
			element3 = search(A, minX, midX-1, minY, maxY, minZ, maxZ, target);
		} else {
			// upper left corner discarded
			element1 = search(A, minX, maxX, minY, maxY, midZ+1, maxZ, target);
			element2 = search(A, minX, maxX, midY+1, maxY, minZ, maxZ, target);
			element3 = search(A, midX+1, maxX, minY, maxY, minZ, maxZ, target);
		}
		if (element1 != null) {
			return element1;
		} else if (element2 != null) {
			return element2;
		} else {
			return element3;
		}
        // END YOUR CODE
    }
}