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

public class ZxyjfSimpleAdapter extends SimpleAdapter { 
	/*需要绑定的控件资源id*/
    private int[] mTo;
    /*map集合关键字数组*/
    private String[] mFrom;
/*需要绑定的数据*/
    private List<? extends Map<String, ?>> mData; 

    private LayoutInflater mInflater;
    Context context = null;

    private ListView mListView;
    //图片异步缓存加载类,带内存缓存和文件缓存
    private SyncImageLoader syncImageLoader;

    public ZxyjfSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,ListView listView) { 
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
	  ///*第一次装载这个view时=null,就新建一个调用inflate渲染一个view*/
	  if (convertView == null) convertView = mInflater.inflate(R.layout.zxyjf_list_item, null);
	  convertView.setTag("listViewTAG" + position);
	  holder = new ViewHolder(); 
	  /*绑定该view各个控件*/
	  holder.tv_id = (TextView)convertView.findViewById(R.id.tv_id);
	  holder.tv_bname = (TextView)convertView.findViewById(R.id.tv_bname);
	  holder.tv_btypes = (TextView)convertView.findViewById(R.id.tv_btypes);
	  holder.tv_sname = (TextView)convertView.findViewById(R.id.tv_sname);
	  holder.tv_jftj = (TextView)convertView.findViewById(R.id.tv_jftj);
	  holder.tv_jfjd = (TextView)convertView.findViewById(R.id.tv_jfjd);
	  holder.tv_jfdate = (TextView)convertView.findViewById(R.id.tv_jfdate);
	  /*设置各个控件的展示内容*/
	  holder.tv_id.setText("id：" + mData.get(position).get("id").toString());
	  holder.tv_bname.setText("部门名称：" + mData.get(position).get("bname").toString());
	  holder.tv_btypes.setText("部门类型：" + mData.get(position).get("btypes").toString());
	  holder.tv_sname.setText("警员姓名：" + mData.get(position).get("sname").toString());
	  holder.tv_jftj.setText("积分条件：" + mData.get(position).get("jftj").toString());
	  holder.tv_jfjd.setText("积分季度：" + mData.get(position).get("jfjd").toString());
	  try {holder.tv_jfdate.setText("积分日期：" + mData.get(position).get("jfdate").toString().substring(0, 10));} catch(Exception ex){}
	  /*返回修改好的view*/
	  return convertView; 
    } 

    static class ViewHolder{ 
    	TextView tv_id;
    	TextView tv_bname;
    	TextView tv_btypes;
    	TextView tv_sname;
    	TextView tv_jftj;
    	TextView tv_jfjd;
    	TextView tv_jfdate;
    }
} 
