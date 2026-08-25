package kotlin.text;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements ga.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f19935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f19936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r7.p f19937c;

    public c(CharSequence charSequence, int i10, r7.p pVar) {
        this.f19935a = charSequence;
        this.f19936b = i10;
        this.f19937c = pVar;
    }

    @Override // ga.m
    public final Iterator iterator() {
        return new b(this);
    }
}
