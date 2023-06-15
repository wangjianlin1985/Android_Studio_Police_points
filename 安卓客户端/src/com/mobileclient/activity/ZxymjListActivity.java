package com.mobileclient.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.Zxymj;
import com.mobileclient.service.ZxymjService;
import com.mobileclient.util.ZxymjSimpleAdapter;
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

public class ZxymjListActivity extends Activity {
	ZxymjSimpleAdapter adapter;
	ListView lv; 
	List<Map<String, Object>> list;
	int id;
	/* 警员季度积分操作业务逻辑层对象 */
	ZxymjService zxymjService = new ZxymjService();
	/*保存查询参数条件的警员季度积分对象*/
	private Zxymj queryConditionZxymj;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxymj_list);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("当前位置--警员季度积分列表");
		} else {
			setTitle("您好：" + username + "   当前位置---警员季度积分列表");
		}
		Bundle extras = this.getIntent().getExtras();
		if(extras != null) 
			queryConditionZxymj = (Zxymj)extras.getSerializable("queryConditionZxymj");
		else if(!declare.getMc().equals("管理员")) {
			queryConditionZxymj = new Zxymj();
			queryConditionZxymj.setBname(declare.getMc());
			queryConditionZxymj.setSname("");
			queryConditionZxymj.setJfjd("");
		}
		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		list = getDatas();
		try {
			adapter = new ZxymjSimpleAdapter(this, list,
					R.layout.zxymj_list_item,
					new String[] { "bname","sname","jfjd","jdsdate","jdedate","hjf" },
					new int[] { R.id.tv_bname,R.id.tv_sname,R.id.tv_jfjd,R.id.tv_jdsdate,R.id.tv_jdedate,R.id.tv_hjf,},lv);
			lv.setAdapter(adapter);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 添加长按点击
		lv.setOnCreateContextMenuListener(zxymjListItemListener);
		lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	int id = Integer.parseInt(list.get(arg2).get("id").toString());
            	Intent intent = new Intent();
            	intent.setClass(ZxymjListActivity.this, ZxymjDetailActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("id", id);
            	intent.putExtras(bundle);
            	startActivity(intent);
            }
        });
	}
	private OnCreateContextMenuListener zxymjListItemListener = new OnCreateContextMenuListener() {
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			Declare declare = (Declare) ZxymjListActivity.this.getApplication();
			if(declare.getMc().equals("管理员")) {
				menu.add(0, 0, 0, "编辑警员季度积分信息"); 
				menu.add(0, 1, 0, "删除警员季度积分信息");
			}
		}
	};

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //编辑警员季度积分信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取id
			id = Integer.parseInt(list.get(position).get("id").toString());
			Intent intent = new Intent();
			intent.setClass(ZxymjListActivity.this, ZxymjEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			startActivity(intent);
			ZxymjListActivity.this.finish();
		} else if (item.getItemId() == 1) {// 删除警员季度积分信息
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
		Builder builder = new Builder(ZxymjListActivity.this);
		builder.setMessage("确认删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = zxymjService.DeleteZxymj(id);
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
			/* 查询警员季度积分信息 */
			List<Zxymj> zxymjList = zxymjService.QueryZxymj(queryConditionZxymj);
			for (int i = 0; i < zxymjList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id",zxymjList.get(i).getId());
				map.put("bname", zxymjList.get(i).getBname());
				map.put("sname", zxymjList.get(i).getSname());
				map.put("jfjd", zxymjList.get(i).getJfjd());
				map.put("jdsdate", zxymjList.get(i).getJdsdate());
				map.put("jdedate", zxymjList.get(i).getJdedate());
				map.put("hjf", zxymjList.get(i).getHjf());
				list.add(map);
			}
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Declare declare = (Declare) ZxymjListActivity.this.getApplication();
		if(declare.getMc().equals("管理员")) { 
			menu.add(0, 1, 1, "添加警员季度积分");
			menu.add(0, 2, 2, "查询警员季度积分");
			menu.add(0, 3, 3, "返回主界面");
		} else {
			menu.add(0, 1, 1, "查询警员季度积分");
			menu.add(0, 2, 2, "返回主界面");
		}
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Declare declare = (Declare) ZxymjListActivity.this.getApplication();
		if(declare.getMc().equals("管理员")) { 
			if (item.getItemId() == 1) {
				// 添加警员季度积分信息
				Intent intent = new Intent();
				intent.setClass(ZxymjListActivity.this, ZxymjAddActivity.class);
				startActivity(intent);
				ZxymjListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*查询警员季度积分信息*/
				Intent intent = new Intent();
				intent.setClass(ZxymjListActivity.this, ZxymjQueryActivity.class);
				startActivity(intent);
				ZxymjListActivity.this.finish();
			} else if (item.getItemId() == 3) {
				/*返回主界面*/
				Intent intent = new Intent();
				intent.setClass(ZxymjListActivity.this, MainMenuActivity.class);
				startActivity(intent);
				ZxymjListActivity.this.finish();
			}
		} else {
			if (item.getItemId() == 1) {
				/*查询警员季度积分信息*/
				Intent intent = new Intent();
				intent.setClass(ZxymjListActivity.this, ZxymjDepartQueryActivity.class);
				startActivity(intent);
				ZxymjListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*返回主界面*/
				Intent intent = new Intent();
				intent.setClass(ZxymjListActivity.this, MainMenuDepartmentActivity.class);
				startActivity(intent);
				ZxymjListActivity.this.finish();
			}
		}
		
		return true; 
	}
}
