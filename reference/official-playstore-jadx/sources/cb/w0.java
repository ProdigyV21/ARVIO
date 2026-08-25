package cb;

import kotlinx.serialization.SerializationException;

/* JADX INFO: loaded from: classes5.dex */
public final class w0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w0 f7579a = new w0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final v0 f7580b = v0.f7574a;

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        throw new SerializationException("'kotlin.Nothing' does not have instances");
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return f7580b;
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        throw new SerializationException("'kotlin.Nothing' cannot be serialized");
    }
}
