package io.sentry;

import androidx.media3.common.MimeTypes;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f16288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.protocol.j0 f16289b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final androidx.media3.datasource.c f16290c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f16291d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f16292e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f16293f;

    public a(String str, String str2, String str3, byte[] bArr) {
        this.f16288a = bArr;
        this.f16289b = null;
        this.f16290c = null;
        this.f16291d = str;
        this.f16292e = str2;
        this.f16293f = str3;
    }

    public a(io.sentry.protocol.j0 j0Var) {
        this.f16288a = null;
        this.f16289b = j0Var;
        this.f16290c = null;
        this.f16291d = "view-hierarchy.json";
        this.f16292e = "application/json";
        this.f16293f = "event.view_hierarchy";
    }

    public a(androidx.media3.datasource.c cVar) {
        this.f16288a = null;
        this.f16289b = null;
        this.f16290c = cVar;
        this.f16291d = "screenshot.png";
        this.f16292e = MimeTypes.IMAGE_PNG;
        this.f16293f = "event.attachment";
    }
}
