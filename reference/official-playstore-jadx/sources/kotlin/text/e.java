package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CharSequence f19938i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f19939l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19940m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19941n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f19942o;

    public e(CharSequence charSequence) {
        this.f19938i = charSequence;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i10;
        int i11;
        int i12 = this.f19939l;
        if (i12 != 0) {
            return i12 == 1;
        }
        if (this.f19942o < 0) {
            this.f19939l = 2;
            return false;
        }
        CharSequence charSequence = this.f19938i;
        int length = charSequence.length();
        int length2 = charSequence.length();
        for (int i13 = this.f19940m; i13 < length2; i13++) {
            char cCharAt = charSequence.charAt(i13);
            if (cCharAt == '\n' || cCharAt == '\r') {
                i10 = (cCharAt == '\r' && (i11 = i13 + 1) < charSequence.length() && charSequence.charAt(i11) == '\n') ? 2 : 1;
                length = i13;
                this.f19939l = 1;
                this.f19942o = i10;
                this.f19941n = length;
                return true;
            }
        }
        i10 = -1;
        this.f19939l = 1;
        this.f19942o = i10;
        this.f19941n = length;
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f19939l = 0;
        int i10 = this.f19941n;
        int i11 = this.f19940m;
        this.f19940m = this.f19942o + i10;
        return this.f19938i.subSequence(i11, i10).toString();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
