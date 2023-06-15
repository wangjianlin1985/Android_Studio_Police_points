package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.Jftjc;
import com.mobileclient.service.JftjcService;
import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
public class JftjcDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ��������id�ؼ�
	private TextView TV_id;
	// �������������ؼ�
	private TextView TV_jftj;
	// ���������ؼ�
	private TextView TV_fs;
	// �����������Ϳؼ�
	private TextView TV_typeid;
	// ����mtypeid�ؼ�
	private TextView TV_mtypeid;
	// ������ע�ؼ�
	private TextView TV_bz;
	/* Ҫ����Ļ���������Ϣ */
	Jftjc jftjc = new Jftjc(); 
	/* ������������ҵ���߼��� */
	private JftjcService jftjcService = new JftjcService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�鿴������������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.jftjc_detail);
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_id = (TextView) findViewById(R.id.TV_id);
		TV_jftj = (TextView) findViewById(R.id.TV_jftj);
		TV_fs = (TextView) findViewById(R.id.TV_fs);
		TV_typeid = (TextView) findViewById(R.id.TV_typeid);
		TV_mtypeid = (TextView) findViewById(R.id.TV_mtypeid);
		TV_bz = (TextView) findViewById(R.id.TV_bz);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				JftjcDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    jftjc = jftjcService.GetJftjc(id); 
		this.TV_id.setText(jftjc.getId() + "");
		this.TV_jftj.setText(jftjc.getJftj());
		this.TV_fs.setText(jftjc.getFs() + "");
		this.TV_typeid.setText(jftjc.getTypeid() + "");
		this.TV_mtypeid.setText(jftjc.getMtypeid() + "");
		this.TV_bz.setText(jftjc.getBz());
	} 
}
