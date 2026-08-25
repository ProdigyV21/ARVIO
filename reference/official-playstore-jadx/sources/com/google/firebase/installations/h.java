package com.google.firebase.installations;

import io.sentry.util.l;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f14387b = TimeUnit.HOURS.toSeconds(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Pattern f14388c = Pattern.compile("\\AA[\\w-]{38}\\z");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static h f14389d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q5.a f14390a;

    public h(l lVar) {
        this.f14390a = lVar;
    }
}
