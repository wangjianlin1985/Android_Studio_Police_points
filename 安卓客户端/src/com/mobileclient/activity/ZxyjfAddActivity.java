package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.Zxyjf;
import com.mobileclient.service.ZxyjfService;
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
public class ZxyjfAddActivity extends Activity {
	// ����ȷ����Ӱ�ť
	private Button btnAdd;
	// ��������id�����
	private EditText ET_bid;
	// �����������������
	private EditText ET_bname;
	// �����������������
	private EditText ET_btypes;
	// ������Աid�����
	private EditText ET_sid;
	// ������Ա���������
	private EditText ET_sname;
	// ������������ID�����
	private EditText ET_jid;
	// �����������������
	private EditText ET_jftj;
	// �������ּ��������
	private EditText ET_jfjd;
	// ����������ڿؼ�
	private DatePicker dp_jfdate;
	// ������ּ��ȿ�ʼʱ��ؼ�
	private DatePicker dp_jdsdate;
	// �������������
	private EditText ET_fs;
	// �������������
	private EditText ET_sl;
	// �������·������������
	private EditText ET_xsfajf;
	// ���������߷û��������
	private EditText ET_hmzfjf;
	// ���������������������
	private EditText ET_cpfkjf;
	// ������λ���ӻ��������
	private EditText ET_dwzsjf;
	protected String carmera_path;
	/*Ҫ����ĵ�λ�񾯻�����Ϣ*/
	Zxyjf zxyjf = new Zxyjf();
	/*��λ�񾯻��ֹ���ҵ���߼���*/
	private ZxyjfService zxyjfService = new ZxyjfService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-��ӵ�λ�񾯻���");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxyjf_add); 
		ET_bid = (EditText) findViewById(R.id.ET_bid);
		ET_bname = (EditText) findViewById(R.id.ET_bname);
		ET_btypes = (EditText) findViewById(R.id.ET_btypes);
		ET_sid = (EditText) findViewById(R.id.ET_sid);
		ET_sname = (EditText) findViewById(R.id.ET_sname);
		ET_jid = (EditText) findViewById(R.id.ET_jid);
		ET_jftj = (EditText) findViewById(R.id.ET_jftj);
		ET_jfjd = (EditText) findViewById(R.id.ET_jfjd);
		dp_jfdate = (DatePicker)this.findViewById(R.id.dp_jfdate);
		dp_jdsdate = (DatePicker)this.findViewById(R.id.dp_jdsdate);
		ET_fs = (EditText) findViewById(R.id.ET_fs);
		ET_sl = (EditText) findViewById(R.id.ET_sl);
		ET_xsfajf = (EditText) findViewById(R.id.ET_xsfajf);
		ET_hmzfjf = (EditText) findViewById(R.id.ET_hmzfjf);
		ET_cpfkjf = (EditText) findViewById(R.id.ET_cpfkjf);
		ET_dwzsjf = (EditText) findViewById(R.id.ET_dwzsjf);
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*������ӵ�λ�񾯻��ְ�ť*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��֤��ȡ����id*/ 
					if(ET_bid.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "����id���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bid.setFocusable(true);
						ET_bid.requestFocus();
						return;	
					}
					zxyjf.setBid(Integer.parseInt(ET_bid.getText().toString()));
					/*��֤��ȡ��������*/ 
					if(ET_bname.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bname.setFocusable(true);
						ET_bname.requestFocus();
						return;	
					}
					zxyjf.setBname(ET_bname.getText().toString());
					/*��֤��ȡ��������*/ 
					if(ET_btypes.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_btypes.setFocusable(true);
						ET_btypes.requestFocus();
						return;	
					}
					zxyjf.setBtypes(Integer.parseInt(ET_btypes.getText().toString()));
					/*��֤��ȡ��Աid*/ 
					if(ET_sid.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "��Աid���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_sid.setFocusable(true);
						ET_sid.requestFocus();
						return;	
					}
					zxyjf.setSid(ET_sid.getText().toString());
					/*��֤��ȡ��Ա����*/ 
					if(ET_sname.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "��Ա�������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_sname.setFocusable(true);
						ET_sname.requestFocus();
						return;	
					}
					zxyjf.setSname(ET_sname.getText().toString());
					/*��֤��ȡ��������ID*/ 
					if(ET_jid.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "��������ID���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jid.setFocusable(true);
						ET_jid.requestFocus();
						return;	
					}
					zxyjf.setJid(Integer.parseInt(ET_jid.getText().toString()));
					/*��֤��ȡ��������*/ 
					if(ET_jftj.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jftj.setFocusable(true);
						ET_jftj.requestFocus();
						return;	
					}
					zxyjf.setJftj(ET_jftj.getText().toString());
					/*��֤��ȡ���ּ���*/ 
					if(ET_jfjd.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "���ּ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jfjd.setFocusable(true);
						ET_jfjd.requestFocus();
						return;	
					}
					zxyjf.setJfjd(ET_jfjd.getText().toString());
					/*��ȡ��������*/
					Date jfdate = new Date(dp_jfdate.getYear()-1900,dp_jfdate.getMonth(),dp_jfdate.getDayOfMonth());
					zxyjf.setJfdate(new Timestamp(jfdate.getTime()));
					/*��ȡ���ּ��ȿ�ʼʱ��*/
					Date jdsdate = new Date(dp_jdsdate.getYear()-1900,dp_jdsdate.getMonth(),dp_jdsdate.getDayOfMonth());
					zxyjf.setJdsdate(new Timestamp(jdsdate.getTime()));
					/*��֤��ȡ����*/ 
					if(ET_fs.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "�������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_fs.setFocusable(true);
						ET_fs.requestFocus();
						return;	
					}
					zxyjf.setFs(Float.parseFloat(ET_fs.getText().toString()));
					/*��֤��ȡ����*/ 
					if(ET_sl.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "�������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_sl.setFocusable(true);
						ET_sl.requestFocus();
						return;	
					}
					zxyjf.setSl(Integer.parseInt(ET_sl.getText().toString()));
					/*��֤��ȡ���·�������*/ 
					if(ET_xsfajf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "���·����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_xsfajf.setFocusable(true);
						ET_xsfajf.requestFocus();
						return;	
					}
					zxyjf.setXsfajf(Float.parseFloat(ET_xsfajf.getText().toString()));
					/*��֤��ȡ�����߷û���*/ 
					if(ET_hmzfjf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "�����߷û������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_hmzfjf.setFocusable(true);
						ET_hmzfjf.requestFocus();
						return;	
					}
					zxyjf.setHmzfjf(Float.parseFloat(ET_hmzfjf.getText().toString()));
					/*��֤��ȡ������������*/ 
					if(ET_cpfkjf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "���������������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_cpfkjf.setFocusable(true);
						ET_cpfkjf.requestFocus();
						return;	
					}
					zxyjf.setCpfkjf(Float.parseFloat(ET_cpfkjf.getText().toString()));
					/*��֤��ȡ��λ���ӻ���*/ 
					if(ET_dwzsjf.getText().toString().equals("")) {
						Toast.makeText(ZxyjfAddActivity.this, "��λ���ӻ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_dwzsjf.setFocusable(true);
						ET_dwzsjf.requestFocus();
						return;	
					}
					zxyjf.setDwzsjf(Float.parseFloat(ET_dwzsjf.getText().toString()));
					/*����ҵ���߼����ϴ���λ�񾯻�����Ϣ*/
					ZxyjfAddActivity.this.setTitle("�����ϴ���λ�񾯻�����Ϣ���Ե�...");
					String result = zxyjfService.AddZxyjf(zxyjf);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*������ɺ󷵻ص���λ�񾯻��ֹ������*/ 
					Intent intent = new Intent();
					intent.setClass(ZxyjfAddActivity.this, ZxyjfListActivity.class);
					startActivity(intent); 
					ZxyjfAddActivity.this.finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
