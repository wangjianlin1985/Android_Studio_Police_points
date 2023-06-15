package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import com.mobileclient.domain.Zxy;
import com.mobileclient.service.ZxyService;
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

public class ZxyEditActivity extends Activity {
	// ����ȷ����Ӱ�ť
	private Button btnUpdate;
	// ����idTextView
	private TextView TV_id;
	// ��������ID�����
	private EditText ET_bid;
	// �����������������
	private EditText ET_bname;
	// �����������������
	private EditText ET_btypes;
	// �����������������
	private EditText ET_jid;
	// �������ּ��������
	private EditText ET_jfjd;
	// ������ּ��ȿ�ʼʱ��ؼ�
	private DatePicker dp_jdsdate;
	// ������ּ��Ƚ���ʱ��ؼ�
	private DatePicker dp_jdedate;
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
	/*Ҫ����ĵ�λ���Ȼ�����Ϣ*/
	Zxy zxy = new Zxy();
	/*��λ���Ȼ��ֹ���ҵ���߼���*/
	private ZxyService zxyService = new ZxyService();

	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�޸ĵ�λ���Ȼ���");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxy_edit); 
		TV_id = (TextView) findViewById(R.id.TV_id);
		ET_bid = (EditText) findViewById(R.id.ET_bid);
		ET_bname = (EditText) findViewById(R.id.ET_bname);
		ET_btypes = (EditText) findViewById(R.id.ET_btypes);
		ET_jid = (EditText) findViewById(R.id.ET_jid);
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		dp_jdsdate = (DatePicker)this.findViewById(R.id.dp_jdsdate);
		dp_jdedate = (DatePicker)this.findViewById(R.id.dp_jdedate);
		ET_xsfajf = (EditText) findViewById(R.id.ET_xsfajf);
		ET_hmzfjf = (EditText) findViewById(R.id.ET_hmzfjf);
		ET_cpfkjf = (EditText) findViewById(R.id.ET_cpfkjf);
		ET_dwzsjf = (EditText) findViewById(R.id.ET_dwzsjf);
		ET_hjf = (EditText) findViewById(R.id.ET_hjf);
		btnUpdate = (Button) findViewById(R.id.BtnUpdate);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		/*�����޸ĵ�λ���Ȼ��ְ�ť*/
		btnUpdate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��֤��ȡ����ID*/ 
					if(ET_bid.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "����ID���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bid.setFocusable(true);
						ET_bid.requestFocus();
						return;	
					}
					zxy.setBid(Integer.parseInt(ET_bid.getText().toString()));
					/*��֤��ȡ��������*/ 
					if(ET_bname.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bname.setFocusable(true);
						ET_bname.requestFocus();
						return;	
					}
					zxy.setBname(ET_bname.getText().toString());
					/*��֤��ȡ��������*/ 
					if(ET_btypes.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_btypes.setFocusable(true);
						ET_btypes.requestFocus();
						return;	
					}
					zxy.setBtypes(Integer.parseInt(ET_btypes.getText().toString()));
					/*��֤��ȡ��������*/ 
					if(ET_jid.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jid.setFocusable(true);
						ET_jid.requestFocus();
						return;	
					}
					zxy.setJid(Integer.parseInt(ET_jid.getText().toString()));
					/*��֤��ȡ���ּ���*/ 
					if(ET_jfjd.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "���ּ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jfjd.setFocusable(true);
						ET_jfjd.requestFocus();
						return;	
					}
					zxy.setJfjd(ET_jfjd.getText().toString());
					/*��ȡ��������*/
					Date jdsdate = new Date(dp_jdsdate.getYear()-1900,dp_jdsdate.getMonth(),dp_jdsdate.getDayOfMonth());
					zxy.setJdsdate(new Timestamp(jdsdate.getTime()));
					/*��ȡ��������*/
					Date jdedate = new Date(dp_jdedate.getYear()-1900,dp_jdedate.getMonth(),dp_jdedate.getDayOfMonth());
					zxy.setJdedate(new Timestamp(jdedate.getTime()));
					/*��֤��ȡ���·�������*/ 
					if(ET_xsfajf.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "���·����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_xsfajf.setFocusable(true);
						ET_xsfajf.requestFocus();
						return;	
					}
					zxy.setXsfajf(Float.parseFloat(ET_xsfajf.getText().toString()));
					/*��֤��ȡ�����߷û���*/ 
					if(ET_hmzfjf.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "�����߷û������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_hmzfjf.setFocusable(true);
						ET_hmzfjf.requestFocus();
						return;	
					}
					zxy.setHmzfjf(Float.parseFloat(ET_hmzfjf.getText().toString()));
					/*��֤��ȡ������������*/ 
					if(ET_cpfkjf.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "���������������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_cpfkjf.setFocusable(true);
						ET_cpfkjf.requestFocus();
						return;	
					}
					zxy.setCpfkjf(Float.parseFloat(ET_cpfkjf.getText().toString()));
					/*��֤��ȡ��λ���ӻ���*/ 
					if(ET_dwzsjf.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "��λ���ӻ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_dwzsjf.setFocusable(true);
						ET_dwzsjf.requestFocus();
						return;	
					}
					zxy.setDwzsjf(Float.parseFloat(ET_dwzsjf.getText().toString()));
					/*��֤��ȡ�ϼƷ�*/ 
					if(ET_hjf.getText().toString().equals("")) {
						Toast.makeText(ZxyEditActivity.this, "�ϼƷ����벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_hjf.setFocusable(true);
						ET_hjf.requestFocus();
						return;	
					}
					zxy.setHjf(Float.parseFloat(ET_hjf.getText().toString()));
					/*����ҵ���߼����ϴ���λ���Ȼ�����Ϣ*/
					ZxyEditActivity.this.setTitle("���ڸ��µ�λ���Ȼ�����Ϣ���Ե�...");
					String result = zxyService.UpdateZxy(zxy);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*������ɺ󷵻ص���λ���Ȼ��ֹ������*/ 
					Intent intent = new Intent();
					intent.setClass(ZxyEditActivity.this, ZxyListActivity.class);
					startActivity(intent); 
					ZxyEditActivity.this.finish();
				} catch (Exception e) {}
			}
		});
		initViewData();
	}

	/* ��ʼ����ʾ�༭��������� */
	private void initViewData() {
	    zxy = zxyService.GetZxy(id);
		this.TV_id.setText(id+"");
		this.ET_bid.setText(zxy.getBid() + "");
		this.ET_bname.setText(zxy.getBname());
		this.ET_btypes.setText(zxy.getBtypes() + "");
		this.ET_jid.setText(zxy.getJid() + "");
		this.ET_jfjd.setText(zxy.getJfjd());
		Date jdsdate = new Date(zxy.getJdsdate().getTime());
		this.dp_jdsdate.init(jdsdate.getYear() + 1900,jdsdate.getMonth(), jdsdate.getDate(), null);
		Date jdedate = new Date(zxy.getJdedate().getTime());
		this.dp_jdedate.init(jdedate.getYear() + 1900,jdedate.getMonth(), jdedate.getDate(), null);
		this.ET_xsfajf.setText(zxy.getXsfajf() + "");
		this.ET_hmzfjf.setText(zxy.getHmzfjf() + "");
		this.ET_cpfkjf.setText(zxy.getCpfkjf() + "");
		this.ET_dwzsjf.setText(zxy.getDwzsjf() + "");
		this.ET_hjf.setText(zxy.getHjf() + "");
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
