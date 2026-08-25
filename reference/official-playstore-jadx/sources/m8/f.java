package m8;

import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends e implements w8.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Annotation f20277b;

    public f(f9.f fVar, Annotation annotation) {
        super(fVar);
        this.f20277b = annotation;
    }

    @Override // w8.c
    public final d a() {
        return new d(this.f20277b);
    }
}
