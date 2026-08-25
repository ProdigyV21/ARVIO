package i8;

import g8.f;
import java.util.Collection;
import kotlin.collections.z;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.v;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements b, d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f16238b = new a(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f16239c = new a(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f16240d = new a(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16241a;

    public /* synthetic */ a(int i10) {
        this.f16241a = i10;
    }

    @Override // i8.b
    public Collection a(f fVar) {
        return z.f19728i;
    }

    @Override // i8.b
    public Collection b(f fVar) {
        return z.f19728i;
    }

    @Override // i8.b
    public Collection c(f fVar) {
        return z.f19728i;
    }

    @Override // i8.d
    public boolean d(f fVar, v vVar) {
        switch (this.f16241a) {
            case 1:
                return true;
            default:
                return !vVar.getAnnotations().x(e.f16242a);
        }
    }

    @Override // i8.b
    public Collection e(f9.f fVar, f fVar2) {
        return z.f19728i;
    }
}
