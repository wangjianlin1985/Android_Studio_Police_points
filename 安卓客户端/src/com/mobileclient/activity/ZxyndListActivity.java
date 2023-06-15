package com.mobileclient.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.Zxynd;
import com.mobileclient.service.ZxyndService;
import com.mobileclient.util.ZxyndSimpleAdapter;
import com.mobileclient.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ZxyndListActivity extends Activity {
	ZxyndSimpleAdapter adapter;
	ListView lv; 
	List<Map<String, Object>> list;
	int id;
	/* 单位年度积分操作业务逻辑层对象 */
	ZxyndService zxyndService = new ZxyndService();
	/*保存查询参数条件的单位年度积分对象*/
	private Zxynd queryConditionZxynd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxynd_list);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("当前位置--单位年度积分列表");
		} else {
			setTitle("您好：" + username + "   当前位置---单位年度积分列表");
		}
		Bundle extras = this.getIntent().getExtras();
		if(extras != null) 
			queryConditionZxynd = (Zxynd)extras.getSerializable("queryConditionZxynd");
		else if(!declare.getMc().equals("管理员")) {
			queryConditionZxynd = new Zxynd();
			queryConditionZxynd.setBname(declare.getMc());
			queryConditionZxynd.setJfjd("");
		}
		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		list = getDatas();
		try {
			adapter = new ZxyndSimpleAdapter(this, list,
					R.layout.zxynd_list_item,
					new String[] { "bname","jfjd","hjf" },
					new int[] { R.id.tv_bname,R.id.tv_jfjd,R.id.tv_hjf,},lv);
			lv.setAdapter(adapter);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 添加长按点击
		lv.setOnCreateContextMenuListener(zxyndListItemListener);
		lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	int id = Integer.parseInt(list.get(arg2).get("id").toString());
            	Intent intent = new Intent();
            	intent.setClass(ZxyndListActivity.this, ZxyndDetailActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("id", id);
            	intent.putExtras(bundle);
            	startActivity(intent);
            }
        });
	}
	private OnCreateContextMenuListener zxyndListItemListener = new OnCreateContextMenuListener() {
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			Declare declare = (Declare) ZxyndListActivity.this.getApplication();
			if(declare.getMc().equals("管理员 ")) {
				menu.add(0, 0, 0, "编辑单位年度积分信息"); 
				menu.add(0, 1, 0, "删除单位年度积分信息");
			}
			
		}
	};

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //编辑单位年度积分信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取id
			id = Integer.parseInt(list.get(position).get("id").toString());
			Intent intent = new Intent();
			intent.setClass(ZxyndListActivity.this, ZxyndEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			startActivity(intent);
			ZxyndListActivity.this.finish();
		} else if (item.getItemId() == 1) {// 删除单位年度积分信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取id
			id = Integer.parseInt(list.get(position).get("id").toString());
			dialog();
		}
		return super.onContextItemSelected(item);
	}

	// 删除
	protected void dialog() {
		Builder builder = new Builder(ZxyndListActivity.this);
		builder.setMessage("确认删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = zxyndService.DeleteZxynd(id);
				Toast.makeText(getApplicationContext(), result, 1).show();
				setViews();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			/* 查询单位年度积分信息 */
			List<Zxynd> zxyndList = zxyndService.QueryZxynd(queryConditionZxynd);
			for (int i = 0; i < zxyndList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id",zxyndList.get(i).getId());
				map.put("bname", zxyndList.get(i).getBname());
				map.put("jfjd", zxyndList.get(i).getJfjd());
				map.put("hjf", zxyndList.get(i).getHjf());
				list.add(map);
			}
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Declare declare = (Declare) ZxyndListActivity.this.getApplication();
		if(declare.getMc().equals("管理员 ")) {
			menu.add(0, 1, 1, "添加单位年度积分");
			menu.add(0, 2, 2, "查询单位年度积分");
			menu.add(0, 3, 3, "返回主界面");
		} else {
			menu.add(0, 1, 1, "查询单位年度积分");
			menu.add(0, 2, 2, "返回主界面");
		}
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Declare declare = (Declare) ZxyndListActivity.this.getApplication();
		if(declare.getMc().equals("管理员 ")) {
			if (item.getItemId() == 1) {
				// 添加单位年度积分信息
				Intent intent = new Intent();
				intent.setClass(ZxyndListActivity.this, ZxyndAddActivity.class);
				startActivity(intent);
				ZxyndListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*查询单位年度积分信息*/
				Intent intent = new Intent();
				intent.setClass(ZxyndListActivity.this, ZxyndQueryActivity.class);
				startActivity(intent);
				ZxyndListActivity.this.finish();
			} else if (item.getItemId() == 3) {
				/*返回主界面*/
				Intent intent = new Intent();
				intent.setClass(ZxyndListActivity.this, MainMenuActivity.class);
				startActivity(intent);
				ZxyndListActivity.this.finish();
			}
		} else {
			if (item.getItemId() == 1) {
				/*查询单位年度积分信息*/
				Intent intent = new Intent();
				intent.setClass(ZxyndListActivity.this, ZxyndDepartQueryActivity.class);
				startActivity(intent);
				ZxyndListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*返回主界面*/
				Intent intent = new Intent();
				intent.setClass(ZxyndListActivity.this, MainMenuDepartmentActivity.class);
				startActivity(intent);
				ZxyndListActivity.this.finish();
			}
		}
		
		return true; 
	}
}
