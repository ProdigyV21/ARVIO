package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class t2 extends l4 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f14113l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t2(Iterator it, int i10) {
        super(it);
        this.f14113l = i10;
    }

    @Override // com.google.common.collect.l4
    public final Object a(Object obj) {
        switch (this.f14113l) {
            case 0:
                return ((Map.Entry) obj).getKey();
            default:
                return ((Map.Entry) obj).getValue();
        }
    }
}
