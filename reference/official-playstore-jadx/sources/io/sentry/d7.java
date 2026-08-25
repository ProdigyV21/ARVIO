package io.sentry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes4.dex */
public final class d7 extends androidx.appcompat.app.o0 {
    public static final List D = Collections.unmodifiableList(Arrays.asList(HttpConnection.CONTENT_TYPE, "Content-Length", "Accept"));
    public boolean A;
    public List B;
    public List C;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile boolean f17216m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Double f17217n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Double f17218o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public c7 f17219p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17220q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f17221r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f17222s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f17223t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f17224u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public io.sentry.protocol.t f17225v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f17226w;
    public k4 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f17227y;
    public List z;

    public final void A(Double d4) {
        if (androidx.work.impl.t.x(d4, true)) {
            this.f17218o = d4;
            return;
        }
        throw new IllegalArgumentException("The value " + d4 + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
    }

    public final void B(Double d4) {
        if (androidx.work.impl.t.x(d4, true)) {
            this.f17217n = d4;
            return;
        }
        throw new IllegalArgumentException("The value " + d4 + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
    }

    @Override // androidx.appcompat.app.o0
    public final void i(boolean z) {
        if (!z) {
            l();
        }
        super.i(z);
    }

    @Override // androidx.appcompat.app.o0
    public final void j(boolean z) {
        if (!z) {
            l();
        }
        super.j(z);
    }

    @Override // androidx.appcompat.app.o0
    public final void l() {
        if (this.f17216m) {
            return;
        }
        this.f17216m = true;
        xc.d.H("ReplayCustomMasking");
    }

    public final List m() {
        return this.f17227y;
    }

    public final List n() {
        return this.z;
    }

    public final List o() {
        return this.B;
    }

    public final List p() {
        return this.C;
    }

    public final Double q() {
        return this.f17218o;
    }

    public final Double s() {
        return this.f17217n;
    }

    public final boolean t() {
        return this.A;
    }

    public final void u(boolean z) {
        this.f17226w = z;
    }

    public final void v(boolean z) {
        this.A = z;
    }

    public final void w(ArrayList arrayList) {
        this.f17227y = Collections.unmodifiableList(new ArrayList(arrayList));
    }

    public final void x(ArrayList arrayList) {
        this.z = Collections.unmodifiableList(new ArrayList(arrayList));
    }

    public final void y(ArrayList arrayList) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(D);
        linkedHashSet.addAll(arrayList);
        this.B = Collections.unmodifiableList(new ArrayList(linkedHashSet));
    }

    public final void z(ArrayList arrayList) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(D);
        linkedHashSet.addAll(arrayList);
        this.C = Collections.unmodifiableList(new ArrayList(linkedHashSet));
    }
}
