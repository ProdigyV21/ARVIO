package com.google.common.collect;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class n0 extends o0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final n0 f14063m = new n0("", 0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final n0 f14064n = new n0("", 1);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f14065l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n0(Comparable comparable, int i10) {
        super(comparable);
        this.f14065l = i10;
    }

    @Override // com.google.common.collect.o0
    /* JADX INFO: renamed from: a */
    public int compareTo(o0 o0Var) {
        switch (this.f14065l) {
            case 0:
                return o0Var == this ? 0 : 1;
            case 1:
                return o0Var == this ? 0 : -1;
            default:
                return super.compareTo(o0Var);
        }
    }

    @Override // com.google.common.collect.o0, java.lang.Comparable
    public int compareTo(Object obj) {
        switch (this.f14065l) {
            case 0:
                return ((o0) obj) == this ? 0 : 1;
            case 1:
                return ((o0) obj) == this ? 0 : -1;
            default:
                return super.compareTo(obj);
        }
    }

    @Override // com.google.common.collect.o0
    public final void d(StringBuilder sb2) {
        switch (this.f14065l) {
            case 0:
                throw new AssertionError();
            case 1:
                sb2.append("(-∞");
                return;
            default:
                sb2.append('[');
                sb2.append(this.f14076i);
                return;
        }
    }

    @Override // com.google.common.collect.o0
    public final void f(StringBuilder sb2) {
        switch (this.f14065l) {
            case 0:
                sb2.append("+∞)");
                return;
            case 1:
                throw new AssertionError();
            default:
                sb2.append(this.f14076i);
                sb2.append(')');
                return;
        }
    }

    @Override // com.google.common.collect.o0
    public final int hashCode() {
        switch (this.f14065l) {
            case 0:
                return System.identityHashCode(this);
            case 1:
                return System.identityHashCode(this);
            default:
                return this.f14076i.hashCode();
        }
    }

    @Override // com.google.common.collect.o0
    public Comparable i() {
        switch (this.f14065l) {
            case 0:
                throw new IllegalStateException("range unbounded on this side");
            case 1:
                throw new IllegalStateException("range unbounded on this side");
            default:
                return super.i();
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Comparable, java.lang.Object] */
    @Override // com.google.common.collect.o0
    public final boolean j(Comparable comparable) {
        switch (this.f14065l) {
            case 0:
                break;
            case 1:
                break;
            default:
                j3 j3Var = j3.f14034m;
                if (this.f14076i.compareTo(comparable) <= 0) {
                }
                break;
        }
        return true;
    }

    public final String toString() {
        switch (this.f14065l) {
            case 0:
                return "+∞";
            case 1:
                return "-∞";
            default:
                return "\\" + this.f14076i + DomExceptionUtils.SEPARATOR;
        }
    }
}
