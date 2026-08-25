package com.google.common.collect;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class b2 extends l4 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ com.google.common.base.m f13941l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b2(Iterator it, com.google.common.base.m mVar) {
        super(it);
        this.f13941l = mVar;
    }

    @Override // com.google.common.collect.l4
    public final Object a(Object obj) {
        return this.f13941l.apply(obj);
    }
}
