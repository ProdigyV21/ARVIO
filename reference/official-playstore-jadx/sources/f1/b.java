package f1;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public class b extends InputStream implements DataInput {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final ByteOrder f15207o = ByteOrder.LITTLE_ENDIAN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ByteOrder f15208p = ByteOrder.BIG_ENDIAN;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final DataInputStream f15209i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ByteOrder f15210l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15211m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public byte[] f15212n;

    public b(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        this(byteArrayInputStream, 0);
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.f15209i.available();
    }

    public final void i(int i10) throws IOException {
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i10 - i11;
            DataInputStream dataInputStream = this.f15209i;
            int iSkip = (int) dataInputStream.skip(i12);
            if (iSkip <= 0) {
                if (this.f15212n == null) {
                    this.f15212n = new byte[8192];
                }
                iSkip = dataInputStream.read(this.f15212n, 0, Math.min(8192, i12));
                if (iSkip == -1) {
                    throw new EOFException(androidx.compose.foundation.c.o(i10, "Reached EOF while skipping ", " bytes."));
                }
            }
            i11 += iSkip;
        }
        this.f15211m += i11;
    }

    @Override // java.io.InputStream
    public final void mark(int i10) {
        throw new UnsupportedOperationException("Mark is currently unsupported");
    }

    @Override // java.io.InputStream
    public final int read() {
        this.f15211m++;
        return this.f15209i.read();
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() {
        this.f15211m++;
        return this.f15209i.readBoolean();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        this.f15211m++;
        int i10 = this.f15209i.read();
        if (i10 >= 0) {
            return (byte) i10;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final char readChar() {
        this.f15211m += 2;
        return this.f15209i.readChar();
    }

    @Override // java.io.DataInput
    public final double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i10, int i11) throws IOException {
        this.f15211m += i11;
        this.f15209i.readFully(bArr, i10, i11);
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        this.f15211m += 4;
        DataInputStream dataInputStream = this.f15209i;
        int i10 = dataInputStream.read();
        int i11 = dataInputStream.read();
        int i12 = dataInputStream.read();
        int i13 = dataInputStream.read();
        if ((i10 | i11 | i12 | i13) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f15210l;
        if (byteOrder == f15207o) {
            return (i13 << 24) + (i12 << 16) + (i11 << 8) + i10;
        }
        if (byteOrder == f15208p) {
            return (i10 << 24) + (i11 << 16) + (i12 << 8) + i13;
        }
        throw new IOException("Invalid byte order: " + this.f15210l);
    }

    @Override // java.io.DataInput
    public final String readLine() {
        Log.d("ExifInterface", "Currently unsupported");
        return null;
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        long j10;
        long j11;
        this.f15211m += 8;
        DataInputStream dataInputStream = this.f15209i;
        int i10 = dataInputStream.read();
        int i11 = dataInputStream.read();
        int i12 = dataInputStream.read();
        int i13 = dataInputStream.read();
        int i14 = dataInputStream.read();
        int i15 = dataInputStream.read();
        int i16 = dataInputStream.read();
        int i17 = dataInputStream.read();
        if ((i10 | i11 | i12 | i13 | i14 | i15 | i16 | i17) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f15210l;
        if (byteOrder == f15207o) {
            j10 = (((long) i17) << 56) + (((long) i16) << 48) + (((long) i15) << 40) + (((long) i14) << 32) + (((long) i13) << 24) + (((long) i12) << 16) + (((long) i11) << 8);
            j11 = i10;
        } else {
            if (byteOrder != f15208p) {
                throw new IOException("Invalid byte order: " + this.f15210l);
            }
            j10 = (((long) i10) << 56) + (((long) i11) << 48) + (((long) i12) << 40) + (((long) i13) << 32) + (((long) i14) << 24) + (((long) i15) << 16) + (((long) i16) << 8);
            j11 = i17;
        }
        return j10 + j11;
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        this.f15211m += 2;
        DataInputStream dataInputStream = this.f15209i;
        int i10 = dataInputStream.read();
        int i11 = dataInputStream.read();
        if ((i10 | i11) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f15210l;
        if (byteOrder == f15207o) {
            return (short) ((i11 << 8) + i10);
        }
        if (byteOrder == f15208p) {
            return (short) ((i10 << 8) + i11);
        }
        throw new IOException("Invalid byte order: " + this.f15210l);
    }

    @Override // java.io.DataInput
    public final String readUTF() {
        this.f15211m += 2;
        return this.f15209i.readUTF();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() {
        this.f15211m++;
        return this.f15209i.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        this.f15211m += 2;
        DataInputStream dataInputStream = this.f15209i;
        int i10 = dataInputStream.read();
        int i11 = dataInputStream.read();
        if ((i10 | i11) < 0) {
            throw new EOFException();
        }
        ByteOrder byteOrder = this.f15210l;
        if (byteOrder == f15207o) {
            return (i11 << 8) + i10;
        }
        if (byteOrder == f15208p) {
            return (i10 << 8) + i11;
        }
        throw new IOException("Invalid byte order: " + this.f15210l);
    }

    @Override // java.io.InputStream
    public final void reset() {
        throw new UnsupportedOperationException("Reset is currently unsupported");
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i10) {
        throw new UnsupportedOperationException("skipBytes is currently unsupported");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(InputStream inputStream) {
        this(inputStream, 0);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
    }

    public b(InputStream inputStream, int i10) {
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        this.f15210l = byteOrder;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f15209i = dataInputStream;
        dataInputStream.mark(0);
        this.f15211m = 0;
        this.f15210l = byteOrder;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i10, int i11) throws IOException {
        int i12 = this.f15209i.read(bArr, i10, i11);
        this.f15211m += i12;
        return i12;
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws IOException {
        this.f15211m += bArr.length;
        this.f15209i.readFully(bArr);
    }
}
