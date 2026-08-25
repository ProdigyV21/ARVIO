package io.sentry.android.core;

import io.sentry.e4;
import io.sentry.j7;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class j1 implements e4, NodeFilter {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ AtomicBoolean f16654i;

    public /* synthetic */ j1(AtomicBoolean atomicBoolean) {
        this.f16654i = atomicBoolean;
    }

    @Override // io.sentry.e4
    public void e(io.sentry.a1 a1Var) {
        j7 j7VarE = a1Var.E();
        if (j7VarE == null || j7VarE.c() == null) {
            return;
        }
        this.f16654i.set(true);
    }

    @Override // org.jsoup.select.NodeFilter
    public NodeFilter.FilterResult head(Node node, int i10) {
        return Element.lambda$hasText$1(this.f16654i, node, i10);
    }

    @Override // org.jsoup.select.NodeFilter
    public /* synthetic */ NodeFilter.FilterResult tail(Node node, int i10) {
        return org.jsoup.select.c.a(this, node, i10);
    }
}
