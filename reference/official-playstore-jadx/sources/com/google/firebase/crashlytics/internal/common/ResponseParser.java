package com.google.firebase.crashlytics.internal.common;

/* JADX INFO: loaded from: classes4.dex */
public class ResponseParser {
    public static final int ResponseActionDiscard = 0;
    public static final int ResponseActionRetry = 1;

    public static int parse(int i10) {
        if (i10 < 200 || i10 > 299) {
            return ((i10 < 300 || i10 > 399) && i10 >= 400 && i10 <= 499) ? 0 : 1;
        }
        return 0;
    }
}
