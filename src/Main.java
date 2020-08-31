/**
 * ブラックジャックを起動するクラス
 *
 * @author ozawa
 */
public class Main {

	/**
	 * ゲームを開始するメインメソッド
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		//Blackjackクラスのインスタンスを生成
		Blackjack blackjack = new Blackjack();
		blackjack.start();
	}
}
