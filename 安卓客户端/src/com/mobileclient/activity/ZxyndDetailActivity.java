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
	// 声明返回按钮
	private Button btnReturn;
	// 声明id控件
	private TextView TV_id;
	// 声明部门ID控件
	private TextView TV_bid;
	// 声明部门名称控件
	private TextView TV_bname;
	// 声明部门类型控件
	private TextView TV_btypes;
	// 声明积分年度控件
	private TextView TV_jfjd;
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
	/* 要保存的单位年度积分信息 */
	Zxynd zxynd = new Zxynd(); 
	/* 单位年度积分管理业务逻辑层 */
	private ZxyndService zxyndService = new ZxyndService();
	private int id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("手机客户端-查看单位年度积分详情");
		// 设置当前Activity界面布局
		setContentView(R.layout.zxynd_detail);
		// 通过findViewById方法实例化组件
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
	/* 初始化显示详情界面的数据 */
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
