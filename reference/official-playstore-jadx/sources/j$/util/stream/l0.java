package j$.util.stream;

import j$.util.stream.IntStream;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class l0 implements IntFunction, LongFunction {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IntFunction f18772a;

    @Override // java.util.function.IntFunction
    public Object apply(int i10) {
        Object objApply = this.f18772a.apply(i10);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof IntStream) {
            return IntStream.Wrapper.convert((IntStream) objApply);
        }
        if (objApply instanceof java.util.stream.IntStream) {
            return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) objApply);
        }
        j$.util.f.a(objApply.getClass(), "java.util.stream.IntStream");
        throw null;
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j10) {
        return v3.Y(j10, this.f18772a);
    }
}
