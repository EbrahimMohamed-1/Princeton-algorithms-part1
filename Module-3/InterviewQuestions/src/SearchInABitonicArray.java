import java.util.Scanner;

public class SearchInABitonicArray {

    public static int findPeak(int [] arr, int low, int high){
        int mid, ans=1;// if len =2 set ans to 1
        while (low<high){
            mid= (low+high)/2;
            if (arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]) return mid;
            if (arr[mid]<arr[mid+1]) {
                low=mid+1;
                ans=mid+1;
            }
            else if (arr[mid]>arr[mid+1]) {
                high=mid-1;
                ans=mid-1;
            }
        }
       return ans;
    }


    public static int binarySearch(int [] arr, int low, int high, int target, boolean asc ){
        int mid;
        while (low<=high){
            mid= (low + high)/2;
            if (arr[mid]==target)return mid;
            if ((arr[mid]>target && asc) || (arr[mid]>target && !asc)) low= mid+1;
            else high=mid-1;
        }

        return -1;
    }
    public static int  find(int [] arr, int max, int len, int target){
        int ans= binarySearch(arr, 0, max, target, true );
        if (ans==-1){
            ans= binarySearch(arr, max, len, target, false );
        }
        return ans;
    }


    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int len= scanner.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i]= scanner.nextInt();
        }
        int max = findPeak(arr, 1, len-1);
       // System.out.println(max);

       System.out.println(find(arr, max, len-1, 5));

    }


}
