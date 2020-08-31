import java.util.List;

/**
 * ポイントの計算やバースト判定を行うクラス
 *
 * @author ozawa
 *
 */
public class CardUtil {

	/**A(エース)のカードの枚数を記録する*/
	private static int ace = 0;

	/**
	 * 山札の数をカードの数に置き換えるメソッド
	 *
	 * @param cardNumber 山札からプレイヤーが引いた数字
	 * @return 1-52の山札の数をトランプの仕様に合わせ1-13までの数字に置き換えて値を返す
	 */
	public static int toNumber(int cardNumber) {
		int number = cardNumber % 13;
		if (number == 0) {
			number = 13;
		}

		return number;
	}

	/**
	 * 手札がバーストしているか判定するメソッド
	 *
	 * @param point 手札の合計ポイント
	 * @return バーストしているかの判定を返す
	 */
	public static boolean isBusted(int point) {
		if (point <= 21) {
			return false;
		}
		return true;

	}

	/**
	 * 現在の合計ポイントを計算するメソッド
	 *
	 * @param list 現在の各プレイヤーの手札リスト
	 * @return 合計ポイントを返す
	 */
	public static int sumPoint(List<Card> list) {
		// 合計値を初期化
		int sum = 0;
		// 手札のポイントをすべて合計する
		for (int i = 0; i < list.size(); i++) {
			sum = sum + toPoint(list.get(i).getNumber());
		}
		// もしAが一枚以上かつ合計値が11以内の場合
		if (ace >= 1 && sum <= 11) {
			sum += 10;
		}
		// 合計した後に値をリセット
		ace = 0;

		return sum;
	}

	/**
	 * トランプの数字を得点計算用のポイントに変換するメソッド.J/Q/Kは10とする
	 *
	 * @param num トランプの数字
	 * @return 11,12,13の数字を10に数え直し値を返す
	 */
	public static int toPoint(int num) {
		// カードの数字が11以上（絵札）の場合
		if (num == 11 || num == 12 || num == 13) {
			// 10とする
			num = 10;
		}
		// もしカードの数字が1(A)である場合
		if (num == 1) {
			// Aの数をプラスする
			ace++;
		}

		return num;
	}

}
