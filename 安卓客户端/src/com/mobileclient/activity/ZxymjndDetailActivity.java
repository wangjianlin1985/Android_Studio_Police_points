package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.Zxymjnd;
import com.mobileclient.service.ZxymjndService;
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
public class ZxymjndDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ����id�ؼ�
	private TextView TV_id;
	// ��������ID�ؼ�
	private TextView TV_bid;
	// �����������ƿؼ�
	private TextView TV_bname;
	// ������Աid�ؼ�
	private TextView TV_sid;
	// ������Ա���ƿؼ�
	private TextView TV_sname;
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
	/* Ҫ����ľ�Ա��Ȼ�����Ϣ */
	Zxymjnd zxymjnd = new Zxymjnd(); 
	/* ��Ա��Ȼ��ֹ���ҵ���߼��� */
	private ZxymjndService zxymjndService = new ZxymjndService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("�ֻ��ͻ���-�鿴��Ա��Ȼ�������");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.zxymjnd_detail);
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_id = (TextView) findViewById(R.id.TV_id);
		TV_bid = (TextView) findViewById(R.id.TV_bid);
		TV_bname = (TextView) findViewById(R.id.TV_bname);
		TV_sid = (TextView) findViewById(R.id.TV_sid);
		TV_sname = (TextView) findViewById(R.id.TV_sname);
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
				ZxymjndDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    zxymjnd = zxymjndService.GetZxymjnd(id); 
		this.TV_id.setText(zxymjnd.getId() + "");
		this.TV_bid.setText(zxymjnd.getBid() + "");
		this.TV_bname.setText(zxymjnd.getBname());
		this.TV_sid.setText(zxymjnd.getSid() + "");
		this.TV_sname.setText(zxymjnd.getSname());
		this.TV_btypes.setText(zxymjnd.getBtypes() + "");
		this.TV_jfjd.setText(zxymjnd.getJfjd());
		this.TV_xsfajf.setText(zxymjnd.getXsfajf() + "");
		this.TV_hmzfjf.setText(zxymjnd.getHmzfjf() + "");
		this.TV_cpfkjf.setText(zxymjnd.getCpfkjf() + "");
		this.TV_dwzsjf.setText(zxymjnd.getDwzsjf() + "");
		this.TV_hjf.setText(zxymjnd.getHjf() + "");
	} 
}
