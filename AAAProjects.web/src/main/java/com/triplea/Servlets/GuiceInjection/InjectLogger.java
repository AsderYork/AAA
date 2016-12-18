package com.triplea.Servlets.GuiceInjection;

/**
 * Created by Asder on 12.12.2016.
 */
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectLogger {
}