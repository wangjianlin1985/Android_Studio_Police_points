package com.mobileclient.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.Zxyjf;
import com.mobileclient.service.ZxyjfService;
import com.mobileclient.util.ZxyjfSimpleAdapter;
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

public class ZxyjfListActivity extends Activity {
	ZxyjfSimpleAdapter adapter;
	ListView lv; 
	List<Map<String, Object>> list;
	int id;
	/* 单位民警积分操作业务逻辑层对象 */
	ZxyjfService zxyjfService = new ZxyjfService();
	/*保存查询参数条件的单位民警积分对象*/
	private Zxyjf queryConditionZxyjf;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxyjf_list);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("当前位置--单位民警积分列表");
		} else {
			setTitle("您好：" + username + "   当前位置---单位民警积分列表");
		}
		Bundle extras = this.getIntent().getExtras();
		if(extras != null) 
			queryConditionZxyjf = (Zxyjf)extras.getSerializable("queryConditionZxyjf");
		else if(!declare.getMc().equals("管理员")) {
			queryConditionZxyjf = new Zxyjf();
			queryConditionZxyjf.setBname(declare.getMc());
			queryConditionZxyjf.setSname("");
			queryConditionZxyjf.setJfjd("");
			queryConditionZxyjf.setJfdate(null); 
		}
		
		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		list = getDatas();
		try {
			adapter = new ZxyjfSimpleAdapter(this, list,
					R.layout.zxyjf_list_item,
					new String[] { "id","bname","btypes","sname","jftj","jfjd","jfdate" },
					new int[] { R.id.tv_id,R.id.tv_bname,R.id.tv_btypes,R.id.tv_sname,R.id.tv_jftj,R.id.tv_jfjd,R.id.tv_jfdate,},lv);
			lv.setAdapter(adapter);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 添加长按点击
		lv.setOnCreateContextMenuListener(zxyjfListItemListener);
		lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	int id = Integer.parseInt(list.get(arg2).get("id").toString());
            	Intent intent = new Intent();
            	intent.setClass(ZxyjfListActivity.this, ZxyjfDetailActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("id", id);
            	intent.putExtras(bundle);
            	startActivity(intent);
            }
        });
	}
	private OnCreateContextMenuListener zxyjfListItemListener = new OnCreateContextMenuListener() {
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			Declare declare = (Declare) ZxyjfListActivity.this.getApplication();
			if(declare.getMc().equals("管理员")) {
				menu.add(0, 0, 0, "编辑单位民警积分信息"); 
				menu.add(0, 1, 0, "删除单位民警积分信息");
			}
			
		}
	};

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //编辑单位民警积分信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取id
			id = Integer.parseInt(list.get(position).get("id").toString());
			Intent intent = new Intent();
			intent.setClass(ZxyjfListActivity.this, ZxyjfEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			startActivity(intent);
			ZxyjfListActivity.this.finish();
		} else if (item.getItemId() == 1) {// 删除单位民警积分信息
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
		Builder builder = new Builder(ZxyjfListActivity.this);
		builder.setMessage("确认删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = zxyjfService.DeleteZxyjf(id);
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
			/* 查询单位民警积分信息 */
			List<Zxyjf> zxyjfList = zxyjfService.QueryZxyjf(queryConditionZxyjf);
			for (int i = 0; i < zxyjfList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", zxyjfList.get(i).getId());
				map.put("bname", zxyjfList.get(i).getBname());
				map.put("btypes", zxyjfList.get(i).getBtypes());
				map.put("sname", zxyjfList.get(i).getSname());
				map.put("jftj", zxyjfList.get(i).getJftj());
				map.put("jfjd", zxyjfList.get(i).getJfjd());
				map.put("jfdate", zxyjfList.get(i).getJfdate());
				list.add(map);
			}
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Declare declare = (Declare) ZxyjfListActivity.this.getApplication();
		if(declare.getMc().equals("管理员")) {
			menu.add(0, 1, 1, "添加单位民警积分");
			menu.add(0, 2, 2, "查询单位民警积分");
			menu.add(0, 3, 3, "返回主界面");	
		} else {
			menu.add(0, 1, 1, "查询单位民警积分");
			menu.add(0, 2, 2, "返回主界面");
		}
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Declare declare = (Declare) ZxyjfListActivity.this.getApplication();
		if(declare.getMc().equals("管理员")) {
			if (item.getItemId() == 1) {
				// 添加单位民警积分信息
				Intent intent = new Intent();
				intent.setClass(ZxyjfListActivity.this, ZxyjfAddActivity.class);
				startActivity(intent);
				ZxyjfListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*查询单位民警积分信息*/
				Intent intent = new Intent();
				intent.setClass(ZxyjfListActivity.this, ZxyjfQueryActivity.class);
				startActivity(intent);
				ZxyjfListActivity.this.finish();
			} else if (item.getItemId() == 3) {
				/*返回主界面*/
				Intent intent = new Intent();
				intent.setClass(ZxyjfListActivity.this, MainMenuActivity.class);
				startActivity(intent);
				ZxyjfListActivity.this.finish();
			}
		} else {
			if (item.getItemId() == 1) {
				/*查询单位民警积分信息*/
				Intent intent = new Intent();
				intent.setClass(ZxyjfListActivity.this, ZxyjfDepartQueryActivity.class);
				startActivity(intent);
				ZxyjfListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*返回主界面*/
				Intent intent = new Intent();
				intent.setClass(ZxyjfListActivity.this, MainMenuDepartmentActivity.class);
				startActivity(intent);
				ZxyjfListActivity.this.finish();
			}
		}
		
		return true; 
	}
}
