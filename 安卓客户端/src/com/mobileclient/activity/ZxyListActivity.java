package com.mobileclient.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.Zxy;
import com.mobileclient.service.ZxyService;
import com.mobileclient.util.ZxySimpleAdapter;
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

public class ZxyListActivity extends Activity {
	ZxySimpleAdapter adapter;
	ListView lv; 
	List<Map<String, Object>> list;
	int id;
	/* ��λ���Ȼ��ֲ���ҵ���߼������ */
	ZxyService zxyService = new ZxyService();
	/*�����ѯ���������ĵ�λ���Ȼ��ֶ���*/
	private Zxy queryConditionZxy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxy_list);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("��ǰλ��--��λ���Ȼ����б�");
		} else {
			setTitle("���ã�" + username + "   ��ǰλ��---��λ���Ȼ����б�");
		}
		Bundle extras = this.getIntent().getExtras();
		if(extras != null) 
			queryConditionZxy = (Zxy)extras.getSerializable("queryConditionZxy");
		else if(!declare.getMc().equals("����Ա")) {
			queryConditionZxy = new Zxy();
			queryConditionZxy.setBname(declare.getMc());
			queryConditionZxy.setJfjd("");
			
		}
		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		list = getDatas();
		try {
			adapter = new ZxySimpleAdapter(this, list,
					R.layout.zxy_list_item,
					new String[] { "bname","jid","jfjd","jdsdate","jdedate","hjf" },
					new int[] { R.id.tv_bname,R.id.tv_jid,R.id.tv_jfjd,R.id.tv_jdsdate,R.id.tv_jdedate,R.id.tv_hjf,},lv);
			lv.setAdapter(adapter);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// ��ӳ������
		lv.setOnCreateContextMenuListener(zxyListItemListener);
		lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	int id = Integer.parseInt(list.get(arg2).get("id").toString());
            	Intent intent = new Intent();
            	intent.setClass(ZxyListActivity.this, ZxyDetailActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("id", id);
            	intent.putExtras(bundle);
            	startActivity(intent);
            }
        });
	}
	private OnCreateContextMenuListener zxyListItemListener = new OnCreateContextMenuListener() {
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			Declare declare = (Declare)ZxyListActivity.this.getApplication();
			if(declare.getMc().equals("����Ա")) {
				menu.add(0, 0, 0, "�༭��λ���Ȼ�����Ϣ"); 
				menu.add(0, 1, 0, "ɾ����λ���Ȼ�����Ϣ");
			}
			
		}
	};

	// �����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //�༭��λ���Ȼ�����Ϣ
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// ��ȡѡ����λ��
			int position = contextMenuInfo.position;
			// ��ȡid
			id = Integer.parseInt(list.get(position).get("id").toString());
			Intent intent = new Intent();
			intent.setClass(ZxyListActivity.this, ZxyEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			startActivity(intent);
			ZxyListActivity.this.finish();
		} else if (item.getItemId() == 1) {// ɾ����λ���Ȼ�����Ϣ
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
		Builder builder = new Builder(ZxyListActivity.this);
		builder.setMessage("ȷ��ɾ����");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = zxyService.DeleteZxy(id);
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
			/* ��ѯ��λ���Ȼ�����Ϣ */
			List<Zxy> zxyList = zxyService.QueryZxy(queryConditionZxy);
			for (int i = 0; i < zxyList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id",zxyList.get(i).getId());
				map.put("bname", zxyList.get(i).getBname());
				map.put("jid", zxyList.get(i).getJidString());
				map.put("jfjd", zxyList.get(i).getJfjd());
				map.put("jdsdate", zxyList.get(i).getJdsdate());
				map.put("jdedate", zxyList.get(i).getJdedate());
				map.put("hjf", zxyList.get(i).getHjf());
				list.add(map);
			}
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Declare declare = (Declare)ZxyListActivity.this.getApplication();
		if(declare.getMc().equals("����Ա")) {
			menu.add(0, 1, 1, "��ӵ�λ���Ȼ���");
			menu.add(0, 2, 2, "��ѯ��λ���Ȼ���");
			menu.add(0, 3, 3, "����������");
		} else {
			menu.add(0, 1, 1, "��ѯ��λ���Ȼ���");
			menu.add(0, 2, 2, "����������");
		}
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Declare declare = (Declare)ZxyListActivity.this.getApplication();
		if(declare.getMc().equals("����Ա")) {
			if (item.getItemId() == 1) {
				// ��ӵ�λ���Ȼ�����Ϣ
				Intent intent = new Intent();
				intent.setClass(ZxyListActivity.this, ZxyAddActivity.class);
				startActivity(intent);
				ZxyListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*��ѯ��λ���Ȼ�����Ϣ*/
				Intent intent = new Intent();
				intent.setClass(ZxyListActivity.this, ZxyQueryActivity.class);
				startActivity(intent);
				ZxyListActivity.this.finish();
			} else if (item.getItemId() == 3) {
				/*����������*/
				Intent intent = new Intent();
				intent.setClass(ZxyListActivity.this, MainMenuActivity.class);
				startActivity(intent);
				ZxyListActivity.this.finish();
			}
		} else {
			if (item.getItemId() == 1) {
				/*��ѯ��λ���Ȼ�����Ϣ*/
				Intent intent = new Intent();
				intent.setClass(ZxyListActivity.this, ZxyDepartQueryActivity.class);
				startActivity(intent);
				ZxyListActivity.this.finish();
			} else if (item.getItemId() == 2) {
				/*����������*/
				Intent intent = new Intent();
				intent.setClass(ZxyListActivity.this, MainMenuDepartmentActivity.class);
				startActivity(intent);
				ZxyListActivity.this.finish();
			}
		}
		
		return true; 
	}
}
