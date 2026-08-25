package androidx.media3.datasource;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public final class AesFlushingCipher {
    private final int blockSize;
    private final Cipher cipher;
    private final byte[] flushedBlock;
    private int pendingXorBytes;
    private final byte[] zerosBlock;

    public AesFlushingCipher(int i10, byte[] bArr, String str, long j10) {
        this(i10, bArr, getFNV64Hash(str), j10);
    }

    private static long getFNV64Hash(String str) {
        long j10 = 0;
        if (str == null) {
            return 0L;
        }
        for (int i10 = 0; i10 < str.length(); i10++) {
            long jCharAt = j10 ^ ((long) str.charAt(i10));
            j10 = jCharAt + (jCharAt << 1) + (jCharAt << 4) + (jCharAt << 5) + (jCharAt << 7) + (jCharAt << 8) + (jCharAt << 40);
        }
        return j10;
    }

    private byte[] getInitializationVector(long j10, long j11) {
        return ByteBuffer.allocate(16).putLong(j10).putLong(j11).array();
    }

    private int nonFlushingUpdate(byte[] bArr, int i10, int i11, byte[] bArr2, int i12) {
        try {
            return this.cipher.update(bArr, i10, i11, bArr2, i12);
        } catch (ShortBufferException e5) {
            throw new RuntimeException(e5);
        }
    }

    public void update(byte[] bArr, int i10, int i11, byte[] bArr2, int i12) {
        int i13 = i10;
        int i14 = i11;
        int i15 = i12;
        do {
            int i16 = this.pendingXorBytes;
            if (i16 <= 0) {
                int iNonFlushingUpdate = nonFlushingUpdate(bArr, i13, i14, bArr2, i15);
                if (i14 == iNonFlushingUpdate) {
                    return;
                }
                int i17 = i14 - iNonFlushingUpdate;
                int i18 = 0;
                ac.b.s(i17 < this.blockSize);
                int i19 = i15 + iNonFlushingUpdate;
                int i20 = this.blockSize - i17;
                this.pendingXorBytes = i20;
                ac.b.s(nonFlushingUpdate(this.zerosBlock, 0, i20, this.flushedBlock, 0) == this.blockSize);
                while (i18 < i17) {
                    bArr2[i19] = this.flushedBlock[i18];
                    i18++;
                    i19++;
                }
                return;
            }
            bArr2[i15] = (byte) (bArr[i13] ^ this.flushedBlock[this.blockSize - i16]);
            i15++;
            i13++;
            this.pendingXorBytes = i16 - 1;
            i14--;
        } while (i14 != 0);
    }

    public void updateInPlace(byte[] bArr, int i10, int i11) {
        update(bArr, i10, i11, bArr, i10);
    }

    public AesFlushingCipher(int i10, byte[] bArr, long j10, long j11) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            this.cipher = cipher;
            int blockSize = cipher.getBlockSize();
            this.blockSize = blockSize;
            this.zerosBlock = new byte[blockSize];
            this.flushedBlock = new byte[blockSize];
            long j12 = j11 / ((long) blockSize);
            int i11 = (int) (j11 % ((long) blockSize));
            cipher.init(i10, new SecretKeySpec(bArr, Util.splitAtFirst(cipher.getAlgorithm(), DomExceptionUtils.SEPARATOR)[0]), new IvParameterSpec(getInitializationVector(j10, j12)));
            if (i11 != 0) {
                updateInPlace(new byte[i11], 0, i11);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e5) {
            throw new RuntimeException(e5);
        }
    }
}
