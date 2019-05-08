package com.kjetland.jackson.jsonSchema.testData;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDescription;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaFormat;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaProperty;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle;

@JsonSchemaFormat("grid")
@JsonSchemaDescription("This is our pojo")
@JsonSchemaTitle("Pojo using format")
public class PojoUsingFormat {

    @JsonSchemaFormat("email")
    @JsonSchemaDescription("This is our email value")
    @JsonSchemaTitle("Email value")
    public String emailValue;

    @JsonSchemaFormat("checkbox")
    public Boolean choice;

    @JsonSchemaProperty(required = false)
    public String aNonRequiredString;

    @JsonPropertyDescription("This is description from @JsonPropertyDescription")
    public OffsetDateTime dateTime;

    @JsonSchemaFormat("text")
    public OffsetDateTime dateTimeWithAnnotation;

    private PojoUsingFormat() {
    }

    public PojoUsingFormat(String emailValue, Boolean choice, String aNonRequiredString, OffsetDateTime dateTime, OffsetDateTime dateTimeWithAnnotation) {
        this.emailValue = emailValue;
        this.choice = choice;
        this.aNonRequiredString = aNonRequiredString;
        this.dateTime = dateTime;
        this.dateTimeWithAnnotation = dateTimeWithAnnotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PojoUsingFormat)) return false;

        PojoUsingFormat that = (PojoUsingFormat) o;

        if (emailValue != null ? !emailValue.equals(that.emailValue) : that.emailValue != null) return false;
        if (choice != null ? !choice.equals(that.choice) : that.choice != null) return false;
        if (aNonRequiredString != null ? !aNonRequiredString.equals(that.aNonRequiredString) : that.aNonRequiredString != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        return dateTimeWithAnnotation != null ? dateTimeWithAnnotation.equals(that.dateTimeWithAnnotation) : that.dateTimeWithAnnotation == null;

    }

    @Override
    public int hashCode() {
        int result = emailValue != null ? emailValue.hashCode() : 0;
        result = 31 * result + (choice != null ? choice.hashCode() : 0);
        result = 31 * result + (aNonRequiredString != null ? aNonRequiredString.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (dateTimeWithAnnotation != null ? dateTimeWithAnnotation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PojoUsingFormat{" +
                "emailValue='" + emailValue + '\'' +
                ", choice=" + choice +
                ", aNonRequiredString=" + aNonRequiredString +
                ", dateTime=" + dateTime +
                ", dateTimeWithAnnotation=" + dateTimeWithAnnotation +
                '}';
    }
}
