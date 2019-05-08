package com.kjetland.jackson.jsonSchema.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({METHOD, FIELD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSchemaProperty {
  /**
   * This dictates whether or not to mark a property of a schema model as required or not.
   * This overrides ALL other behavior affecting required.
   */
  boolean required();
}
