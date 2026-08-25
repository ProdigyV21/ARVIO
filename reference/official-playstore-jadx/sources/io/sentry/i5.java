package io.sentry;

import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class i5 implements i2, k2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f17352i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Integer f17353l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f17354m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f17355n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final v5 f17356o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f17357p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Callable f17358q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f17359r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public HashMap f17360s;

    public i5(v5 v5Var, int i10, String str, String str2, String str3, String str4, Integer num) {
        this.f17356o = v5Var;
        this.f17352i = str;
        this.f17357p = i10;
        this.f17354m = str2;
        this.f17358q = null;
        this.f17359r = str3;
        this.f17355n = str4;
        this.f17353l = num;
    }

    public final int a() {
        Callable callable = this.f17358q;
        if (callable == null) {
            return this.f17357p;
        }
        try {
            return ((Integer) callable.call()).intValue();
        } catch (Throwable unused) {
            return -1;
        }
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        String str = this.f17352i;
        if (str != null) {
            fVar.S("content_type");
            fVar.value(str);
        }
        String str2 = this.f17354m;
        if (str2 != null) {
            fVar.S(ContentDisposition.Parameters.FileName);
            fVar.value(str2);
        }
        fVar.S(LinkHeader.Parameters.Type);
        fVar.b0(iLogger, this.f17356o);
        String str3 = this.f17359r;
        if (str3 != null) {
            fVar.S("attachment_type");
            fVar.value(str3);
        }
        String str4 = this.f17355n;
        if (str4 != null) {
            fVar.S("platform");
            fVar.value(str4);
        }
        Integer num = this.f17353l;
        if (num != null) {
            fVar.S("item_count");
            fVar.d0(num);
        }
        fVar.S("length");
        fVar.d(a());
        HashMap map = this.f17360s;
        if (map != null) {
            for (String str5 : map.keySet()) {
                g8.b.m(this.f17360s, str5, fVar, str5, iLogger);
            }
        }
        fVar.K();
    }

    public i5(v5 v5Var, Callable callable, String str, String str2, String str3) {
        this(v5Var, callable, str, str2, str3, (String) null, (Integer) null);
    }

    public i5(v5 v5Var, Callable callable, String str, String str2, String str3, String str4, Integer num) {
        ac.b.V(v5Var, "type is required");
        this.f17356o = v5Var;
        this.f17352i = str;
        this.f17357p = -1;
        this.f17354m = str2;
        this.f17358q = callable;
        this.f17359r = str3;
        this.f17355n = str4;
        this.f17353l = num;
    }
}
