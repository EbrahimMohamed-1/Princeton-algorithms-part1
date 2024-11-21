import java.util.Arrays;
import java.util.Scanner;

public class ThreeSUMInQuadraticTime {

    public static boolean binarySearch(int num, int arr [], int idx){
        int low = idx; int high = arr.length-1; int mid;
        while (low<=high){
            mid = low + (high - low)/2;
            if (arr[mid]+num ==0)return true;
            else if (arr[mid]+num<0) low=mid+1;
            else if (arr[mid]+num   >0)high=mid-1;
        }
        return false;
    }
//    public static void main(String [] args){
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] arr= new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i]=scanner.nextInt();
//        }
//        Arrays.stream(arr).sorted();
//        int cnt=0;
//        for (int i = 0; i < n; i++) {
//            for (int j = i+1; j < n; j++) {
//                int num= arr[i]+arr[j];
//                if (binarySearch(num, arr, j+1))cnt++;
//            }
//        }
//        System.out.println(cnt);
//
//
//    }

}
