package io.ktor.utils.io.jvm.javaio;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u000f\u0010\u0004\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\"\"\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n\"\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00078AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lx6/t0;", "prohibitParking", "()V", "", "isParkingAllowed", "()Z", "Ljava/lang/ThreadLocal;", "Lio/ktor/utils/io/jvm/javaio/Parking;", "Ljava/lang/Thread;", "parkingImplLocal", "Ljava/lang/ThreadLocal;", "getParkingImpl", "()Lio/ktor/utils/io/jvm/javaio/Parking;", "parkingImpl", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PollersKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f16280a = 0;
    private static final ThreadLocal<Parking<Thread>> parkingImplLocal = new ThreadLocal<>();

    public static final Parking<Thread> getParkingImpl() {
        Parking<Thread> parking = parkingImplLocal.get();
        return parking == null ? DefaultParking.INSTANCE : parking;
    }

    public static final boolean isParkingAllowed() {
        return getParkingImpl() != ProhibitParking.INSTANCE;
    }

    public static final void prohibitParking() {
        parkingImplLocal.set(ProhibitParking.INSTANCE);
    }
}
