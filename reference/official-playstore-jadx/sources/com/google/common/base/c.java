package com.google.common.base;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class c extends i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13896i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f13897l;

    public c(i iVar) {
        iVar.getClass();
        this.f13897l = iVar;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        switch (this.f13896i) {
        }
        return c(((Character) obj).charValue());
    }

    @Override // com.google.common.base.i
    public final boolean c(char c10) {
        switch (this.f13896i) {
            case 0:
                return Arrays.binarySearch((char[]) this.f13897l, c10) >= 0;
            default:
                return !((i) this.f13897l).c(c10);
        }
    }

    @Override // com.google.common.base.i
    public i d() {
        switch (this.f13896i) {
            case 1:
                return (i) this.f13897l;
            default:
                return super.d();
        }
    }

    public final String toString() {
        switch (this.f13896i) {
            case 0:
                StringBuilder sb2 = new StringBuilder("CharMatcher.anyOf(\"");
                for (char c10 : (char[]) this.f13897l) {
                    sb2.append(i.a(c10));
                }
                sb2.append("\")");
                return sb2.toString();
            default:
                return ((i) this.f13897l) + ".negate()";
        }
    }

    public c(String str) {
        char[] charArray = str.toString().toCharArray();
        this.f13897l = charArray;
        Arrays.sort(charArray);
    }
}
