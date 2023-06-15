package com.mobileclient.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.Zxymjnd;
import com.mobileclient.service.ZxymjndService;
import com.mobileclient.util.ZxymjndSimpleAdapter;
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

public class ZxymjndListActivity extends Activity {
	ZxymjndSimpleAdapter adapter;
	ListView lv; 
	List<Map<String, Object>> list;
	int id;
	/* ��Ա��Ȼ��ֲ���ҵ���߼������ */
	ZxymjndService zxymjndService = new ZxymjndService();
	/*�����ѯ���������ľ�Ա��Ȼ��ֶ���*/
	private Zxymjnd queryConditionZxymjnd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxymjnd_list);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("��ǰλ��--��Ա��Ȼ����б�");
		} else {
			setTitle("���ã�" + username + "   ��ǰλ��---��Ա��Ȼ����б�");
		}
		Bundle extras = this.getIntent().getExtras();
		if(extras != null) 
			queryConditionZxymjnd = (Zxymjnd)extras.getSerializable("queryConditionZxymjnd");
		else if(!declare.getMc().equals("����Ա")) {
			queryConditionZxymjnd = new Zxymjnd();
			queryConditionZxymjnd.setBname(declare.getMc());
			queryConditionZxymjnd.setSname("");
			queryConditionZxymjnd.setJfjd("");
		 
		}
		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		list = getDatas();
		try {
			adapter = new ZxymjndSimpleAdapter(this, list,
					R.layout.zxymjnd_list_item,
					new String[] { "bname","sname","jfjd","hjf" },
					new int[] { R.id.tv_bname,R.id.tv_sname,R.id.tv_jfjd,R.id.tv_hjf,},lv);
			lv.setAdapter(adapter);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// ��ӳ������
		lv.setOnCreateContextMenuListener(zxymjndListItemListener);
		lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	int id = Integer.parseInt(list.get(arg2).get("id").toString());
            	Intent intent = new Intent();
            	intent.setClass(ZxymjndListActivity.this, ZxymjndDetailActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("id", id);
            	intent.putExtras(bundle);
            	startActivity(intent);
            }
        });
	}
	private OnCreateContextMenuListener zxymjndListItemListener = new OnCreateContextMenuListener() {
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			Declare declare = (Declare) ZxymjndListActivity.this.getApplication();
			if(declare.getMc().equals("����Ա")) {
				menu.add(0, 0, 0, "�༭��Ա��Ȼ�����Ϣ"); 
				menu.add(0, 1, 0, "ɾ����Ա��Ȼ�����Ϣ");
			}
			
		}
	};

	// �����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //�༭��Ա��Ȼ�����Ϣ
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// ��ȡѡ����λ��
			int position = contextMenuInfo.position;
			// ��ȡid
			id = Integer.parseInt(list.get(position).get("id").toString());
			Intent intent = new Intent();
			intent.setClass(ZxymjndListActivity.this, ZxymjndEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			startActivity(intent);
			ZxymjndListActivity.this.finish();
		} else if (item.getItemId() == 1) {// ɾ����Ա��Ȼ�����Ϣ
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// ��ȡѡ����λ��
			int position = contextMenuInfo.position;
			// ��ȡid
			id = Integer.parseInt(list.get(position).get("id").toString());
			dialog();
		}
		return super.onContextItemSelected(item);
	}

	// ɾ��
	protected void dialog() {
		Builder builder = new Builder(ZxymjndListActivity.this);
		builder.setMessage("ȷ��ɾ����");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = zxymjndService.DeleteZxymjnd(id);
				Toast.makeText(getApplicationContext(), result, 1).show();
				setViews();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			/* ��ѯ��Ա��Ȼ�����Ϣ */
			List<Zxymjnd> zxymjndList = zxymjndService.QueryZxymjnd(queryConditionZxymjnd);
			for (int i = 0; i < zxymjndList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id",zxymjndList.get(i).getId());
				map.put("bname", zxymjndList.get(i).getBname());
				map.put("sname", zxymjndList.get(i).getSname());
				map.put("jfjd", zxymjndList.get(i).getJfjd());
				map.put("hjf", zxymjndList.get(i).getHjf());
				list.add(map);
			}
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Declare declare = (Declare) ZxymjndListActivity.this.getApplication();
		if(declare.getMc().equals("����Ա")) {
			menu.add(0, 1, 1, "��Ӿ�Ա��Ȼ���");
			menu.add(0, 2, 2, "��ѯ��Ա��Ȼ���");
			menu.add(0, 3, 3, "����������");
		} else {
			menu.add(0, 1, 1, "��ѯ��Ա��Ȼ���");
			menu.add(0, 2, 2, "����������");
		}
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Declare declare = (Declare) ZxymjndListActivity.this.getApplication();
		if(declare.getMc().equals("����Ա")) {
			if (item.getItemId() == 1) {
				// ��Ӿ�Ա��Ȼ�����Ϣ
				Intent intent = new Intent();
				intent.setClass(ZxymjndListActivity.this, ZxymjndAddActivity.class);
				startActivity(intent);
				ZxymjndListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*��ѯ��Ա��Ȼ�����Ϣ*/
				Intent intent = new Intent();
				intent.setClass(ZxymjndListActivity.this, ZxymjndQueryActivity.class);
				startActivity(intent);
				ZxymjndListActivity.this.finish();
			} else if (item.getItemId() == 3) {
				/*����������*/
				Intent intent = new Intent();
				intent.setClass(ZxymjndListActivity.this, MainMenuActivity.class);
				startActivity(intent);
				ZxymjndListActivity.this.finish();
			}
		} else {
			if (item.getItemId() == 1) {
				/*��ѯ��Ա��Ȼ�����Ϣ*/
				Intent intent = new Intent();
				intent.setClass(ZxymjndListActivity.this, ZxymjndDepartQueryActivity.class);
				startActivity(intent);
				ZxymjndListActivity.this.finish();
			} else if (item.getItemId() == 3) {
				/*����������*/
				Intent intent = new Intent();
				intent.setClass(ZxymjndListActivity.this, MainMenuDepartmentActivity.class);
				startActivity(intent);
				ZxymjndListActivity.this.finish();
			}	
		}
		
		return true; 
	}
}
