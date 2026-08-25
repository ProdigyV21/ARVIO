package j5;

import com.google.firebase.encoders.EncodingException;

/* JADX INFO: loaded from: classes4.dex */
public final class h implements com.google.firebase.encoders.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f19224a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f19225b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public com.google.firebase.encoders.c f19226c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f19227d;

    public h(f fVar) {
        this.f19227d = fVar;
    }

    @Override // com.google.firebase.encoders.g
    public final com.google.firebase.encoders.g e(String str) {
        if (this.f19224a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f19224a = true;
        this.f19227d.e(this.f19226c, str, this.f19225b);
        return this;
    }

    @Override // com.google.firebase.encoders.g
    public final com.google.firebase.encoders.g g(boolean z) {
        if (this.f19224a) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.f19224a = true;
        this.f19227d.h(this.f19226c, z ? 1 : 0, this.f19225b);
        return this;
    }
}
