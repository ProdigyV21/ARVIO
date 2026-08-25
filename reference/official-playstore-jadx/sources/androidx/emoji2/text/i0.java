package androidx.emoji2.text;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class i0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadLocal f2615d = new ThreadLocal();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e0 f2617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f2618c = 0;

    public i0(e0 e0Var, int i10) {
        this.f2617b = e0Var;
        this.f2616a = i10;
    }

    public final int a(int i10) {
        androidx.emoji2.text.flatbuffer.a aVarB = b();
        int iA = aVarB.a(16);
        if (iA == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = (ByteBuffer) aVarB.f2241n;
        int i11 = iA + aVarB.f2238i;
        return byteBuffer.getInt((i10 * 4) + byteBuffer.getInt(i11) + i11 + 4);
    }

    public final androidx.emoji2.text.flatbuffer.a b() {
        ThreadLocal threadLocal = f2615d;
        androidx.emoji2.text.flatbuffer.a aVar = (androidx.emoji2.text.flatbuffer.a) threadLocal.get();
        if (aVar == null) {
            aVar = new androidx.emoji2.text.flatbuffer.a();
            threadLocal.set(aVar);
        }
        androidx.emoji2.text.flatbuffer.b bVar = (androidx.emoji2.text.flatbuffer.b) this.f2617b.f2600l;
        int iA = bVar.a(6);
        if (iA != 0) {
            int i10 = iA + bVar.f2238i;
            int i11 = (this.f2616a * 4) + ((ByteBuffer) bVar.f2241n).getInt(i10) + i10 + 4;
            int i12 = ((ByteBuffer) bVar.f2241n).getInt(i11) + i11;
            ByteBuffer byteBuffer = (ByteBuffer) bVar.f2241n;
            aVar.f2241n = byteBuffer;
            if (byteBuffer != null) {
                aVar.f2238i = i12;
                int i13 = i12 - byteBuffer.getInt(i12);
                aVar.f2239l = i13;
                aVar.f2240m = ((ByteBuffer) aVar.f2241n).getShort(i13);
                return aVar;
            }
            aVar.f2238i = 0;
            aVar.f2239l = 0;
            aVar.f2240m = 0;
        }
        return aVar;
    }

    public final String toString() {
        int i10;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append(", id:");
        androidx.emoji2.text.flatbuffer.a aVarB = b();
        int iA = aVarB.a(4);
        sb2.append(Integer.toHexString(iA != 0 ? ((ByteBuffer) aVarB.f2241n).getInt(iA + aVarB.f2238i) : 0));
        sb2.append(", codepoints:");
        androidx.emoji2.text.flatbuffer.a aVarB2 = b();
        int iA2 = aVarB2.a(16);
        if (iA2 != 0) {
            int i11 = iA2 + aVarB2.f2238i;
            i10 = ((ByteBuffer) aVarB2.f2241n).getInt(((ByteBuffer) aVarB2.f2241n).getInt(i11) + i11);
        } else {
            i10 = 0;
        }
        for (int i12 = 0; i12 < i10; i12++) {
            sb2.append(Integer.toHexString(a(i12)));
            sb2.append(" ");
        }
        return sb2.toString();
    }
}
