package android.content;

import kotlin.Metadata;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u0003R\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcoil/util/Time;", "", "<init>", "()V", "", "currentMillis", "()J", "Lx6/t0;", "setCurrentMillis", "(J)V", "reset", "Lkotlin/Function0;", "provider", "Lr7/a;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Time {
    public static final Time INSTANCE = new Time();
    private static a<Long> provider = Time$provider$1.INSTANCE;

    /* JADX INFO: renamed from: coil.util.Time$reset$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass1 extends m implements a<Long> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0, System.class, "currentTimeMillis", "currentTimeMillis()J", 0);
        }

        @Override // r7.a
        public final Long invoke() {
            return Long.valueOf(System.currentTimeMillis());
        }
    }

    /* JADX INFO: renamed from: coil.util.Time$setCurrentMillis$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Long;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class C06501 extends r implements a<Long> {
        final /* synthetic */ long $currentMillis;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06501(long j10) {
            super(0);
            this.$currentMillis = j10;
        }

        @Override // r7.a
        public final Long invoke() {
            return Long.valueOf(this.$currentMillis);
        }
    }

    private Time() {
    }

    public final long currentMillis() {
        return ((Number) provider.invoke()).longValue();
    }

    public final void reset() {
        provider = AnonymousClass1.INSTANCE;
    }

    public final void setCurrentMillis(long currentMillis) {
        provider = new C06501(currentMillis);
    }
}
