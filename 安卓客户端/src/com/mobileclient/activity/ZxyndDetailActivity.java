package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.Zxynd;
import com.mobileclient.service.ZxyndService;
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
public class ZxyndDetailActivity extends Activity {
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
	// ����������ȿؼ�
	private TextView TV_jfjd;
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
	/* Ҫ����ĵ�λ��Ȼ�����Ϣ */
	Zxynd zxynd = new Zxynd(); 
	/* ��λ��Ȼ��ֹ���ҵ���߼��� */
	private ZxyndService zxyndService = new ZxyndService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�鿴��λ��Ȼ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxynd_detail);
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_id = (TextView) findViewById(R.id.TV_id);
		TV_bid = (TextView) findViewById(R.id.TV_bid);
		TV_bname = (TextView) findViewById(R.id.TV_bname);
		TV_btypes = (TextView) findViewById(R.id.TV_btypes);
		TV_jfjd = (TextView) findViewById(R.id.TV_jfjd);
		TV_xsfajf = (TextView) findViewById(R.id.TV_xsfajf);
		TV_hmzfjf = (TextView) findViewById(R.id.TV_hmzfjf);
		TV_cpfkjf = (TextView) findViewById(R.id.TV_cpfkjf);
		TV_dwzsjf = (TextView) findViewById(R.id.TV_dwzsjf);
		TV_hjf = (TextView) findViewById(R.id.TV_hjf);
		Bundle extras = this.getIntent().getExtras();
		id = extras.getInt("id");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ZxyndDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    zxynd = zxyndService.GetZxynd(id); 
		this.TV_id.setText(zxynd.getId() + "");
		this.TV_bid.setText(zxynd.getBid() + "");
		this.TV_bname.setText(zxynd.getBname());
		this.TV_btypes.setText(zxynd.getBtypes() + "");
		this.TV_jfjd.setText(zxynd.getJfjd());
		this.TV_xsfajf.setText(zxynd.getXsfajf() + "");
		this.TV_hmzfjf.setText(zxynd.getHmzfjf() + "");
		this.TV_cpfkjf.setText(zxynd.getCpfkjf() + "");
		this.TV_dwzsjf.setText(zxynd.getDwzsjf() + "");
		this.TV_hjf.setText(zxynd.getHjf() + "");
	} 
}
