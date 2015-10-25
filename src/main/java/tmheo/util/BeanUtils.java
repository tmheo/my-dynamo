package tmheo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Taemyung on 2015-06-07.
 */
@Slf4j
public class BeanUtils {

    private static BeanUtilsBean notNull = new NullAwareBeanUtilsBean();

    public static void copyNotNullProperties(Object source, Object target) {

        log.trace("source : {}, target : {} before copy", source, target);

        try {
            notNull.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            log.error("illegal access exception", e);
        } catch (InvocationTargetException e) {
            log.error("invocation target exception", e);
        }

        log.trace("source : {}, target : {} after copy", source, target);

    }

    static class NullAwareBeanUtilsBean extends BeanUtilsBean {
        @Override
        public void copyProperty(Object dest, String name, Object value)
                throws IllegalAccessException, InvocationTargetException {
            if (value == null) return;
            super.copyProperty(dest, name, value);
        }
    }

}
