package sort;

import java.util.Arrays;

/**
 * 
 * 
 * 快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。</br>
 * 步骤为：</br>
 * 1.从数列中挑出一个元素，称为 "基准"（pivot），</br>
 * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分割结束之后，该基准就处于数列的中间位置。这个称为分割（partition）操作。</br>
 * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。</br>
 * </br></br>
 *最好情况</br>
 *每次基准的最终位置都是在数组中间位置，从而使规模为N的问题分为2个规模为N/2的问题，即T(n) = 2T(n/2) + Θ(n)，用递归树分析或者主定理得到时间T(n) = O(nlogn)。</br>
 *最坏情况</br>
 *每次基准的最终位置都是第一个位置，从而规模为N的问题分为一个规模为N-1的问题，即T(n) = T(n-1) + Θ(n),用递归树分析可得运行时间T(n) = O(n2)。</br>
 *平均情况</br>
 *假设规模为N的问题分为一个规模为9/10N的问题和规模为1/10N的问题，即T(n) = T(9n/10) + T(n/10) + Θ(n),用递归树分析可得T(n) = O(nlogn)，而且比分区9:1要更平均（也就是情况更好）的概率为80%，所以在绝大部分情况下快速排序算法的运行时间为O(nlogn)。</br>
 * @author 李山林
 *
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] source = new int[]{1, 3, 4, 5, 7, 2, 6, 8, 0};
		QuickSort sort = new QuickSort();
		sort.QuickSort(source, 0, source.length-1);
		System.out.println(Arrays.toString(source));
	}
	int Partition(int A[], int p, int r) {
		int privot = A[p]; // 基准
		// 设两个指针
		int left = p; // 从左往右扫描
		int right = r; // 从右往左扫描

		while (right > left) {
			while (right > left && A[right] >= privot) {
				right--;
			}

			A[left] = A[right];

			while (right > left && A[left] <= privot) {
				left++;
			}

			A[right] = A[left];
		}

		A[left] = privot;
		return left;
	}

	void QuickSort(int A[], int p, int r) {
		int pivot;

		if (r > p) {
			pivot = Partition(A, p, r);
			QuickSort(A, p, pivot - 1);
			QuickSort(A, pivot + 1, r);
		}
	}
}
