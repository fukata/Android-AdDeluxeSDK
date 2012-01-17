# Android adDeluxe SDK
アダルト広告の[adDeluxe](http://addeluxe.jp)のスマートフォンサイト用広告をAndroidアプリに表示するためのSDKです。

## サンプル
実際に組み込んだサンプルアプリは[こちら](https://github.com/fukata/Android-AdDeluxeExample)になります。

## 組み込み方 
### レイアウトXML
レイアウト用XMLの広告を表示したい箇所に下記のようなタグを追加してください。

    <!-- 広告表示用箇所 -->
    <RelativeLayout
        android:id="@+id/ad"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_alignParentBottom="true" >
    </RelativeLayout>

### 広告の表示
実際に広告を表示する場合にはアクティビティ等に下記のようなコードを追加してください。

	public class AdDeluxeExampleActivity extends Activity {
		
		public static final String ADDELUXE_SITE_ID = "919";
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			AdDeluxeAdView adView = new AdDeluxeAdView(this, ADDELUXE_SITE_ID);
			RelativeLayout adLayout = (RelativeLayout) findViewById(R.id.ad);
			adLayout.addView(adView);
		}
	}

### 色のカスタマイズ
adDeluxeの管理画面からHTML用のコードを取得する際に枠線の色などを設定できます。SDKでもカスタマイズが可能で、AdDeluxeViewの下記のプロパティにWEBと同じ形式の値をそれぞれ指定することで変更可能です。特に値のフォーマットはチェックしていないので、不正な値を指定した場合に正常に広告が表示されない場合があります。

	/**
	 * 枠線の色
	 */
	public String mBorderColor = "999999";
	
	/**
	 * 背景の色
	 */
	public String mBgColor = "FFFFFF";
	
	/**
	 * リンクの色
	 */
	public String mLinkColor = "2200CC";
	
	/**
	 * テキストの色
	 */
	public String mTextColor = "F25D5D";
	
	/**
	 * ドメインの色
	 */
	public String mDoaminColor = "671F28";
