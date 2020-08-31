import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ゲームに使うデッキを作成するクラス
 *
 * @author ozawa
 */

public class Deck {

	/** デッキの入れ物でありこの中に数字を入れる */
	private ArrayList<Card> cards;

	/**
	 * createDeckを呼び出すメソッド
	 */
	public Deck() {
		this.createDeck();
	}

	/**
	 * deckのゲッター
	 *
	 * @return 変数を返り値に
	 */
	public Card getCard() {
		Card card = this.cards.get(0);
		this.cards.remove(0);
		return card;
	}

	/**
	 *  デッキの枚数が20枚以下の時にデッキを作り直すメソッド
	 */
	public void checkDeck() {
		if (this.cards.size() <= 20) {
			this.createDeck();
		}
	}

	/**
	 * デッキを作成しシャッフルメソッドを呼び出すメソッド
	 *
	 * @return デッキに1-52の数を入れシャッフルし値を返す
	 */
	private ArrayList<Card> createDeck() {
		this.cards = new ArrayList<>();
		for (int i = 1; i <= 52; i++) {
			Card card = new Card();
			//カードの数字を割り振る
			card.setNumber(CardUtil.toNumber(i));
			//カードのマークを割り振る
			if (i > 0 && i <= 13) {
				card.setSuit("スペード");
			}
			if (i > 13 && i <= 26) {
				card.setSuit("ダイヤ");
			}
			if (i > 26 && i <= 39) {
				card.setSuit("ハート");
			}
			if (i > 39 && i <= 52) {
				card.setSuit("クラブ");
			}
			this.cards.add(card);
		}
		// 山札をシャッフル
		this.shuffleDeck(this.cards);
		return this.cards;
	}

	/**
	 * シャッフルするメソッド
	 *
	 * @param deck1 山札リスト
	 */
	private void shuffleDeck(List<Card> deck1) {

		// 山札をシャッフル
		Collections.shuffle(deck1);
	}
}
