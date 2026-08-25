package j$.util;

import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class h implements DoubleConsumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f18564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f18565b;
    private long count;
    private double sum;
    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d4) {
        this.count++;
        this.f18565b += d4;
        b(d4);
        this.min = Math.min(this.min, d4);
        this.max = Math.max(this.max, d4);
    }

    public final void a(h hVar) {
        this.count += hVar.count;
        this.f18565b += hVar.f18565b;
        b(hVar.sum);
        b(hVar.f18564a);
        this.min = Math.min(this.min, hVar.min);
        this.max = Math.max(this.max, hVar.max);
    }

    public final void b(double d4) {
        double d10 = d4 - this.f18564a;
        double d11 = this.sum;
        double d12 = d11 + d10;
        this.f18564a = (d12 - d11) - d10;
        this.sum = d12;
    }

    public final String toString() {
        double d4;
        String simpleName = h.class.getSimpleName();
        Long lValueOf = Long.valueOf(this.count);
        double d10 = this.sum + this.f18564a;
        if (Double.isNaN(d10) && Double.isInfinite(this.f18565b)) {
            d10 = this.f18565b;
        }
        Double dValueOf = Double.valueOf(d10);
        Double dValueOf2 = Double.valueOf(this.min);
        if (this.count > 0) {
            double d11 = this.sum + this.f18564a;
            if (Double.isNaN(d11) && Double.isInfinite(this.f18565b)) {
                d11 = this.f18565b;
            }
            d4 = d11 / this.count;
        } else {
            d4 = 0.0d;
        }
        return String.format("%s{count=%d, sum=%f, min=%f, average=%f, max=%f}", simpleName, lValueOf, dValueOf, dValueOf2, Double.valueOf(d4), Double.valueOf(this.max));
    }
}
