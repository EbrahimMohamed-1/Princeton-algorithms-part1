import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] isOpen;
    private WeightedQuickUnionUF uf; // for percolates()
    private WeightedQuickUnionUF ufBack; // for isFull()
    private final int n;
    private final int topIndex, bottomIndex;
    private int openCount=0;

    public Percolation(int n){
        if (n<=0) throw new java.lang.IllegalArgumentException();
        this.n= n;
        topIndex= 0;
        bottomIndex= n*n+1;
        uf= new WeightedQuickUnionUF(n*n+2);
        ufBack= new WeightedQuickUnionUF(n*n+1); //without bottom index
        isOpen = new boolean[n*n+2];
        isOpen[topIndex]=true;
        isOpen[bottomIndex]=true;

    }

    private int indexOf(int row, int col) {
        // check bounds
        if (row < 1 || row > n) {
            throw new IndexOutOfBoundsException("Row is out of bounds.");
        }
        if (col < 1 || col > n) {
            throw new IndexOutOfBoundsException("Column is out of bounds.");
        }
        return (row - 1) * n + col;// index
    }


    public void open(int row, int col) {
        int currIndex = indexOf(row, col);
        isOpen[currIndex] = true;
        openCount++;

        if (row == 1) {
            ufBack.union(currIndex, topIndex);  // Top
            uf.union(currIndex, topIndex);
        }
        if (row == n) {
            ufBack.union(currIndex, bottomIndex);  // Bottom
        }
        tryUnion(row, col, row - 1, col);  // up
        tryUnion(row, col, row + 1, col);  // bottom
        tryUnion(row, col, row, col - 1);  // left
        tryUnion(row, col, row, col + 1);  // right
    }

    public boolean isOpen(int row, int col) {
        return isOpen[indexOf(row, col)];
    }

    private void tryUnion(int rowA, int colA, int rowB, int colB) {
        // I assume that (rowA, colA) is correct.
        if (0 < rowB && rowB <= n && 0 < colB && colB <= n
                && isOpen(rowB, colB)) {
            ufBack.union(indexOf(rowA, colA), indexOf(rowB, colB));
            uf.union(indexOf(rowA, colA), indexOf(rowB, colB));
        }
    }

    public int numberOfOpenSites(){
        return openCount;
    }

    //is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return uf.connected(topIndex, indexOf(row, col));
    }

    //does the system percolate?
    public boolean percolates() {
        return ufBack.connected(topIndex, bottomIndex);
    }

}