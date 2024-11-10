public class QuickUnionPathCompression {

    private int id[];
    private int cnt[]; // to make linking time (check it connected) small when you check root you minimize while loop.
    public QuickUnionPathCompression(int n){
        id= new int [n];
        cnt= new int[n];
        for (int i = 0; i < n; i++) {
            id[i]=i;
            cnt[i]=1;
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
        if (cnt[i]>cnt[j]){
            id[j]=i;
            cnt[i]+=cnt[j];
        }
        else {
            id[i]=j;
            cnt[j]+=cnt[i];

        }

    }


}
