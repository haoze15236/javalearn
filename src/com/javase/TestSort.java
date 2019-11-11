package com.javase;

import java.util.Arrays;

public class TestSort {

	public static void main(String[] args) {
//        int[] values = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        int values[] = { 3, 6, 1, 2, 9, 0, 7, 4, 5, 8 };
//        bubbleSort(values);
//        System.out.println(Arrays.binarySearch(values, 4));
//        Arrays.sort(values);
        System.out.println(Arrays.toString(values));
        byte num = -128;
        System.out.println(num+"的二进制是："+Integer.toBinaryString(num&0xff));
		int c = 0XFF;
		System.out.println(c+"的二进制是："+Integer.toBinaryString(c));
		Integer.valueOf(-1);
//        int num = binarySearch0(values,0,values.length,8);
//        System.out.println(num);
	}

	public static void bubbleSort(int [] values){
			int temp;
			int exchangeCount;
			boolean isFirstChange;
			for(int i = 0 ;i< values.length-1;i++){
				exchangeCount = 0;
				isFirstChange = false;
				for(int j =0;j<values.length-1-i;j++){
					if(values[j]>values[j+1]){
						temp =values[j];
						values[j]=values[j+1];
						values[j+1] = temp;
						exchangeCount++;
						if (j==0){
							isFirstChange = true;
						}
					}
	                System.out.printf("排序第%s级第%s次："+Arrays.toString(values)+'\n',i,j);
				}
				if(exchangeCount == 1 && isFirstChange==true){
					return;
				}
			}
	}
	/**
	 * 需要数组已排序
	 * @param var0 数组
	 * @param var1 default 0
	 * @param var2 数组长度
	 * @param var3 检索数
	 * @return 索引
	 */
	private static int binarySearch0(int[] var0, int var1, int var2, int var3) {
		int var4 = var1;
		int var5 = var2 - 1;

		while (var4 <= var5) {
			int var6 = var4 + var5 >>> 1;
			int var7 = var0[var6];
			if (var7 < var3) {
				var4 = var6 + 1;
			} else {
				if (var7 <= var3) {
					return var6;
				}

				var5 = var6 - 1;
			}
		}

		return -(var4 + 1);
	}
}
