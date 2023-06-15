package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.Zxymj;
import com.mobileclient.service.ZxymjService;
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
public class ZxymjAddActivity extends Activity {
	// ����ȷ����Ӱ�ť
	private Button btnAdd;
	// ��������id�����
	private EditText ET_bid;
	// �����������������
	private EditText ET_bname;
	// ������Աid�����
	private EditText ET_sid;
	// ������Ա���������
	private EditText ET_sname;
	// �����������������
	private EditText ET_btypes;
	// ������������ID�����
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
	/*Ҫ����ľ�Ա���Ȼ�����Ϣ*/
	Zxymj zxymj = new Zxymj();
	/*��Ա���Ȼ��ֹ���ҵ���߼���*/
	private ZxymjService zxymjService = new ZxymjService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-��Ӿ�Ա���Ȼ���");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxymj_add); 
		ET_bid = (EditText) findViewById(R.id.ET_bid);
		ET_bname = (EditText) findViewById(R.id.ET_bname);
		ET_sid = (EditText) findViewById(R.id.ET_sid);
		ET_sname = (EditText) findViewById(R.id.ET_sname);
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
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*������Ӿ�Ա���Ȼ��ְ�ť*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��֤��ȡ����id*/ 
					if(ET_bid.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "����id���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bid.setFocusable(true);
						ET_bid.requestFocus();
						return;	
					}
					zxymj.setBid(Integer.parseInt(ET_bid.getText().toString()));
					/*��֤��ȡ��������*/ 
					if(ET_bname.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_bname.setFocusable(true);
						ET_bname.requestFocus();
						return;	
					}
					zxymj.setBname(ET_bname.getText().toString());
					/*��֤��ȡ��Աid*/ 
					if(ET_sid.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "��Աid���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_sid.setFocusable(true);
						ET_sid.requestFocus();
						return;	
					}
					zxymj.setSid(Integer.parseInt(ET_sid.getText().toString()));
					/*��֤��ȡ��Ա����*/ 
					if(ET_sname.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "��Ա�������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_sname.setFocusable(true);
						ET_sname.requestFocus();
						return;	
					}
					zxymj.setSname(ET_sname.getText().toString());
					/*��֤��ȡ��������*/ 
					if(ET_btypes.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "�����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_btypes.setFocusable(true);
						ET_btypes.requestFocus();
						return;	
					}
					zxymj.setBtypes(Integer.parseInt(ET_btypes.getText().toString()));
					/*��֤��ȡ��������ID*/ 
					if(ET_jid.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "��������ID���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jid.setFocusable(true);
						ET_jid.requestFocus();
						return;	
					}
					zxymj.setJid(Integer.parseInt(ET_jid.getText().toString()));
					/*��֤��ȡ���ּ���*/ 
					if(ET_jfjd.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "���ּ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_jfjd.setFocusable(true);
						ET_jfjd.requestFocus();
						return;	
					}
					zxymj.setJfjd(ET_jfjd.getText().toString());
					/*��ȡ���ּ��ȿ�ʼʱ��*/
					Date jdsdate = new Date(dp_jdsdate.getYear()-1900,dp_jdsdate.getMonth(),dp_jdsdate.getDayOfMonth());
					zxymj.setJdsdate(new Timestamp(jdsdate.getTime()));
					/*��ȡ���ּ��Ƚ���ʱ��*/
					Date jdedate = new Date(dp_jdedate.getYear()-1900,dp_jdedate.getMonth(),dp_jdedate.getDayOfMonth());
					zxymj.setJdedate(new Timestamp(jdedate.getTime()));
					/*��֤��ȡ���·�������*/ 
					if(ET_xsfajf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "���·����������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_xsfajf.setFocusable(true);
						ET_xsfajf.requestFocus();
						return;	
					}
					zxymj.setXsfajf(Float.parseFloat(ET_xsfajf.getText().toString()));
					/*��֤��ȡ�����߷û���*/ 
					if(ET_hmzfjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "�����߷û������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_hmzfjf.setFocusable(true);
						ET_hmzfjf.requestFocus();
						return;	
					}
					zxymj.setHmzfjf(Float.parseFloat(ET_hmzfjf.getText().toString()));
					/*��֤��ȡ������������*/ 
					if(ET_cpfkjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "���������������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_cpfkjf.setFocusable(true);
						ET_cpfkjf.requestFocus();
						return;	
					}
					zxymj.setCpfkjf(Float.parseFloat(ET_cpfkjf.getText().toString()));
					/*��֤��ȡ��λ���ӻ���*/ 
					if(ET_dwzsjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "��λ���ӻ������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_dwzsjf.setFocusable(true);
						ET_dwzsjf.requestFocus();
						return;	
					}
					zxymj.setDwzsjf(Float.parseFloat(ET_dwzsjf.getText().toString()));
					/*��֤��ȡ�ϼƷ�*/ 
					if(ET_hjf.getText().toString().equals("")) {
						Toast.makeText(ZxymjAddActivity.this, "�ϼƷ����벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_hjf.setFocusable(true);
						ET_hjf.requestFocus();
						return;	
					}
					zxymj.setHjf(Float.parseFloat(ET_hjf.getText().toString()));
					/*����ҵ���߼����ϴ���Ա���Ȼ�����Ϣ*/
					ZxymjAddActivity.this.setTitle("�����ϴ���Ա���Ȼ�����Ϣ���Ե�...");
					String result = zxymjService.AddZxymj(zxymj);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					/*������ɺ󷵻ص���Ա���Ȼ��ֹ������*/ 
					Intent intent = new Intent();
					intent.setClass(ZxymjAddActivity.this, ZxymjListActivity.class);
					startActivity(intent); 
					ZxymjAddActivity.this.finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
