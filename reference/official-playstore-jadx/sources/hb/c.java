package hb;

import java.io.EOFException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import xb.j;
import xb.m;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15970a = 0;

    static {
        Charset charset = StandardCharsets.US_ASCII;
    }

    public static ArrayList a(String str, m mVar) throws EOFException, UnknownHostException {
        ArrayList arrayList = new ArrayList();
        j jVar = new j();
        jVar.J0(mVar);
        jVar.readShort();
        short s10 = jVar.readShort();
        if (((s10 & 65535) >> 15) == 0) {
            throw new IllegalArgumentException("not a response");
        }
        int i10 = s10 & 15;
        if (i10 == 2) {
            throw new UnknownHostException(str.concat(": SERVFAIL"));
        }
        if (i10 == 3) {
            throw new UnknownHostException(str.concat(": NXDOMAIN"));
        }
        int i11 = jVar.readShort() & 65535;
        int i12 = jVar.readShort() & 65535;
        jVar.readShort();
        jVar.readShort();
        for (int i13 = 0; i13 < i11; i13++) {
            byte b10 = jVar.readByte();
            if (b10 < 0) {
                jVar.skip(1L);
            } else {
                while (b10 > 0) {
                    jVar.skip(b10);
                    b10 = jVar.readByte();
                }
            }
            jVar.readShort();
            jVar.readShort();
        }
        for (int i14 = 0; i14 < i12; i14++) {
            byte b11 = jVar.readByte();
            if (b11 < 0) {
                jVar.skip(1L);
            } else {
                while (b11 > 0) {
                    jVar.skip(b11);
                    b11 = jVar.readByte();
                }
            }
            int i15 = jVar.readShort() & 65535;
            jVar.readShort();
            jVar.readInt();
            int i16 = jVar.readShort() & 65535;
            if (i15 == 1 || i15 == 28) {
                byte[] bArr = new byte[i16];
                jVar.read(bArr, 0, i16);
                arrayList.add(InetAddress.getByAddress(bArr));
            } else {
                jVar.skip(i16);
            }
        }
        return arrayList;
    }
}
