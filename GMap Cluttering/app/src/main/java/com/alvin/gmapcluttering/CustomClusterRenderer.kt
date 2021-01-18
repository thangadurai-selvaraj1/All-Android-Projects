package com.alvin.gmapcluttering

import android.content.Context
import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer


class CustomClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<MyItem>
) :
    DefaultClusterRenderer<MyItem>(context, map, clusterManager) {

    override fun getColor(clusterSize: Int): Int {
        return Color.BLUE
    }


}