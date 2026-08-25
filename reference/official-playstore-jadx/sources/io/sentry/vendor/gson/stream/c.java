package io.sentry.vendor.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements Closeable, Flushable {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String[] f18018s = new String[128];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Writer f18019i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f18020l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f18021m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f18022n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f18023o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f18024p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f18025q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f18026r;

    static {
        for (int i10 = 0; i10 <= 31; i10++) {
            f18018s[i10] = String.format("\\u%04x", Integer.valueOf(i10));
        }
        String[] strArr = f18018s;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public c(Writer writer) {
        int[] iArr = new int[32];
        this.f18020l = iArr;
        this.f18021m = 0;
        if (iArr.length == 0) {
            this.f18020l = Arrays.copyOf(iArr, 0);
        }
        int[] iArr2 = this.f18020l;
        int i10 = this.f18021m;
        this.f18021m = i10 + 1;
        iArr2[i10] = 6;
        this.f18023o = ":";
        this.f18026r = true;
        this.f18019i = writer;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f18019i.close();
        int i10 = this.f18021m;
        if (i10 > 1 || (i10 == 1 && this.f18020l[i10 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f18021m = 0;
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.f18021m == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f18019i.flush();
    }

    public final void i() throws IOException {
        int iO = o();
        if (iO == 1) {
            this.f18020l[this.f18021m - 1] = 2;
            k();
            return;
        }
        Writer writer = this.f18019i;
        if (iO == 2) {
            writer.append(',');
            k();
        } else {
            if (iO == 4) {
                writer.append((CharSequence) this.f18023o);
                this.f18020l[this.f18021m - 1] = 5;
                return;
            }
            if (iO != 6) {
                if (iO != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f18024p) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            this.f18020l[this.f18021m - 1] = 7;
        }
    }

    public final void j(int i10, int i11, char c10) throws IOException {
        int iO = o();
        if (iO != i11 && iO != i10) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f18025q != null) {
            throw new IllegalStateException("Dangling name: " + this.f18025q);
        }
        this.f18021m--;
        if (iO == i11) {
            k();
        }
        this.f18019i.write(c10);
    }

    public final void k() throws IOException {
        if (this.f18022n == null) {
            return;
        }
        Writer writer = this.f18019i;
        writer.write(10);
        int i10 = this.f18021m;
        for (int i11 = 1; i11 < i10; i11++) {
            writer.write(this.f18022n);
        }
    }

    public final void l() throws IOException {
        if (this.f18025q != null) {
            if (!this.f18026r) {
                this.f18025q = null;
                return;
            }
            y();
        }
        i();
        this.f18019i.write("null");
    }

    public final int o() {
        int i10 = this.f18021m;
        if (i10 != 0) {
            return this.f18020l[i10 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void x(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            java.io.Writer r0 = r7.f18019i
            r1 = 34
            r0.write(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        Ld:
            if (r3 >= r2) goto L3a
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L1e
            java.lang.String[] r6 = io.sentry.vendor.gson.stream.c.f18018s
            r5 = r6[r5]
            if (r5 != 0) goto L2b
            goto L37
        L1e:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L25
            java.lang.String r5 = "\\u2028"
            goto L2b
        L25:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L37
            java.lang.String r5 = "\\u2029"
        L2b:
            if (r4 >= r3) goto L32
            int r6 = r3 - r4
            r0.write(r8, r4, r6)
        L32:
            r0.write(r5)
            int r4 = r3 + 1
        L37:
            int r3 = r3 + 1
            goto Ld
        L3a:
            if (r4 >= r2) goto L40
            int r2 = r2 - r4
            r0.write(r8, r4, r2)
        L40:
            r0.write(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.c.x(java.lang.String):void");
    }

    public final void y() throws IOException {
        if (this.f18025q != null) {
            int iO = o();
            if (iO == 5) {
                this.f18019i.write(44);
            } else if (iO != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            k();
            this.f18020l[this.f18021m - 1] = 4;
            x(this.f18025q);
            this.f18025q = null;
        }
    }
}
