/**
 * カードの絵柄やデッキを作成するクラス
 *
 * @author ozawa
 */
public class Card {
	/** カードの数字 */
	private int number;

	/** カードのスート（ハートやスペードなどのマーク）  */
	private String suit;

	/**
	 * numberのゲッター
	 *
	 * @return カードの数字
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * numberのセッター
	 *
	 * @param number1 カードの数字
	 */
	public void setNumber(int number1) {
		this.number = number1;
	}

	/**
	 * suitのゲッター
	 *
	 * @return カードのマーク
	 */
	public String getSuit() {
		return this.suit;
	}

	/**
	 * suitのセッター
	 *
	 * @param suit1 カードのマーク
	 */
	public void setSuit(String suit1) {
		this.suit = suit1;
	}

	/** カードのランク(AやJ,Q,K) */
	private String rank;

	/**
	 * カード番号をランク(AやJ,Q,K)に変換するメソッド
	 *
	 * @return ランクに変換して値を返す
	 */
	public String setRank() {

		// ランクに変換するための変数を初期化
		String rank1 = null;

		switch (this.number) {

		case 1:
			rank1 = "A";
			break;

		case 11:
			rank1 = "J";
			break;

		case 12:
			rank1 = "Q";
			break;

		case 13:
			rank1 = "K";
			break;

		default:
			String str = String.valueOf(this.number);
			rank1 = str;

		}
		this.rank = rank1;
		return this.rank;

	}

	/**
	 * 山札の数を（スート）の（ランク）の文字列に置き換えるメソッド
	 *
	 * @return (スート）の(ランク)の文字列にして値を返す
	 */
	public String toDescription() {
		String rank1 = this.setRank();
		String suit1 = this.getSuit();

		return suit1 + "の" + rank1;
	}

}