package com.can2do.bbs;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.can2do.bbs.net.Api;
import com.can2do.bbs.R;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	/*
	 * 退出时间设置
	 */
	private long m_exitTime = 0;
	/*
	 * 底部菜单
	 */
	private String[] m_tabTitle = new String[] { "新贴", "主页", "安全资讯", "设置" };
	/*
	 * 底部菜单Intent类
	 * ForumDisplayPage论坛显示类
	 * ForumHomePage为主页显示类
	 * ForumDisplayPage论坛显示类
	 * SettingPage设置类
	 */
	private Class<?>[] m_tabIntent = new Class<?>[] { ForumDisplayPage.class,
			ForumHomePage.class, ForumDisplayPage.class, SettingPage.class };
	/*
	 * 底部菜单图标显示
	 */
	private int[] m_tabIcon = new int[] { R.drawable.collections_view_as_list,
			R.drawable.collections_view_as_grid, R.drawable.coffee,
			R.drawable.action_settings };
	/*
	 * 数据绑定
	 * NEW_FORUM_ID新贴集合版块id
	 * SECURITY_FORUM_ID安全版块id
	 */
	private Bundle[] m_data = new Bundle[] {
			createBundle(Api.NEW_FORUM_ID, "新贴", true), null,
			createBundle(Api.SECURITY_FORUM_ID, "安全资讯", true), null };
	/*
	 * (non-Javadoc)
	 * @see android.app.ActivityGroup#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
		TabHost tabHost = getTabHost();
		for (int i = 0; i < this.m_tabTitle.length; i++) {
			String title = this.m_tabTitle[i];
			Intent intent = new Intent(this, m_tabIntent[i]);
			if (m_data[i] != null) {
				intent.putExtras(m_data[i]);
			}
			View tab = getLayoutInflater().inflate(R.layout.forum_tab, null);
			ImageView imgView = (ImageView) tab.findViewById(R.id.tabIcon);
			imgView.setImageResource(m_tabIcon[i]);
			TabSpec spec = tabHost.newTabSpec(title).setIndicator(tab)
					.setContent(intent);
			tabHost.addTab(spec);
		}
		// 每次进入检测更新
		App app = (App) this.getApplication();
		app.checkUpdate(this);
	}
	//按二次退出程序
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - m_exitTime) > 2000) { // System.currentTimeMillis()无论何时调用，肯定大于2000
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				m_exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	private Bundle createBundle(int id, String title, boolean bHideBackBtn) {
		Bundle data = new Bundle();
		data.putInt("id", id);
		data.putString("title", title);
		data.putBoolean("isHideBackBtn", bHideBackBtn);
		return data;
	}
}
