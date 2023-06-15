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
	// 声明返回按钮
	private Button btnReturn;
	// 声明id控件
	private TextView TV_id;
	// 声明部门id控件
	private TextView TV_bid;
	// 声明部门名称控件
	private TextView TV_bname;
	// 声明警员id控件
	private TextView TV_sid;
	// 声明警员姓名控件
	private TextView TV_sname;
	// 声明部门类型控件
	private TextView TV_btypes;
	// 声明积分条件ID控件
	private TextView TV_jid;
	// 声明积分季度控件
	private TextView TV_jfjd;
	// 声明积分季度开始时间控件
	private TextView TV_jdsdate;
	// 声明积分季度结束时间控件
	private TextView TV_jdedate;
	// 声明刑事发案积分控件
	private TextView TV_xsfajf;
	// 声明号码走访积分控件
	private TextView TV_hmzfjf;
	// 声明测评反馈积分控件
	private TextView TV_cpfkjf;
	// 声明单位重视积分控件
	private TextView TV_dwzsjf;
	// 声明合计分控件
	private TextView TV_hjf;
	/* 要保存的警员季度积分信息 */
	Zxymj zxymj = new Zxymj(); 
	/* 警员季度积分管理业务逻辑层 */
	private ZxymjService zxymjService = new ZxymjService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-查看警员季度积分详情");
		// 设置当前Activity界面布局
		setContentView(R.layout.zxymj_detail);
		// 通过findViewById方法实例化组件
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
	/* 初始化显示详情界面的数据 */
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
			this.TV_jid.setText("积分id错误"); 
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
