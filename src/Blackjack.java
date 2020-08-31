import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * 主にブラックジャックの記述や実際の動作を行うクラス
 *
 * @author ozawa
 */
public class Blackjack {

	/** ゲームの勝利数を記録する */
	private int win = 0;
	/** ゲームの敗北数を記録する */
	private int lose = 0;
	/** ゲームの引き分け数を記録する */
	private int draw = 0;

	/** 賭けに使う為の所持チップ数 */
	private int tip = 10;
	/** 賭けに使う為のベット数*/
	private int bet = 0;

	/** プレイヤーの手札枚数を記録する */
	private int playerHands = 2;
	/** ディーラーの手札枚数を記録する */
	private int dealerHands = 2;

	/** プレイヤーの手札の合計ポイント */
	private int playerPoint = 0;
	/** ディーラーの手札の合計ポイント */
	private int dealerPoint = 0;

	/** プレイヤーの手札リストを生成 */
	List<Card> player = new ArrayList<>();
	/** ディーラーの手札リストを生成 */
	List<Card> dealer = new ArrayList<>();

	//Deckクラスのインスタンスを作成
	Deck deck = new Deck();

	/**
	 * ゲームを開始し繰り返し機能を持たせているメソッド
	 */
	public void start() {
		System.out.println("ブラックジャックを開始します");
		System.out.println("手持ちのチップ10枚からのスタートです");
		/** ゲームを続けるか決める変数を定義 */
		boolean continueFlg = false;

		// ゲームを開始、最後の選択次第で何回も続けられるようにする
		do {
			this.reset();
			this.setting();
			this.drawCard();
			this.saidPoint();
			this.battlePhase();

			// チップを使い切った場合GAME OVERとし、ゲームを終了する
			if (this.tip <= 0) {
				System.out.println("チップを使い切りました");
				System.out.println("GAME OVER");
				break;
			}

			// ゲームオーバーでない場合、ゲームを続けるか確認
			while (true) {
				System.out.println("ゲームを続けますか？ Yes:y  or  No:n");
				String str = InputUtils.input();

				// ゲームの継続判定
				if (str.equals("y")) {
					// ゲームを続ける場合
					continueFlg = true;
					break;
				} else if (str.equals("n")) {
					// ゲームを続けない場合
					continueFlg = false;
					System.out.println("最終所持チップ枚数は" + this.tip + "枚でした");
					System.out.println("ゲームを終了します");
					break;
				} else {
					// 入力不備
					System.out.println("入力が間違っています。yかnを入力してください");
				}
			}
		} while (continueFlg);
	}

	/**
	 * ゲームを繰り返すにあたって初期化する必要がある変数を初期化するメソッド
	 */
	public void reset() {

		// 手札リストをリセット
		this.player.clear();
		this.dealer.clear();

		// プレイヤーの手札枚数を記録する変数を再定義
		this.playerHands = 2;
		this.dealerHands = 2;

		// プレイヤーとディーラーの手札のポイントを再定義
		this.playerPoint = 0;
		this.dealerPoint = 0;

		this.deck.checkDeck();
	}

	/**
	 * 賭け金などを設定するメソッド
	 */
	public void setting() {

		// 掛け金を設定する為のwhile文
		while (true) {
			try {
				System.out.println("賭け金を設定して下さい。現在所持チップ" + this.tip + "枚");
				this.bet = InputUtils.inputInt();
				if (this.bet > 0 && this.bet <= this.tip) {
					// 入力が正しい場合ブレイク
					break;
				}
				System.out.println("入力を間違えています。1～所持チップ枚数内で入力してください");
			} catch (InputMismatchException e) {
				System.out.println("入力を間違えています。整数で入力してください");
			}
		}

	}

	/**
	 * プレイヤーとディーラーが山札からカードを引くメソッド
	 */
	public void drawCard() {

		// プレイヤー・ディーラーがカードを2枚引く
		this.player.add(this.deck.getCard());
		this.player.add(this.deck.getCard());
		this.dealer.add(this.deck.getCard());
		this.dealer.add(this.deck.getCard());
	}

	/**
	 * 現在ポイントについて記述するメソッド
	 */
	public void saidPoint() {
		// プレイヤー・ディーラーの手札のポイントを表示
		System.out.println("あなたの1枚目のカードは" + this.player.get(0).toDescription());
		System.out.println("ディーラーの1枚目のカードは" + this.dealer.get(0).toDescription());
		System.out.println("あなたの2枚目のカードは" + this.player.get(1).toDescription());
		System.out.println("ディーラーの2枚目のカードは秘密です");

		// プレイヤー・ディーラーのポイントを集計
		this.playerPoint = CardUtil.sumPoint(this.player);
		this.dealerPoint = CardUtil.sumPoint(this.dealer);
		System.out.println("あなたの現在のポイントは" + this.playerPoint + "です");
	}

	/**
	 * お互いに追加でカードを引くか決め、引いた場合バーストチェックを行うメソッド
	 */

	public void battlePhase() {

		// バーストした場合の結果を表示するための変数を定義
		boolean yourLose = false;

		// プレイヤーがカードを引くフェーズ
		while (true) {

			System.out.println("カードを引きますか？ Yes:y  or  No:n");

			// キーボードの入力を受け付けて、変数strに代入する
			String str = InputUtils.input();

			if ("n".equals(str)) {
				// カードを引かない場合、ブレイク
				break;
			} else if ("y".equals(str)) {
				// 手札の追加とバーストチェック

				// 山札から一枚追加
				this.player.add(this.deck.getCard());

				//プレイヤーの手札を一カウント進める

				this.playerHands++;

				System.out.println("あなたの" + this.playerHands + "枚目のカードは"
						+ this.player.get(this.playerHands - 1).toDescription());
				this.playerPoint = CardUtil.sumPoint(this.player);
				System.out.println("");
				System.out.println("現在のポイントは" + this.playerPoint + "です");
				// バースト判定を行う
				if (CardUtil.isBusted(this.playerPoint)) {
					// バーストしていた場合true
					yourLose = true;
				}
			} else {
				System.out.println("入力を間違えています。yかnを入力してください");
			}

			// プレイヤーがバーストした場合ディーラー勝利の結果表示
			if (yourLose) {
				System.out.println("残念、バーストしてしまいました。あなたの負けです。");
				this.lose++;
				this.tip -= this.bet;
				System.out.println("--------------------------------------------------");
				System.out.println("現在" + this.win + "勝" + this.lose + "負" + this.draw + "分け");
				System.out.println("所持チップ枚数 " + this.tip);
				System.out.println("--------------------------------------------------");
				return;
			}
		}

		System.out.println("ディーラーの2枚目のカードは" + this.dealer.get(1).toDescription() + "でした");

		// ディーラーが手札を17以上にするまでカードを引くフェーズ
		while (true) {
			if (this.dealerPoint >= 17) {
				// ディーラーの手札が17以上になった場合ブレイク
				break;
			}

			// 山札から手札に１枚加える
			this.dealer.add(this.deck.getCard());

			// ディーラーの手札を１カウント進める
			this.dealerHands++;

			// ディーラーのポイント合計を算出
			this.dealerPoint = CardUtil.sumPoint(this.dealer);

			System.out.println("ディーラーの" + this.dealerHands + "枚目のカードは"
					+ this.dealer.get(this.dealerHands - 1).toDescription());

			// ディーラーのバーストしていた場合プレイヤー勝利の結果表示
			if (CardUtil.isBusted(this.dealerPoint)) {
				System.out.println("");
				System.out.println("ディーラーのポイントは" + this.dealerPoint);
				System.out.println("ディーラーがバーストしました。あなたの勝ちです。");
				this.win++;
				this.tip += this.bet;
				System.out.println("--------------------------------------------------");
				System.out.println("現在" + this.win + "勝" + this.lose + "負" + this.draw + "分け");
				System.out.println("所持チップ枚数 " + this.tip);
				System.out.println("--------------------------------------------------");
				return;
			}
		}
		// resultメソッドを呼び出し結果を表示する
		this.result();
	}

	/**
	 * お互いにバーストしなかった場合、ポイントを比較し結果を表示するメソッド
	 */
	public void result() {
		// ポイントを比較する
		System.out.println("");
		System.out.println("あなたのポイントは" + this.playerPoint);
		System.out.println("ディーラーのポイントは" + this.dealerPoint);
		// 引き分けの場合
		if (this.playerPoint == this.dealerPoint) {
			this.draw++;
			System.out.println("引き分けです");
			// プレイヤー勝利の場合
		} else if (this.playerPoint > this.dealerPoint) {
			this.win++;
			this.tip += this.bet;
			System.out.println("勝ちました！");
			// プレイヤー敗北の場合
		} else if (this.playerPoint < this.dealerPoint) {
			this.lose++;
			this.tip -= this.bet;
			System.out.println("負けました・・・");
		}
		System.out.println("--------------------------------------------------");
		System.out.println("現在" + this.win + "勝" + this.lose + "負" + this.draw + "分け");
		System.out.println("所持チップ枚数 " + this.tip);
		System.out.println("--------------------------------------------------");

	}

}