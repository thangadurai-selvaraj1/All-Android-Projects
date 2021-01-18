package com.alvin.gmapcluttering

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MyItem(
    latLng: LatLng,
    markerTitle: String,
    des: String
) : ClusterItem {

    private val position: LatLng = latLng
    private val mMarkerTitle = markerTitle
    private val mDes = des

    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String {
        return mMarkerTitle
    }

    override fun getSnippet(): String {
        return mDes
    }

}
