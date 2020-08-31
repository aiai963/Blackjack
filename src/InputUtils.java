
/*
 * (c) Japan Financial Engineering, Ltd
 */

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 標準入力用ユーティリティクラス
 *
 * @author ozawa
*/
public class InputUtils implements Cloneable, Serializable {
	private static Scanner scan = new Scanner(System.in);

	/**
	 * String型を標準入力するメソッド
	 *
	 * @return 入力された文字
	 */
	public static String input() {

		String result = "";

		try {
			result = scan.next();
		} catch (NoSuchElementException e) {
			// 入力無し
		}
		return result;
	}

	/**
	 * int型を標準入力するメソッド
	 *
	 * @return 入力された数字
	 */
	public static int inputInt() {

		int result = 0;

		scan = new Scanner(System.in);
		try {
			result = scan.nextInt();
		} catch (InputMismatchException e) {
			throw e;
		} catch (NoSuchElementException e) {
			// 入力無し
		}

		return result;
	}
}
