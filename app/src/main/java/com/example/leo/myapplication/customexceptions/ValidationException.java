package com.example.leo.myapplication.customexceptions;

import android.support.annotation.StringRes;

/**
 * Created by Trovata on 27/06/2017.
 */

public class ValidationException extends Exception {
    private String invalidField;
    private String invalidCause;
    private Class fieldClass;
    private int localizedMessageId;

    public ValidationException(String invalidField, String invalidCause, Class fieldClass, @StringRes int localizedMessageId){
        super(invalidCause + " on " + invalidField + " of class : " + fieldClass.getName());
        this.invalidField = invalidField;
        this.invalidCause = invalidCause;
        this.fieldClass = fieldClass;
        this.localizedMessageId = localizedMessageId;
    }

    public String getInvalidField() {
        return invalidField;
    }

    public void setInvalidField(String invalidField) {
        this.invalidField = invalidField;
    }

    public String getInvalidCause() {
        return invalidCause;
    }

    public void setInvalidCause(String invalidCause) {
        this.invalidCause = invalidCause;
    }

    public Class getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(Class fieldClass) {
        this.fieldClass = fieldClass;
    }

    public int getLocalizedMessageId() {
        return localizedMessageId;
    }

    public void setLocalizedMessageId(int localizedMessageId) {
        this.localizedMessageId = localizedMessageId;
    }
}
