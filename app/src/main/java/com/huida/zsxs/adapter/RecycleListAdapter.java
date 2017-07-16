package com.huida.zsxs.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huida.zsxs.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liling on 2017/7/15.
 */

public class RecycleListAdapter extends BaseAdapter {
    private Activity  mActivity;
     private ArrayList<String>  mdata;
    private ViewHolder holder;
    private  HashMap<Integer,Boolean>  hashmap;



    public RecycleListAdapter(Activity mActivity, ArrayList<String> data) {
        this.mActivity = mActivity;
        this.mdata = data;


    }



    public HashMap<Integer,Boolean> isSelected(int i){


        return hashmap;
    }
    public  void  setSelected(int i){
        hashmap.put(i,true);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        holder = new ViewHolder();
        if (view==null){
            view=View.inflate(mActivity, R.layout.list_item_layout,null);
            holder.iv_item_icon= (ImageView) view.findViewById(R.id.iv_item_icon);
            holder.tv_item_title= (TextView) view.findViewById(R.id.tv_item_title);
            holder.tv_item_sectitle= (TextView) view.findViewById(R.id.tv_item_Sectitle);
            holder.tv_item_jifen= (TextView) view.findViewById(R.id.tv__item_jifen);
            holder.tv__item_dianjiliang= (TextView) view.findViewById(R.id.tv_item_dianjiliang);
            holder.cb_sel= (CheckBox) view.findViewById(R.id.cb_sel);
            view.setTag(holder);

        }else {
            holder= (ViewHolder) view.getTag();

        }
        //给各个控件赋值



        return view;
    }
    class ViewHolder{
        ImageView iv_item_icon ;
        TextView tv_item_title ;
        TextView tv_item_sectitle ;
        TextView tv_item_jifen ;
        TextView tv__item_dianjiliang;
        CheckBox cb_sel;
    }
}
