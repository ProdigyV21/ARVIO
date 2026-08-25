package androidx.media3.decoder;

/* JADX INFO: loaded from: classes3.dex */
public class CryptoException extends Exception {
    public final int errorCode;

    public CryptoException(int i10, String str) {
        super(str);
        this.errorCode = i10;
    }
}
