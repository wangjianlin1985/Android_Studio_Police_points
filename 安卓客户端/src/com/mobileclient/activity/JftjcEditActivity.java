package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import com.mobileclient.domain.Jftjc;
import com.mobileclient.service.JftjcService;
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
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.Toast;

public class JftjcEditActivity extends Activity {
	// ����ȷ����Ӱ�ť
	private Button btnUpdate;
	// ��������idTextView
	private TextView TV_id;
	// �����������������
	private EditText ET_jftj;
	// �������������
	private EditText ET_fs;
	// �����������������
	private EditText ET_typeid;
	// ����mtypeid�����
	private EditText ET_mtypeid;
	// ������ע�����
	private EditText ET_bz;
	protected String carmera_path;
	/*Ҫ����Ļ���������Ϣ*/
	Jftjc jftjc = new Jftjc();
	/*������������ҵ���߼���*/
	private JftjcService jftjcService = new JftjcService();

	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�޸Ļ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.jftjc_edit); 
		TV_id = (TextView) findViewById(R.id.TV_id);
		ET_jftj = (EditText) findViewById(R.id.ET_jftj);
		ET_fs = (EditText) findViewById(R.id.ET_fs);
		ET_typeid = (EditText) findViewById(R.id.ET_typeid);
		ET_mtypeid = (EditText) findViewById(R.id.ET_mtypeid);
		ET_bz = (EditText) findViewById(R.id.ET_bz);
		btnUpdate = (Button) findViewById(R.id.BtnUpdate);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		/*�����޸Ļ���������ť*/
		btnUpdate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��֤��ȡ��������*/ 
					if(ET_jftj.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jftj.setFocusable(true);
						ET_jftj.requestFocus();
						return;	
					}
					jftjc.setJftj(ET_jftj.getText().toString());
					/*��֤��ȡ����*/ 
					if(ET_fs.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "�������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_fs.setFocusable(true);
						ET_fs.requestFocus();
						return;	
					}
					jftjc.setFs(Float.parseFloat(ET_fs.getText().toString()));
					/*��֤��ȡ��������*/ 
					if(ET_typeid.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_typeid.setFocusable(true);
						ET_typeid.requestFocus();
						return;	
					}
					jftjc.setTypeid(Integer.parseInt(ET_typeid.getText().toString()));
					/*��֤��ȡmtypeid*/ 
					if(ET_mtypeid.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "mtypeid���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_mtypeid.setFocusable(true);
						ET_mtypeid.requestFocus();
						return;	
					}
					jftjc.setMtypeid(Integer.parseInt(ET_mtypeid.getText().toString()));
					/*��֤��ȡ��ע*/ 
					if(ET_bz.getText().toString().equals("")) {
						Toast.makeText(JftjcEditActivity.this, "��ע���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bz.setFocusable(true);
						ET_bz.requestFocus();
						return;	
					}
					jftjc.setBz(ET_bz.getText().toString());
					/*����ҵ���߼����ϴ�����������Ϣ*/
					JftjcEditActivity.this.setTitle("���ڸ��»���������Ϣ���Ե�...");
					String result = jftjcService.UpdateJftjc(jftjc);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*������ɺ󷵻ص����������������*/ 
					Intent intent = new Intent();
					intent.setClass(JftjcEditActivity.this, JftjcListActivity.class);
					startActivity(intent); 
					JftjcEditActivity.this.finish();
				} catch (Exception e) {}
			}
		});
		initViewData();
	}

	/* ��ʼ����ʾ�༭��������� */
	private void initViewData() {
	    jftjc = jftjcService.GetJftjc(id);
		this.TV_id.setText(id+"");
		this.ET_jftj.setText(jftjc.getJftj());
		this.ET_fs.setText(jftjc.getFs() + "");
		this.ET_typeid.setText(jftjc.getTypeid() + "");
		this.ET_mtypeid.setText(jftjc.getMtypeid() + "");
		this.ET_bz.setText(jftjc.getBz());
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
