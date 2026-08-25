package org.jsoup.helper;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class ValidationException extends IllegalArgumentException {
    public static final String Validator = Validate.class.getName();

    public ValidationException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        try {
            super.fillInStackTrace();
            StackTraceElement[] stackTrace = getStackTrace();
            ArrayList arrayList = new ArrayList();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (!stackTraceElement.getClassName().equals(Validator)) {
                    arrayList.add(stackTraceElement);
                }
            }
            setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }
}
