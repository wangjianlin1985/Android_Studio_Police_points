package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.Zxyjf;
import com.mobileclient.service.ZxyjfService;
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
public class ZxyjfDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ����id�ؼ�
	private TextView TV_id;
	// ��������id�ؼ�
	private TextView TV_bid;
	// �����������ƿؼ�
	private TextView TV_bname;
	// �����������Ϳؼ�
	private TextView TV_btypes;
	// ������Աid�ؼ�
	private TextView TV_sid;
	// ������Ա�����ؼ�
	private TextView TV_sname;
	// ������������ID�ؼ�
	private TextView TV_jid;
	// �������������ؼ�
	private TextView TV_jftj;
	// �������ּ��ȿؼ�
	private TextView TV_jfjd;
	// �����������ڿؼ�
	private TextView TV_jfdate;
	// �������ּ��ȿ�ʼʱ��ؼ�
	private TextView TV_jdsdate;
	// ���������ؼ�
	private TextView TV_fs;
	// ���������ؼ�
	private TextView TV_sl;
	// �������·������ֿؼ�
	private TextView TV_xsfajf;
	// ���������߷û��ֿؼ�
	private TextView TV_hmzfjf;
	// ���������������ֿؼ�
	private TextView TV_cpfkjf;
	// ������λ���ӻ��ֿؼ�
	private TextView TV_dwzsjf;
	/* Ҫ����ĵ�λ�񾯻�����Ϣ */
	Zxyjf zxyjf = new Zxyjf(); 
	/* ��λ�񾯻��ֹ���ҵ���߼��� */
	private ZxyjfService zxyjfService = new ZxyjfService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�鿴��λ�񾯻�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxyjf_detail);
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_id = (TextView) findViewById(R.id.TV_id);
		TV_bid = (TextView) findViewById(R.id.TV_bid);
		TV_bname = (TextView) findViewById(R.id.TV_bname);
		TV_btypes = (TextView) findViewById(R.id.TV_btypes);
		TV_sid = (TextView) findViewById(R.id.TV_sid);
		TV_sname = (TextView) findViewById(R.id.TV_sname);
		TV_jid = (TextView) findViewById(R.id.TV_jid);
		TV_jftj = (TextView) findViewById(R.id.TV_jftj);
		TV_jfjd = (TextView) findViewById(R.id.TV_jfjd);
		TV_jfdate = (TextView) findViewById(R.id.TV_jfdate);
		TV_jdsdate = (TextView) findViewById(R.id.TV_jdsdate);
		TV_fs = (TextView) findViewById(R.id.TV_fs);
		TV_sl = (TextView) findViewById(R.id.TV_sl);
		TV_xsfajf = (TextView) findViewById(R.id.TV_xsfajf);
		TV_hmzfjf = (TextView) findViewById(R.id.TV_hmzfjf);
		TV_cpfkjf = (TextView) findViewById(R.id.TV_cpfkjf);
		TV_dwzsjf = (TextView) findViewById(R.id.TV_dwzsjf);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ZxyjfDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    zxyjf = zxyjfService.GetZxyjf(id); 
		this.TV_id.setText(zxyjf.getId() + "");
		this.TV_bid.setText(zxyjf.getBid() + "");
		this.TV_bname.setText(zxyjf.getBname());
		this.TV_btypes.setText(zxyjf.getBtypes() + "");
		this.TV_sid.setText(zxyjf.getSid());
		this.TV_sname.setText(zxyjf.getSname());
		this.TV_jid.setText(zxyjf.getJid() + "");
		this.TV_jftj.setText(zxyjf.getJftj());
		this.TV_jfjd.setText(zxyjf.getJfjd());
		Date jfdate = new Date(zxyjf.getJfdate().getTime());
		String jfdateStr = (jfdate.getYear() + 1900) + "-" + (jfdate.getMonth()+1) + "-" + jfdate.getDate();
		this.TV_jfdate.setText(jfdateStr);
		Date jdsdate = new Date(zxyjf.getJdsdate().getTime());
		String jdsdateStr = (jdsdate.getYear() + 1900) + "-" + (jdsdate.getMonth()+1) + "-" + jdsdate.getDate();
		this.TV_jdsdate.setText(jdsdateStr);
		this.TV_fs.setText(zxyjf.getFs() + "");
		this.TV_sl.setText(zxyjf.getSl() + "");
		this.TV_xsfajf.setText(zxyjf.getXsfajf() + "");
		this.TV_hmzfjf.setText(zxyjf.getHmzfjf() + "");
		this.TV_cpfkjf.setText(zxyjf.getCpfkjf() + "");
		this.TV_dwzsjf.setText(zxyjf.getDwzsjf() + "");
	} 
}
