package com.gim.java.Array;

public class ArrayInit {
	public static void main(String[] args) {

		/**
		 * 注意， 声明一个数组不需分配任何存储空间，它仅仅是代表你试图创建一个数组。跟C/C++声明一个数组的明显区别就是空间的大小没有被特别标识。
		 * 因此，下面的声明将会引起一个编译期错误。
		 * int num[5];
		 * 即：上面代码Demo中静态初始化中的方法二，如果改为String dogs[3] = {"Jimmy","Gougou","Doggy"};  则在编译时报错。
		 * 一个数组的大小将在数组使用new关键字真正创建时被给定，例如：
		 * int num[];
		 * num = new int[5];
		 * ————————————————
		 * 版权声明：本文为CSDN博主「DayThinking」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
		 * 原文链接：https://blog.csdn.net/sszgg2006/article/details/49148189
		 */

//		int num[5]; 报错

		//静态初始化数组：方法一
		String cats[] = new String[] {
				"Tom","Sam","Mimi"
		};

		//静态初始化数组：方法二
		String dogs[] = {"Jimmy","Gougou","Doggy"};

//		String dogs1[3] = {"Jimmy","Gougou","Doggy"};  报错

		//动态初始化数据
		String books[] = new String[2];
		books[0] = "Thinking in Java";
		books[1] = "Effective Java";

		System.out.println(cats.length);
		System.out.println(dogs.length);
		System.out.println(books.length);
	}
}
