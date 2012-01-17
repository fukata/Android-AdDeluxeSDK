package org.fukata.android.addeluxe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class AdDeluxeAdView extends RelativeLayout {
	public static final String TAG = AdDeluxeAdView.class.getSimpleName();

	private static final String JS_HOST = "adv.addeluxe.jp";
	private static final String JS_VERSION = "1.4";
	private static final String ADS_JS = "http://adv.addeluxe.jp/js/iframe/adv.js";
	
	public Activity mActivity;
	
	/**
	 * サイトID
	 */
	public String mSiteId;
	
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
	
	public AdDeluxeAdView(Activity activity, String siteId) {
		super(activity);
		mActivity = activity;
		mSiteId = siteId;
		setVisibility(View.GONE);
	}

	protected void onWindowVisibilityChanged(int visibility) {
		super.onWindowVisibilityChanged(visibility);
		if (visibility == View.VISIBLE) {
			loadAd();
		}
	}
	
	public void loadAd() {
		Log.d(TAG, "loadAd SiteId: " + mSiteId);
		removeAllViewsInLayout();
		
		WebView view = new WebView(getContext());
		view.getSettings().setJavaScriptEnabled(true);
		view.getSettings().setGeolocationEnabled(true);
		view.getSettings().setSupportZoom(false);
		view.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
		view.setWebViewClient(new AdDeluxeWebViewClient());
		view.loadData(createAdHtml(), "text/html", "UTF-8");
		addView(view);
		setVisibility(View.VISIBLE);
	}
	
	String createAdHtml() {
		StringBuilder html = new StringBuilder();

		html.append("<!DOCTYPE HTML>");
		html.append("<html lang=\"ja\"><head>");
		html.append("<meta charset=\"UTF-8\">");
		html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		html.append("<title>adDeluxe</title>");
		html.append("<style> * { padding:0;margin:0; } </style>");
		html.append("</head>");
		html.append("<body>");
		html.append("<script type=\"text/javascript\" language=\"javascript\">");
		html.append("var addeluxue_conf = {");
		html.append("site:\"").append(mSiteId).append("\"");
		html.append(",frame:2,width:730,height:132,color:[\"").append(mBorderColor)
			.append("\",\"").append(mBgColor).append("\",\"")
			.append(mLinkColor).append("\",\"")
			.append(mTextColor).append("\",\"")
			.append(mDoaminColor)
			.append("\"],host:'").append(JS_HOST).append("',ver:").append(JS_VERSION).append("};</script>");
		html.append("<script type=\"text/javascript\" language=\"javascript\" src=\"").append(ADS_JS).append("\" charset=\"utf-8\"></script>");
		html.append("</body></html>");

		return html.toString();
	}
	
	class AdDeluxeWebViewClient extends WebViewClient {
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			Log.w(TAG, description);
			setVisibility(View.GONE);
		}

		/**
		 * クリックされた場合に、遷移先URLをブラウザで開く
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.d(TAG, "url=" + url);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
			intent.putExtra("referer", "http://google.com");
			mActivity.startActivity(intent);
			return true;
		}
	}
}
