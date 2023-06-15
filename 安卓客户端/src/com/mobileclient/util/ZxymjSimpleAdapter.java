package com.mobileclient.util;

import java.util.List;  
import java.util.Map;

import com.mobileclient.activity.R;
import com.mobileclient.imgCache.ImageLoadListener;
import com.mobileclient.imgCache.ListViewOnScrollListener;
import com.mobileclient.imgCache.SyncImageLoader;
import android.content.Context;
import android.view.LayoutInflater; 
import android.view.View;
import android.view.ViewGroup;  
import android.widget.ImageView; 
import android.widget.ListView;
import android.widget.SimpleAdapter; 
import android.widget.TextView; 

public class ZxymjSimpleAdapter extends SimpleAdapter { 
	/*��Ҫ�󶨵Ŀؼ���Դid*/
    private int[] mTo;
    /*map���Ϲؼ�������*/
    private String[] mFrom;
/*��Ҫ�󶨵�����*/
    private List<? extends Map<String, ?>> mData; 

    private LayoutInflater mInflater;
    Context context = null;

    private ListView mListView;
    //ͼƬ�첽���������,���ڴ滺����ļ�����
    private SyncImageLoader syncImageLoader;

    public ZxymjSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,ListView listView) { 
        super(context, data, resource, from, to); 
        mTo = to; 
        mFrom = from; 
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context= context;
        mListView = listView; 
        syncImageLoader = SyncImageLoader.getInstance();
        ListViewOnScrollListener onScrollListener = new ListViewOnScrollListener(syncImageLoader,listView,getCount());
        mListView.setOnScrollListener(onScrollListener);
    } 

  public View getView(int position, View convertView, ViewGroup parent) { 
	  ViewHolder holder = null;
	  ///*��һ��װ�����viewʱ=null,���½�һ������inflate��Ⱦһ��view*/
	  if (convertView == null) convertView = mInflater.inflate(R.layout.zxymj_list_item, null);
	  convertView.setTag("listViewTAG" + position);
	  holder = new ViewHolder(); 
	  /*�󶨸�view�����ؼ�*/
	  holder.tv_bname = (TextView)convertView.findViewById(R.id.tv_bname);
	  holder.tv_sname = (TextView)convertView.findViewById(R.id.tv_sname);
	  holder.tv_jfjd = (TextView)convertView.findViewById(R.id.tv_jfjd);
	  holder.tv_jdsdate = (TextView)convertView.findViewById(R.id.tv_jdsdate);
	  holder.tv_jdedate = (TextView)convertView.findViewById(R.id.tv_jdedate);
	  holder.tv_hjf = (TextView)convertView.findViewById(R.id.tv_hjf);
	  /*���ø����ؼ���չʾ����*/
	  holder.tv_bname.setText("�������ƣ�" + mData.get(position).get("bname").toString());
	  holder.tv_sname.setText("��Ա������" + mData.get(position).get("sname").toString());
	  holder.tv_jfjd.setText("���ּ��ȣ�" + mData.get(position).get("jfjd").toString());
	  try {holder.tv_jdsdate.setText("���ּ��ȿ�ʼʱ�䣺" + mData.get(position).get("jdsdate").toString().substring(0, 10));} catch(Exception ex){}
	  try {holder.tv_jdedate.setText("���ּ��Ƚ���ʱ�䣺" + mData.get(position).get("jdedate").toString().substring(0, 10));} catch(Exception ex){}
	  holder.tv_hjf.setText("�ϼƷ֣�" + mData.get(position).get("hjf").toString());
	  /*�����޸ĺõ�view*/
	  return convertView; 
    } 

    static class ViewHolder{ 
    	TextView tv_bname;
    	TextView tv_sname;
    	TextView tv_jfjd;
    	TextView tv_jdsdate;
    	TextView tv_jdedate;
    	TextView tv_hjf;
    }
} 
