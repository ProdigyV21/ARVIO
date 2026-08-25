package c1;

import java.util.Map;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends r implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f7367i = new a(1);

    @Override // r7.l
    public final Object invoke(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        return "  " + ((e) entry.getKey()).f7375a + " = " + entry.getValue();
    }
}
