package com.mobileclient.activity;

import java.util.Date;

import com.mobileclient.domain.Jftjc;
import com.mobileclient.domain.Zxymj;
import com.mobileclient.service.JftjcService;
import com.mobileclient.service.ZxymjService;
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
public class ZxymjDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ����id�ؼ�
	private TextView TV_id;
	// ��������id�ؼ�
	private TextView TV_bid;
	// �����������ƿؼ�
	private TextView TV_bname;
	// ������Աid�ؼ�
	private TextView TV_sid;
	// ������Ա�����ؼ�
	private TextView TV_sname;
	// �����������Ϳؼ�
	private TextView TV_btypes;
	// ������������ID�ؼ�
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
	/* Ҫ����ľ�Ա���Ȼ�����Ϣ */
	Zxymj zxymj = new Zxymj(); 
	/* ��Ա���Ȼ��ֹ���ҵ���߼��� */
	private ZxymjService zxymjService = new ZxymjService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�鿴��Ա���Ȼ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxymj_detail);
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_id = (TextView) findViewById(R.id.TV_id);
		TV_bid = (TextView) findViewById(R.id.TV_bid);
		TV_bname = (TextView) findViewById(R.id.TV_bname);
		TV_sid = (TextView) findViewById(R.id.TV_sid);
		TV_sname = (TextView) findViewById(R.id.TV_sname);
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
				ZxymjDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    zxymj = zxymjService.GetZxymj(id); 
		this.TV_id.setText(zxymj.getId() + "");
		this.TV_bid.setText(zxymj.getBid() + "");
		this.TV_bname.setText(zxymj.getBname());
		this.TV_sid.setText(zxymj.getSid() + "");
		this.TV_sname.setText(zxymj.getSname());
		this.TV_btypes.setText(zxymj.getBtypes() + "");
		
		JftjcService JftjcServ = new JftjcService();
		Jftjc jftjc = JftjcServ.GetJftjc(zxymj.getJid());
		if(jftjc == null)
			this.TV_jid.setText("����id����"); 
		else 
			this.TV_jid.setText(jftjc.getJftj()); 
		this.TV_jfjd.setText(zxymj.getJfjd());
		Date jdsdate = new Date(zxymj.getJdsdate().getTime());
		String jdsdateStr = (jdsdate.getYear() + 1900) + "-" + (jdsdate.getMonth()+1) + "-" + jdsdate.getDate();
		this.TV_jdsdate.setText(jdsdateStr);
		Date jdedate = new Date(zxymj.getJdedate().getTime());
		String jdedateStr = (jdedate.getYear() + 1900) + "-" + (jdedate.getMonth()+1) + "-" + jdedate.getDate();
		this.TV_jdedate.setText(jdedateStr);
		this.TV_xsfajf.setText(zxymj.getXsfajf() + "");
		this.TV_hmzfjf.setText(zxymj.getHmzfjf() + "");
		this.TV_cpfkjf.setText(zxymj.getCpfkjf() + "");
		this.TV_dwzsjf.setText(zxymj.getDwzsjf() + "");
		this.TV_hjf.setText(zxymj.getHjf() + "");
	} 
}
