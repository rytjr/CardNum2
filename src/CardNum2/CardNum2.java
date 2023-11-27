package CardNum2;

import java.util.*;
import java.io.*;

/*숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다.
 *  정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을 작성하시오.*/

//이번 문제는 정렬한 후 이진탐색을 통해 탐색을 하였으며 카운팅 정렬을 사용하여 값을 저장하였습니다.
//처음에는 해쉬함수를 이용하려 했으나 숫자는 이진 탐색을 하는 것이 나을 것같아 이진탐색으로 하게 되었다. 탐색 방법은 이진탐색과 해쉬함수가 있다는 것을 다시 생각하는게 좋겠다.
public class CardNum2 {
	static int[] count = new int[20000001];
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		int Num = Integer.parseInt(bf.readLine());
		
		int[] arr = new int[Num];
		
		
		st = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < Num; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int Num2 = Integer.parseInt(bf.readLine());
		
		//두번째 숫자열을 저장할 배열을 두개를 만든이유는 정렬을 하면 순서를 잃어버리기 때문에 순서를 기억하기 위해 배열을 두개 만들었따.
		int[] arr2 = new int[Num2];
		int[] arr3 = new int[Num2];
		
		st = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < Num2; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
			arr3[i] = arr2[i];
		}
		
		//두번쨰 숫자열을 정렬
		mergeSort(arr2, 0, Num2 - 1);
		for(int i = 0; i < Num; i++) {
			//정렬한 후 숫자열을 이진탐색으로 원하는 값이 몇번 나오는지 탐색하고 그 숫자에 맞는 위치를 하나씩 더하며 숫자를 센다.
			BinarySort(arr2, arr[i]);
		}
		for(int i = 0; i < Num2; i++) {
			if(arr3[i] <= 0) {
				int d = arr3[i] * -1;
				arr3[i] = count[d];
			}
			else {
				arr3[i] = count[arr3[i] + 10000000];
			}
		}
		
		for(int i : arr3) {
			bw.write(i + " ");
		}
		bw.flush();
		bw.close();
		
	}
	
    public static void mergeSort(int[] arr, int begin, int end) {
		if(begin < end) {
			int middle = (begin + end) / 2;
			mergeSort(arr, begin, middle);
			mergeSort(arr, middle +  1, end);
			
			merge(arr, begin, middle, end);
		}
		
	}
    public static void merge(int[] arr, int begin, int middle, int end) {
    	int n1 = middle - begin + 1;
    	int n2 = end - middle;
    	
    	int[] arrbegin = new int[n1];
    	int[] arrend = new int[n2];
    	
    	for(int i = 0; i < n1; i++) {
    		arrbegin[i] = arr[i + begin];
    	}
    	for(int i = 0; i < n2; i++) {
    		arrend[i] = arr[i + middle + 1];
    	}
    	
    	int i = 0; int j = 0; int k = begin;
    	while(i < n1 && j < n2) {
    		if(arrbegin[i] <= arrend[j]) {
    			arr[k] = arrbegin[i];
    			i++;
    		}
    		else {
    			arr[k] = arrend[j];
    			j++;
    		}
    		k++;
    	}
    	
    	while(i < n1) {
    		arr[k] = arrbegin[i];
    		i++;
    		k++;
    	}
    	while(j < n2) {
    		arr[k] = arrend[j];
    		j++;
    		k++;
    	}
    }
	
	public static void BinarySort(int[] arr, int key) {
		int begin = 0;
		int end = arr.length -1;
		
		while(begin <= end) {
			int middle = (end - begin) / 2 + begin;
			if(key < arr[middle]) {
				end = middle - 1;
			}
			else if(key == arr[middle]) {
				if(key <= 0) {
					//양수만 있는 것이아니기 때문에 음수도 카운팅하기 위해 숫자를 2배로 하고 음수는 -1을 곱한 위치를 카운팅해 준다.
					int c = key*-1;
					count[c]++;
				}
				else {
					//음수를 카운팅 해야하기 때문에 음수의 최대 범위인 10000000을 더한 곳에 카운팅
					count[key + 10000000]++;
				}
				 break;
			}
			else {
				begin = middle + 1;
			}
		}
	}

}
