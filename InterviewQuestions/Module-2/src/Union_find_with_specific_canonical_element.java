public class Union_find_with_specific_canonical_element {

    private int id[];
    private int cnt[];
    private int large[];
    public Union_find_with_specific_canonical_element(int n){
        id= new int [n];
        cnt= new int[n];
        large= new int[n];
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


        return root;
    }

    public boolean connected(int p, int q){
        return root(p)==root(q);

    }
    public int find(int p){
        return large[p];
    }

    public void union(int p, int q){
        int i= root(p);
        int j= root(q);
        if (cnt[i]>=cnt[j]){
            id[j]=i;
            cnt[i]+=cnt[j];
            if (large[i]<large[j])large[i]=large[j];
            else large[j]=large[i];
        }
        else {
            id[i]=j;
            cnt[j]+=cnt[i];
            if (large[j]<large[i])large[j]=large[i];
            else large[i]=large[j];

        }

    }

}
