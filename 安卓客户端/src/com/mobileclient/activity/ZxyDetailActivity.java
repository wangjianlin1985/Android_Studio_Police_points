package com.mobileclient.activity;

import java.util.Date;

import com.mobileclient.domain.Jftjc;
import com.mobileclient.domain.Zxy;
import com.mobileclient.service.JftjcService;
import com.mobileclient.service.ZxyService;
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
public class ZxyDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ����id�ؼ�
	private TextView TV_id;
	// ��������ID�ؼ�
	private TextView TV_bid;
	// �����������ƿؼ�
	private TextView TV_bname;
	// �����������Ϳؼ�
	private TextView TV_btypes;
	// �������������ؼ�
	private TextView TV_jid;
	// �������ּ��ȿؼ�
	private TextView TV_jfjd;
	// �������ּ��ȿ�ʼʱ��ؼ�
	private TextView TV_jdsdate;
	// �������ּ��Ƚ���ʱ��ؼ�
	private TextView TV_jdedate;
	// �������·������ֿؼ�
	private TextView TV_xsfajf;
	// ���������߷û��ֿؼ�
	private TextView TV_hmzfjf;
	// ���������������ֿؼ�
	private TextView TV_cpfkjf;
	// ������λ���ӻ��ֿؼ�
	private TextView TV_dwzsjf;
	// �����ϼƷֿؼ�
	private TextView TV_hjf;
	/* Ҫ����ĵ�λ���Ȼ�����Ϣ */
	Zxy zxy = new Zxy(); 
	/* ��λ���Ȼ��ֹ���ҵ���߼��� */
	private ZxyService zxyService = new ZxyService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�鿴��λ���Ȼ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxy_detail);
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_id = (TextView) findViewById(R.id.TV_id);
		TV_bid = (TextView) findViewById(R.id.TV_bid);
		TV_bname = (TextView) findViewById(R.id.TV_bname);
		TV_btypes = (TextView) findViewById(R.id.TV_btypes);
		TV_jid = (TextView) findViewById(R.id.TV_jid);
		TV_jfjd = (TextView) findViewById(R.id.TV_jfjd);
		TV_jdsdate = (TextView) findViewById(R.id.TV_jdsdate);
		TV_jdedate = (TextView) findViewById(R.id.TV_jdedate);
		TV_xsfajf = (TextView) findViewById(R.id.TV_xsfajf);
		TV_hmzfjf = (TextView) findViewById(R.id.TV_hmzfjf);
		TV_cpfkjf = (TextView) findViewById(R.id.TV_cpfkjf);
		TV_dwzsjf = (TextView) findViewById(R.id.TV_dwzsjf);
		TV_hjf = (TextView) findViewById(R.id.TV_hjf);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ZxyDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    zxy = zxyService.GetZxy(id); 
		this.TV_id.setText(zxy.getId() + "");
		this.TV_bid.setText(zxy.getBid() + "");
		this.TV_bname.setText(zxy.getBname());
		this.TV_btypes.setText(zxy.getBtypes() + "");
		JftjcService jftjServ = new JftjcService();
		Jftjc jftjc = jftjServ.GetJftjc(zxy.getJid()); 
		if(jftjc == null)
			this.TV_jid.setText("id����");
		else 
			this.TV_jid.setText(jftjc.getJftj());
		//this.TV_jid.setText(zxy.getJid() + "");
		this.TV_jfjd.setText(zxy.getJfjd());
		Date jdsdate = new Date(zxy.getJdsdate().getTime());
		String jdsdateStr = (jdsdate.getYear() + 1900) + "-" + (jdsdate.getMonth()+1) + "-" + jdsdate.getDate();
		this.TV_jdsdate.setText(jdsdateStr);
		Date jdedate = new Date(zxy.getJdedate().getTime());
		String jdedateStr = (jdedate.getYear() + 1900) + "-" + (jdedate.getMonth()+1) + "-" + jdedate.getDate();
		this.TV_jdedate.setText(jdedateStr);
		this.TV_xsfajf.setText(zxy.getXsfajf() + "");
		this.TV_hmzfjf.setText(zxy.getHmzfjf() + "");
		this.TV_cpfkjf.setText(zxy.getCpfkjf() + "");
		this.TV_dwzsjf.setText(zxy.getDwzsjf() + "");
		this.TV_hjf.setText(zxy.getHjf() + "");
	} 
}
