package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d implements e0 {
    static {
        int i10 = l.f19804b;
    }

    public static void c(c0 c0Var) throws InvalidProtocolBufferException {
        if (c0Var == null || c0Var.isInitialized()) {
            return;
        }
        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException((c0Var instanceof c ? new UninitializedMessageException() : new UninitializedMessageException()).getMessage());
        invalidProtocolBufferException.f19759i = c0Var;
        throw invalidProtocolBufferException;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.e0
    public final c0 a(ByteArrayInputStream byteArrayInputStream, l lVar) throws InvalidProtocolBufferException {
        c0 c0Var;
        try {
            int i10 = byteArrayInputStream.read();
            if (i10 == -1) {
                c0Var = null;
            } else {
                if ((i10 & 128) != 0) {
                    i10 &= 127;
                    int i11 = 7;
                    while (true) {
                        if (i11 >= 32) {
                            while (i11 < 64) {
                                int i12 = byteArrayInputStream.read();
                                if (i12 == -1) {
                                    throw InvalidProtocolBufferException.a();
                                }
                                if ((i12 & 128) != 0) {
                                    i11 += 7;
                                }
                            }
                            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
                        }
                        int i13 = byteArrayInputStream.read();
                        if (i13 == -1) {
                            throw InvalidProtocolBufferException.a();
                        }
                        i10 |= (i13 & 127) << i11;
                        if ((i13 & 128) == 0) {
                            break;
                        }
                        i11 += 7;
                    }
                }
                i iVar = new i(new a(byteArrayInputStream, i10));
                c0 c0Var2 = (c0) b(iVar, lVar);
                try {
                    iVar.a(0);
                    c0Var = c0Var2;
                } catch (InvalidProtocolBufferException e5) {
                    e5.f19759i = c0Var2;
                    throw e5;
                }
            }
            c(c0Var);
            return c0Var;
        } catch (IOException e6) {
            throw new InvalidProtocolBufferException(e6.getMessage());
        }
    }
}
