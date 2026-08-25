package android.content;

import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
public /* synthetic */ class Time$provider$1 extends m implements a<Long> {
    public static final Time$provider$1 INSTANCE = new Time$provider$1();

    public Time$provider$1() {
        super(0, System.class, "currentTimeMillis", "currentTimeMillis()J", 0);
    }

    @Override // r7.a
    public final Long invoke() {
        return Long.valueOf(System.currentTimeMillis());
    }
}
