package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "Lx6/x;", "", "Lio/ktor/http/ContentType;", "invoke", "()Ljava/util/List;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class MimesKt$mimes$2 extends r implements a<List<? extends x>> {
    public static final MimesKt$mimes$2 INSTANCE = new MimesKt$mimes$2();

    public MimesKt$mimes$2() {
        super(0);
    }

    @Override // r7.a
    public final List<x> invoke() {
        return MimesKt.loadMimes();
    }
}
