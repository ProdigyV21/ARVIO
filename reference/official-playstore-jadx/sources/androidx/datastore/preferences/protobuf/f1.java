package androidx.datastore.preferences.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f2421a = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f2422b;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        f2422b = bArr;
        ByteBuffer.wrap(bArr);
        if (0 + 0 <= Integer.MAX_VALUE) {
            return;
        }
        try {
            throw InvalidProtocolBufferException.f();
        } catch (InvalidProtocolBufferException e5) {
            throw new IllegalArgumentException(e5);
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static int b(long j10) {
        return (int) (j10 ^ (j10 >>> 32));
    }

    public static v1 c(Object obj, Object obj2) {
        o0 o0VarC = ((v1) obj).c();
        v1 v1Var = (v1) obj2;
        if (!o0VarC.f2482i.getClass().isInstance(v1Var)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        o0VarC.h();
        o0.i(o0VarC.f2483l, (r0) ((c) v1Var));
        return o0VarC.g();
    }
}
