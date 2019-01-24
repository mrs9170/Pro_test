package com.tilda.weatherapptest3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.tilda.weatherapptest3.R;
import com.tilda.weatherapptest3.data_access_objects.CitiesDAO;
import com.tilda.weatherapptest3.data_access_objects.ProvincesDAO;
import com.tilda.weatherapptest3.models.entity.City;
import com.tilda.weatherapptest3.models.entity.Province;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlacesExpListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Province> provinces;
    private Map<Province, List<City>> cities;
    private ProvincesDAO provincesDAO;
    private CitiesDAO citiesDAO;

    public PlacesExpListAdapter(Context context) {
        this.context = context;
        provinces = new LinkedList<>();
        cities = new LinkedHashMap<>();
        provincesDAO = new ProvincesDAO(context);
        citiesDAO = new CitiesDAO(context);
        fetchData();
    }

    @Override
    public int getGroupCount() {
        return provinces.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return cities.get(provinces.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return provinces.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cities.get(provinces.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return provinces.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return cities.get(provinces.get(groupPosition)).get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.province_item_layout, parent, false);
        }

        TextView provinceItemTextView = convertView.findViewById(R.id.province_item_textView);
        provinceItemTextView.setText(((Province) getGroup(groupPosition)).getName());

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.city_item_layout, parent, false);
        }

        TextView cityItemTextView = convertView.findViewById(R.id.city_item_textView);
        cityItemTextView.setText(((City) getChild(groupPosition, childPosition)).getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void addProvince(Province province) {
        provincesDAO.addProvince(province);
        notifyDataSetChanged();
    }

    public void addCity(City city) {
        citiesDAO.addCity(city);
        notifyDataSetChanged();
    }

    public void fetchData() {

        if (provincesDAO.getProvincesCount() != 0)
            // اگر جدول استان ها در دیتابیس خالی نباشد آنها را به لیست موجود انتساب بده
            provinces = provincesDAO.getAllProvinces();
        else
            //در غیر اینصورت لیست موجود را با صفر خانه ایجاد کن
            provinces = new ArrayList<>(0);

        for (Province province : provinces) {
            // به ازا هر یک از عناصر موجود در لیست استان ها اگر جدول شهرهای مربوط به هر استان خالی نباشد
            // یک عنصر جدید با کلید نام استان و لیست شهرهای مربوط به آن استان را در مپ وارد کن
            if (citiesDAO.getCitiesCount(province.getId()) != 0)
                cities.put(province, citiesDAO.getAllCities(province.getId()));
            else
                // و اگر لیست شهرهای مربوط به هر یک از استان ها خالی باشد
                // یک لیست خالی به همراه نام استان در مپ وارد کن
                cities.put(province, new ArrayList<City>(0));
        }
    }


}
