package com.domeastudio.gis.tools;

import org.gdal.ogr.Geometry;

/**
 * Created by domea on 16-1-8.
 */
public class gdaltest {

    public Boolean getGeo(String wkt1,String wkt2){
        Geometry g1=Geometry.CreateFromWkt(wkt1);
        Geometry g2=Geometry.CreateFromWkt(wkt2);
        return null;
    }

    public static void main(String[] args) {
        gdaltest g=new gdaltest();
        String wkt1="polygon((1 1,5 1,5 5,1 5,1 1))";
        String wkt2="polygon((2 2,4 4,4 4,2 4,2 2))";
        Boolean b=g.getGeo(wkt1,wkt2);
        System.out.println(b);
    }
}
