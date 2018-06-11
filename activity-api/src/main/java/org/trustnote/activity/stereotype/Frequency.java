package org.trustnote.activity.stereotype;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Frequency {
    String name() default "all";
    int time() default 0;
    int limit() default 0;
}
