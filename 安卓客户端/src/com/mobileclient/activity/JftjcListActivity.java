package com.mobileclient.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.Jftjc;
import com.mobileclient.service.JftjcService;
import com.mobileclient.util.JftjcSimpleAdapter;
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

public class JftjcListActivity extends Activity {
	JftjcSimpleAdapter adapter;
	ListView lv; 
	List<Map<String, Object>> list;
	int id;
	/* 积分条件操作业务逻辑层对象 */
	JftjcService jftjcService = new JftjcService();
	/*保存查询参数条件的积分条件对象*/
	private Jftjc queryConditionJftjc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jftjc_list);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("当前位置--积分条件列表");
		} else {
			setTitle("您好：" + username + "   当前位置---积分条件列表");
		}
		Bundle extras = this.getIntent().getExtras();
		if(extras != null) 
			queryConditionJftjc = (Jftjc)extras.getSerializable("queryConditionJftjc");
		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		list = getDatas();
		try {
			adapter = new JftjcSimpleAdapter(this, list,
					R.layout.jftjc_list_item,
					new String[] { "id","jftj","fs","typeid","mtypeid" },
					new int[] { R.id.tv_id,R.id.tv_jftj,R.id.tv_fs,R.id.tv_typeid,R.id.tv_mtypeid,},lv);
			lv.setAdapter(adapter);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// 添加长按点击
		lv.setOnCreateContextMenuListener(jftjcListItemListener);
		lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	int id = Integer.parseInt(list.get(arg2).get("id").toString());
            	Intent intent = new Intent();
            	intent.setClass(JftjcListActivity.this, JftjcDetailActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("id", id);
            	intent.putExtras(bundle);
            	startActivity(intent);
            }
        });
	}
	private OnCreateContextMenuListener jftjcListItemListener = new OnCreateContextMenuListener() {
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			menu.add(0, 0, 0, "编辑积分条件信息"); 
			menu.add(0, 1, 0, "删除积分条件信息");
		}
	};

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //编辑积分条件信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取积分id
			id = Integer.parseInt(list.get(position).get("id").toString());
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, JftjcEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			startActivity(intent);
			JftjcListActivity.this.finish();
		} else if (item.getItemId() == 1) {// 删除积分条件信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取积分id
			id = Integer.parseInt(list.get(position).get("id").toString());
			dialog();
		}
		return super.onContextItemSelected(item);
	}

	// 删除
	protected void dialog() {
		Builder builder = new Builder(JftjcListActivity.this);
		builder.setMessage("确认删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = jftjcService.DeleteJftjc(id);
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
			/* 查询积分条件信息 */
			List<Jftjc> jftjcList = jftjcService.QueryJftjc(queryConditionJftjc);
			for (int i = 0; i < jftjcList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", jftjcList.get(i).getId());
				map.put("jftj", jftjcList.get(i).getJftj());
				map.put("fs", jftjcList.get(i).getFs());
				map.put("typeid", jftjcList.get(i).getTypeid());
				map.put("mtypeid", jftjcList.get(i).getMtypeid());
				list.add(map);
			}
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 1, "添加积分条件");
		menu.add(0, 2, 2, "查询积分条件");
		menu.add(0, 3, 3, "返回主界面");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			// 添加积分条件信息
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, JftjcAddActivity.class);
			startActivity(intent);
			JftjcListActivity.this.finish();
		} else if (item.getItemId() == 2) {
			/*查询积分条件信息*/
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, JftjcQueryActivity.class);
			startActivity(intent);
			JftjcListActivity.this.finish();
		} else if (item.getItemId() == 3) {
			/*返回主界面*/
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, MainMenuActivity.class);
			startActivity(intent);
			JftjcListActivity.this.finish();
		}
		return true; 
	}
}
