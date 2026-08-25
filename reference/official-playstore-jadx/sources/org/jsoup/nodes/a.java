package org.jsoup.nodes;

import j$.util.function.Function$CC;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class a implements Function {
    @Override // java.util.function.Function
    /* JADX INFO: renamed from: andThen */
    public /* synthetic */ Function mo7027andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Collections.unmodifiableList((List) obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }
}
