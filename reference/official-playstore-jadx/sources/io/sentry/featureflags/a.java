package io.sentry.featureflags;

import io.sentry.protocol.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile CopyOnWriteArrayList f17286i;

    @Override // io.sentry.featureflags.b
    public final h n() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f17286i.iterator();
        if (!it.hasNext()) {
            return new h(arrayList);
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    @Override // io.sentry.featureflags.b
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final b m7019clone() {
        a aVar = new a();
        new io.sentry.util.b();
        aVar.f17286i = new CopyOnWriteArrayList(this.f17286i);
        return aVar;
    }
}
