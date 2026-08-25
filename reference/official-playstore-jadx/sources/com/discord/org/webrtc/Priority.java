package com.discord.org.webrtc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface Priority {
    public static final int HIGH = 3;
    public static final int LOW = 1;
    public static final int MEDIUM = 2;
    public static final int VERY_LOW = 0;
}
