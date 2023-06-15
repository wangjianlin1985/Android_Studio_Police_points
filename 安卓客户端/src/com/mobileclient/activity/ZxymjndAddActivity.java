package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.Zxymjnd;
import com.mobileclient.service.ZxymjndService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class ZxymjndAddActivity extends Activity {
	// ����ȷ����Ӱ�ť
	private Button btnAdd;
	// ��������ID�����
	private EditText ET_bid;
	// �����������������
	private EditText ET_bname;
	// ������Աid�����
	private EditText ET_sid;
	// ������Ա���������
	private EditText ET_sname;
	// �����������������
	private EditText ET_btypes;
	// ����������������
	private EditText ET_jfjd;
	// �������·������������
	private EditText ET_xsfajf;
	// ���������߷û��������
	private EditText ET_hmzfjf;
	// ���������������������
	private EditText ET_cpfkjf;
	// ������λ���ӻ��������
	private EditText ET_dwzsjf;
	// �����ϼƷ������
	private EditText ET_hjf;
	protected String carmera_path;
	/*Ҫ����ľ�Ա��Ȼ�����Ϣ*/
	Zxymjnd zxymjnd = new Zxymjnd();
	/*��Ա��Ȼ��ֹ���ҵ���߼���*/
	private ZxymjndService zxymjndService = new ZxymjndService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-��Ӿ�Ա��Ȼ���");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxymjnd_add); 
		ET_bid = (EditText) findViewById(R.id.ET_bid);
		ET_bname = (EditText) findViewById(R.id.ET_bname);
		ET_sid = (EditText) findViewById(R.id.ET_sid);
		ET_sname = (EditText) findViewById(R.id.ET_sname);
		ET_btypes = (EditText) findViewById(R.id.ET_btypes);
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		ET_xsfajf = (EditText) findViewById(R.id.ET_xsfajf);
		ET_hmzfjf = (EditText) findViewById(R.id.ET_hmzfjf);
		ET_cpfkjf = (EditText) findViewById(R.id.ET_cpfkjf);
		ET_dwzsjf = (EditText) findViewById(R.id.ET_dwzsjf);
		ET_hjf = (EditText) findViewById(R.id.ET_hjf);
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*������Ӿ�Ա��Ȼ��ְ�ť*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��֤��ȡ����ID*/ 
					if(ET_bid.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "����ID���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bid.setFocusable(true);
						ET_bid.requestFocus();
						return;	
					}
					zxymjnd.setBid(Integer.parseInt(ET_bid.getText().toString()));
					/*��֤��ȡ��������*/ 
					if(ET_bname.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bname.setFocusable(true);
						ET_bname.requestFocus();
						return;	
					}
					zxymjnd.setBname(ET_bname.getText().toString());
					/*��֤��ȡ��Աid*/ 
					if(ET_sid.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "��Աid���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_sid.setFocusable(true);
						ET_sid.requestFocus();
						return;	
					}
					zxymjnd.setSid(Integer.parseInt(ET_sid.getText().toString()));
					/*��֤��ȡ��Ա����*/ 
					if(ET_sname.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "��Ա�������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_sname.setFocusable(true);
						ET_sname.requestFocus();
						return;	
					}
					zxymjnd.setSname(ET_sname.getText().toString());
					/*��֤��ȡ��������*/ 
					if(ET_btypes.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_btypes.setFocusable(true);
						ET_btypes.requestFocus();
						return;	
					}
					zxymjnd.setBtypes(Integer.parseInt(ET_btypes.getText().toString()));
					/*��֤��ȡ�������*/ 
					if(ET_jfjd.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jfjd.setFocusable(true);
						ET_jfjd.requestFocus();
						return;	
					}
					zxymjnd.setJfjd(ET_jfjd.getText().toString());
					/*��֤��ȡ���·�������*/ 
					if(ET_xsfajf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "���·����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_xsfajf.setFocusable(true);
						ET_xsfajf.requestFocus();
						return;	
					}
					zxymjnd.setXsfajf(Float.parseFloat(ET_xsfajf.getText().toString()));
					/*��֤��ȡ�����߷û���*/ 
					if(ET_hmzfjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "�����߷û������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_hmzfjf.setFocusable(true);
						ET_hmzfjf.requestFocus();
						return;	
					}
					zxymjnd.setHmzfjf(Float.parseFloat(ET_hmzfjf.getText().toString()));
					/*��֤��ȡ������������*/ 
					if(ET_cpfkjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "���������������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_cpfkjf.setFocusable(true);
						ET_cpfkjf.requestFocus();
						return;	
					}
					zxymjnd.setCpfkjf(Float.parseFloat(ET_cpfkjf.getText().toString()));
					/*��֤��ȡ��λ���ӻ���*/ 
					if(ET_dwzsjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "��λ���ӻ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_dwzsjf.setFocusable(true);
						ET_dwzsjf.requestFocus();
						return;	
					}
					zxymjnd.setDwzsjf(Float.parseFloat(ET_dwzsjf.getText().toString()));
					/*��֤��ȡ�ϼƷ�*/ 
					if(ET_hjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjndAddActivity.this, "�ϼƷ����벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_hjf.setFocusable(true);
						ET_hjf.requestFocus();
						return;	
					}
					zxymjnd.setHjf(Float.parseFloat(ET_hjf.getText().toString()));
					/*����ҵ���߼����ϴ���Ա��Ȼ�����Ϣ*/
					ZxymjndAddActivity.this.setTitle("�����ϴ���Ա��Ȼ�����Ϣ���Ե�...");
					String result = zxymjndService.AddZxymjnd(zxymjnd);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*������ɺ󷵻ص���Ա��Ȼ��ֹ������*/ 
					Intent intent = new Intent();
					intent.setClass(ZxymjndAddActivity.this, ZxymjndListActivity.class);
					startActivity(intent); 
					ZxymjndAddActivity.this.finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
