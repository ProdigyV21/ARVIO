package eb;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

/* JADX INFO: loaded from: classes5.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InputStream f15152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharsetDecoder f15153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ByteBuffer f15154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f15155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public char f15156e;

    public j(InputStream inputStream, Charset charset) {
        byte[] bArr;
        this.f15152a = inputStream;
        CharsetDecoder charsetDecoderNewDecoder = charset.newDecoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
        this.f15153b = charsetDecoderNewDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        f fVar = f.f15128l;
        synchronized (fVar) {
            kotlin.collections.p pVar = (kotlin.collections.p) fVar.f6607i;
            byte[] bArr2 = (byte[]) (pVar.isEmpty() ? null : pVar.removeLast());
            bArr = bArr2 != null ? bArr2 : null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr == null ? new byte[8196] : bArr);
        this.f15154c = byteBufferWrap;
        byteBufferWrap.flip();
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e8, code lost:
    
        r2 = r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(char[] r11, int r12, int r13) throws java.nio.charset.CharacterCodingException {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: eb.j.a(char[], int, int):int");
    }
}
