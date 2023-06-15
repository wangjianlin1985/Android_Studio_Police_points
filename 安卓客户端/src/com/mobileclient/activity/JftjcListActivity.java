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
	/* ������������ҵ���߼������ */
	JftjcService jftjcService = new JftjcService();
	/*�����ѯ���������Ļ�����������*/
	private Jftjc queryConditionJftjc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jftjc_list);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("��ǰλ��--���������б�");
		} else {
			setTitle("���ã�" + username + "   ��ǰλ��---���������б�");
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
		// ��ӳ������
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
			menu.add(0, 0, 0, "�༭����������Ϣ"); 
			menu.add(0, 1, 0, "ɾ������������Ϣ");
		}
	};

	// �����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //�༭����������Ϣ
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// ��ȡѡ����λ��
			int position = contextMenuInfo.position;
			// ��ȡ����id
			id = Integer.parseInt(list.get(position).get("id").toString());
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, JftjcEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", id);
			intent.putExtras(bundle);
			startActivity(intent);
			JftjcListActivity.this.finish();
		} else if (item.getItemId() == 1) {// ɾ������������Ϣ
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// ��ȡѡ����λ��
			int position = contextMenuInfo.position;
			// ��ȡ����id
			id = Integer.parseInt(list.get(position).get("id").toString());
			dialog();
		}
		return super.onContextItemSelected(item);
	}

	// ɾ��
	protected void dialog() {
		Builder builder = new Builder(JftjcListActivity.this);
		builder.setMessage("ȷ��ɾ����");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = jftjcService.DeleteJftjc(id);
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
			/* ��ѯ����������Ϣ */
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
		menu.add(0, 1, 1, "��ӻ�������");
		menu.add(0, 2, 2, "��ѯ��������");
		menu.add(0, 3, 3, "����������");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			// ��ӻ���������Ϣ
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, JftjcAddActivity.class);
			startActivity(intent);
			JftjcListActivity.this.finish();
		} else if (item.getItemId() == 2) {
			/*��ѯ����������Ϣ*/
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, JftjcQueryActivity.class);
			startActivity(intent);
			JftjcListActivity.this.finish();
		} else if (item.getItemId() == 3) {
			/*����������*/
			Intent intent = new Intent();
			intent.setClass(JftjcListActivity.this, MainMenuActivity.class);
			startActivity(intent);
			JftjcListActivity.this.finish();
		}
		return true; 
	}
}
