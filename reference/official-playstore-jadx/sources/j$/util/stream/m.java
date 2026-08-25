package j$.util.stream;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class m extends h5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f18779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f18780c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(a aVar, l5 l5Var, int i10) {
        super(l5Var);
        this.f18779b = i10;
        this.f18780c = aVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(l5 l5Var) {
        super(l5Var);
        this.f18779b = 0;
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public void end() {
        switch (this.f18779b) {
            case 0:
                this.f18780c = null;
                this.f18711a.end();
                break;
            default:
                super.end();
                break;
        }
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public void c(long j10) {
        switch (this.f18779b) {
            case 0:
                this.f18780c = new HashSet();
                this.f18711a.c(-1L);
                break;
            case 1:
            default:
                super.c(j10);
                break;
            case 2:
                this.f18711a.c(-1L);
                break;
        }
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.f18779b) {
            case 0:
                if (!((Set) this.f18780c).contains(obj)) {
                    ((Set) this.f18780c).add(obj);
                    this.f18711a.accept(obj);
                }
                break;
            case 1:
                ((Consumer) ((r) this.f18780c).f18840t).accept(obj);
                this.f18711a.accept(obj);
                break;
            case 2:
                if (((Predicate) ((r) this.f18780c).f18840t).test(obj)) {
                    this.f18711a.accept(obj);
                }
                break;
            case 3:
                this.f18711a.accept(((Function) ((r) this.f18780c).f18840t).apply(obj));
                break;
            case 4:
                this.f18711a.accept(((ToIntFunction) ((u0) this.f18780c).f18902t).applyAsInt(obj));
                break;
            case 5:
                this.f18711a.accept(((ToLongFunction) ((f1) this.f18780c).f18687t).applyAsLong(obj));
                break;
            default:
                this.f18711a.accept(((ToDoubleFunction) ((s) this.f18780c).f18860t).applyAsDouble(obj));
                break;
        }
    }
}
