package io.sentry.cache;

import android.content.Context;
import androidx.appcompat.app.i1;
import com.google.android.datatransport.runtime.i;
import com.google.firebase.components.s;
import com.google.firebase.components.u;
import io.sentry.ILogger;
import io.sentry.a1;
import io.sentry.util.j;
import io.sentry.util.l;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.z3;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.concurrent.Executor;
import k3.h;
import l3.a;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class a implements j, z3, a.InterfaceC0242a, com.google.firebase.components.f, NodeVisitor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17144i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f17145l;

    public /* synthetic */ a(l lVar, Object obj, int i10) {
        this.f17144i = i10;
        this.f17145l = obj;
    }

    @Override // io.sentry.z3
    public void c(i1 i1Var) {
        ((a1) this.f17145l).H(new i1(18));
    }

    @Override // l3.a.InterfaceC0242a
    public Object execute() {
        switch (this.f17144i) {
            case 5:
                ((k3.f) this.f17145l).f19496i.i();
                return null;
            case 6:
                return ((com.google.android.datatransport.runtime.scheduling.persistence.c) this.f17145l).k();
            case 7:
                return Integer.valueOf(((com.google.android.datatransport.runtime.scheduling.persistence.d) this.f17145l).e());
            default:
                h hVar = (h) this.f17145l;
                Iterator it = hVar.f19505b.w().iterator();
                while (it.hasNext()) {
                    hVar.f19506c.a((i) it.next(), 1);
                }
                return null;
        }
    }

    @Override // com.google.firebase.components.f
    public Object f(u uVar) {
        return new l5.c((Context) uVar.a(Context.class), ((z4.g) uVar.a(z4.g.class)).d(), uVar.h(l5.d.class), uVar.e(t5.e.class), (Executor) uVar.d((s) this.f17145l));
    }

    @Override // io.sentry.util.j
    public Object g() {
        io.sentry.cache.tape.j jVar;
        switch (this.f17144i) {
            case 0:
                return ((c) this.f17145l).f17148i.getSerializer();
            case 1:
                g gVar = (g) this.f17145l;
                y6 y6Var = gVar.f17161a;
                File fileB = b.b(y6Var, ".scope-cache");
                if (fileB == null) {
                    y6Var.getLogger().q(w5.INFO, "Cache dir is not set, cannot store in scope cache", new Object[0]);
                    return new io.sentry.cache.tape.b();
                }
                File file = new File(fileB, "breadcrumbs.json");
                try {
                    int maxBreadcrumbs = y6Var.getMaxBreadcrumbs();
                    RandomAccessFile randomAccessFileK = io.sentry.cache.tape.j.k(file);
                    try {
                        try {
                            jVar = new io.sentry.cache.tape.j(file, randomAccessFileK, maxBreadcrumbs);
                        } catch (IOException e5) {
                            y6Var.getLogger().l(w5.ERROR, "Failed to create breadcrumbs queue", e5);
                            return new io.sentry.cache.tape.b();
                        }
                    } catch (Throwable th) {
                        randomAccessFileK.close();
                        throw th;
                    }
                    break;
                } catch (IOException unused) {
                    file.delete();
                    int maxBreadcrumbs2 = y6Var.getMaxBreadcrumbs();
                    RandomAccessFile randomAccessFileK2 = io.sentry.cache.tape.j.k(file);
                    try {
                        jVar = new io.sentry.cache.tape.j(file, randomAccessFileK2, maxBreadcrumbs2);
                    } catch (Throwable th2) {
                        randomAccessFileK2.close();
                        throw th2;
                    }
                    break;
                }
                return new io.sentry.cache.tape.e(jVar, new a8.e(gVar, 26));
            case 2:
                return Boolean.valueOf(l.l((y6) this.f17145l, "androidx.core.view.ScrollingView"));
            default:
                return Boolean.valueOf(l.m("androidx.core.app.FrameMetricsAggregator", (ILogger) this.f17145l));
        }
    }

    @Override // org.jsoup.select.NodeVisitor
    public void head(Node node, int i10) {
        Element.lambda$data$2((StringBuilder) this.f17145l, node, i10);
    }

    @Override // org.jsoup.select.NodeVisitor
    public /* synthetic */ void tail(Node node, int i10) {
        org.jsoup.select.d.a(this, node, i10);
    }

    public /* synthetic */ a(Object obj, int i10) {
        this.f17144i = i10;
        this.f17145l = obj;
    }
}
