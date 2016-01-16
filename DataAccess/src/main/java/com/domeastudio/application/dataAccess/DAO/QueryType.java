package com.domeastudio.application.dataAccess.DAO;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by domea on 16-1-16.
 * 查询媒介
 * 1. equals：相等
 * 2. like:mongodb的like查询
 * 3. in:用于列表的in类型查询
 */
public enum QueryType {
    equals {
        @Override
        public Criteria buildcriteria(QueryField queryfieldannotation, Field field, Object value) {
            if (check(queryfieldannotation, field, value)) {
                String queryfield = getqueryfieldname(queryfieldannotation, field);
                return Criteria.where(queryfield).is(value.toString());
            }
            return new Criteria();
        }
    },
    like {
        @Override
        public Criteria buildcriteria(QueryField queryfieldannotation, Field field, Object value) {
            if (check(queryfieldannotation, field, value)) {
                String queryfield = getqueryfieldname(queryfieldannotation, field);
                return Criteria.where(queryfield).regex(value.toString());
            }
            return new Criteria();
        }
    },
    in {
        @Override
        public Criteria buildcriteria(QueryField queryfieldannotation, Field field, Object value) {
            if (check(queryfieldannotation, field, value)) {
                if (value instanceof List) {
                    String queryfield = getqueryfieldname(queryfieldannotation, field);
                    // 此处必须转型为list，否则会在in外面多一层[]
                    return Criteria.where(queryfield).in((List<?>) value);
                }
            }
            return new Criteria();
        }
    };

    private static boolean check(QueryField queryfield, Field field, Object value) {
        return !(queryfield == null || field == null || value == null);
    }

    public abstract Criteria buildcriteria(QueryField queryfieldannotation, Field field, Object value);


    /**
     * 如果实体bean的字段上queryfield注解没有设置attribute属性时，默认为该字段的名称
     *
     * @param field
     * @return
     */
    private static String getqueryfieldname(QueryField queryfield, Field field) {
        String queryfieldvalue = queryfield.attribute();
        if (!StringUtils.hasText(queryfieldvalue)) {
            queryfieldvalue = field.getName();
        }
        return queryfieldvalue;
    }
}
