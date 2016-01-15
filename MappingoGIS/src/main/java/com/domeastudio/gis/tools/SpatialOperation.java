package com.domeastudio.gis.tools;

import org.gdal.ogr.Geometry;
import org.omg.CORBA.Object;

/**
 * Created by domea on 16-1-9.
 * GIS空间运算
 */
public class SpatialOperation {
    /**
     * 相交运算，判断两个对象是否相交，相交和返回true，否则false；当数据转换出现错误时返回null
     * @param geoStr1
     * @param geoStr2
     * @return
     */
    public Boolean intersects(String geoStr1,String geoStr2){
        try{
            Geometry g1=Geometry.CreateFromGML(geoStr1);
            Geometry g2=Geometry.CreateFromGML(geoStr2);
            return g1.Intersects(g2);
        }catch(Exception e){
            try{
                Geometry g1=Geometry.CreateFromJson(geoStr1);
                Geometry g2=Geometry.CreateFromJson(geoStr2);
                return g1.Intersects(g2);
            }catch(Exception e1){
                try{
                    Geometry g1=Geometry.CreateFromWkt(geoStr1);
                    Geometry g2=Geometry.CreateFromWkt(geoStr2);
                    return g1.Intersects(g2);
                }catch(Exception e2){
                    System.out.println("error message:"+e2.getMessage());
                    return null;
                }
            }
        }
    }

    /**
     * 交运算，返回两个对象相交后的交对象；当对象转换错误时返回null。
     * @param geoStr1
     * @param geoStr2
     * @return
     */
    public Geometry Intersection(String geoStr1,String geoStr2){
        try{
            Geometry g1=Geometry.CreateFromGML(geoStr1);
            Geometry g2=Geometry.CreateFromGML(geoStr2);
            return g1.Intersection(g2);
        }catch(Exception e){
            try{
                Geometry g1=Geometry.CreateFromJson(geoStr1);
                Geometry g2=Geometry.CreateFromJson(geoStr2);
                return g1.Intersection(g2);
            }catch (Exception e1){
                try{
                    Geometry g1=Geometry.CreateFromWkt(geoStr1);
                    Geometry g2=Geometry.CreateFromWkt(geoStr2);
                    return g1.Intersection(g2);
                }catch (Exception e2){
                    System.out.println("error message:"+e2.getMessage());
                    return null;
                }
            }
        }
    }
}
