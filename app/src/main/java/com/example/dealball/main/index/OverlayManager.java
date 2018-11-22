package com.example.dealball.main.index;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnPolylineClickListener;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;

/**
 * 该类提供一个能够显示和管理多个Overlay的基类
 * <p>
 * 复写{@link #()} 设置欲显示和管理的Overlay列表
 * </p>
 * <p>
 * 通过
 * {@link BaiduMap#setOnMarkerClickListener(OnMarkerClickListener)}
 * 将覆盖物点击事件传递给OverlayManager后，OverlayManager才能响应点击事件。
 * <p>
 * 复写{@link #(Marker)} 处理Marker点击事件
 * </p>
 * onMarkerClick
 */
public class OverlayManager {

    private BaiduMap mBaiduMap = null;
    private List<OverlayOptions> mOverlayOptionList = null;

    private List<Overlay> mOverlayList = null;
    private List<Marker> mMarkerList= null;
    private MyOverlayOption myOverlayOption;


    /**
     * 通过一个BaiduMap 对象构造
     * 
     * @param baiduMap
     */
     OverlayManager(BaiduMap baiduMap) {
        mBaiduMap = baiduMap;
        myOverlayOption = new MyOverlayOption(baiduMap);
        // mBaiduMap.setOnMarkerClickListener(this);
        if (mOverlayOptionList == null) {
            mOverlayOptionList = new ArrayList<OverlayOptions>();
        }
        if (mOverlayList == null) {
            mOverlayList = new ArrayList<Overlay>();
        }
         if (mMarkerList == null) {
             mMarkerList = new ArrayList<Marker>();
         }
    }

    /**
     * 覆写此方法设置要管理的Overlay列表
     * 
     * @return 管理的Overlay列表
     */
//    public abstract List<OverlayOptions> getOverlayOptions();

    /**
     * 将所有Overlay 添加到地图上
     */
    public final List addToMap() {
        if (mBaiduMap != null) {
            removeFromMap();
            List<OverlayOptions> overlayOptions = myOverlayOption.getOverlayOptionsList();
            if (overlayOptions != null) {
                mOverlayOptionList.addAll(overlayOptions);
            }
        }
        return mOverlayOptionList;
        /*for (OverlayOptions option : mOverlayOptionList) {
            mMarkerList.add((Marker)mBaiduMap.addOverlay(option));
            mOverlayList.add(mBaiduMap.addOverlay(option));
        }*/
    }

    /**
     * 将所有Overlay 从 地图上消除
     */
    public final void removeFromMap() {
        if (mBaiduMap == null) {
            return;
        }
        for (Overlay marker : mOverlayList) {
            marker.remove();
        }
        mOverlayOptionList.clear();
        mOverlayList.clear();
        mMarkerList.clear();

    }

    /**
     * 缩放地图，使所有Overlay都在合适的视野内
     * <p>
     * 注： 该方法只对Marker类型的overlay有效
     * </p>
     * 
     */
    public void zoomToSpan() {
        if (mBaiduMap == null) {
            return;
        }
        if (mOverlayList.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Overlay overlay : mOverlayList) {
                // polyline 中的点可能太多，只按marker 缩放
                if (overlay instanceof Marker) {
                    builder.include(((Marker) overlay).getPosition());
                }
            }
            mBaiduMap.setMapStatus(MapStatusUpdateFactory
                    .newLatLngBounds(builder.build()));
        }
    }

}
//final LatLngBounds visibleBounds = mMap.getMapStatus().bound; 判断marker是否在屏幕显示范围以内
