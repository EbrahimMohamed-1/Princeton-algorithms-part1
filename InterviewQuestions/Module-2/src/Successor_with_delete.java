public class Successor_with_delete{
    int n;
    private int id[];
    private int cnt[];
    private int actualSuccessor[];
    public Successor_with_delete(int n){
        id= new int [n];
        cnt= new int[n];
        actualSuccessor = new int[n];
        this.n=n;
        for (int i = 0; i < n; i++) {
            id[i]=i;
            cnt[i]=1;
            actualSuccessor[i]=i;
        }

    }


    private int root(int i){
        int root=i;
        while (root!=id[root]){
            i=id[i];// to find the root , root =rootId
        }

        //path compression
        while (i!=root){
            int next= id[i];
            id[i]=root;
            i=next;
        }

        return root;
    }

    public boolean connected(int p, int q){
        return root(p)==root(q);

    }

    public void union(int p, int q){
        int i= root(p);
        int j= root(q);
        if (cnt[i]<cnt[j]){
            id[i]=j;
            cnt[j]+=cnt[i];
        }
        else {
            id[j]=i;
            cnt[i]+=cnt[j];
            actualSuccessor[i]=j;

        }

    }


    //Question 3
    //Successor with delete
    public void remove(int x) {
        if (x < 0 || x >= n) throw new IllegalArgumentException("Invalid element");
        if (x + 1 < n) {
            union(x, x + 1);
        }
    }


    public int successor(int x) {
       return actualSuccessor[root(x+1)];
    }
    
}
