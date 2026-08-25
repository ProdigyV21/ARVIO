package l7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.collections.x;
import kotlin.io.FileAlreadyExistsException;
import kotlin.io.FileSystemException;
import kotlin.io.NoSuchFileException;
import kotlin.jvm.internal.p;
import kotlin.text.o;

/* JADX INFO: loaded from: classes5.dex */
public abstract class i extends a.a {
    public static void o0(File file, File file2) throws IOException {
        if (!file.exists()) {
            throw new NoSuchFileException(file, null, "The source file doesn't exist.");
        }
        if (file2.exists() && !file2.delete()) {
            throw new FileAlreadyExistsException(file, file2, "Tried to overwrite the destination, but failed to delete it.");
        }
        if (file.isDirectory()) {
            if (!file2.mkdirs()) {
                throw new FileSystemException(file, file2, "Failed to create target directory.");
            }
            return;
        }
        File parentFile = file2.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                t7.a.k(fileInputStream, fileOutputStream);
                fileOutputStream.close();
                fileInputStream.close();
            } finally {
            }
        } finally {
        }
    }

    public static void p0(File file) {
        f fVar = new f(new h(file));
        while (true) {
            boolean z = true;
            while (fVar.hasNext()) {
                File file2 = (File) fVar.next();
                if (!file2.delete() && file2.exists()) {
                    z = false;
                } else if (z) {
                    break;
                } else {
                    z = false;
                }
            }
            return;
        }
    }

    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Object, java.util.List] */
    public static File q0(File file) {
        a aVarX = a.a.X(file);
        File file2 = aVarX.f20043a;
        ?? r72 = aVarX.f20044b;
        ArrayList arrayList = new ArrayList(r72.size());
        for (File file3 : r72) {
            String name = file3.getName();
            if (!p.a(name, ".")) {
                if (!p.a(name, "..")) {
                    arrayList.add(file3);
                } else if (arrayList.isEmpty() || p.a(((File) x.w0(arrayList)).getName(), "..")) {
                    arrayList.add(file3);
                }
            }
        }
        return t0(file2, new File(x.u0(arrayList, File.separator, null, null, null, 62)));
    }

    public static byte[] r0(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            long length = file.length();
            if (length > 2147483647L) {
                throw new OutOfMemoryError("File " + file + " is too big (" + length + " bytes) to fit in memory.");
            }
            int i10 = (int) length;
            byte[] bArrCopyOf = new byte[i10];
            int i11 = i10;
            int i12 = 0;
            while (i11 > 0) {
                int i13 = fileInputStream.read(bArrCopyOf, i12, i11);
                if (i13 < 0) {
                    break;
                }
                i11 -= i13;
                i12 += i13;
            }
            if (i11 > 0) {
                bArrCopyOf = Arrays.copyOf(bArrCopyOf, i12);
            } else {
                int i14 = fileInputStream.read();
                if (i14 != -1) {
                    io.sentry.cache.tape.c cVar = new io.sentry.cache.tape.c(8193);
                    cVar.write(i14);
                    t7.a.k(fileInputStream, cVar);
                    int size = cVar.size() + i10;
                    if (size < 0) {
                        throw new OutOfMemoryError("File " + file + " is too big to fit in memory.");
                    }
                    byte[] bArrJ = cVar.j();
                    bArrCopyOf = Arrays.copyOf(bArrCopyOf, size);
                    System.arraycopy(bArrJ, 0, bArrCopyOf, i10, cVar.size());
                }
            }
            fileInputStream.close();
            return bArrCopyOf;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                xc.d.L(fileInputStream, th);
                throw th2;
            }
        }
    }

    public static String s0(File file) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), kotlin.text.a.f19924a);
        try {
            String strU = ac.b.U(inputStreamReader);
            inputStreamReader.close();
            return strU;
        } finally {
        }
    }

    public static File t0(File file, File file2) {
        if (a.a.S(file2.getPath()) > 0) {
            return file2;
        }
        String string = file.toString();
        if (string.length() != 0) {
            char c10 = File.separatorChar;
            if (!o.X(string, c10)) {
                return new File(string + c10 + file2);
            }
        }
        return new File(string + file2);
    }

    public static void u0(File file, byte[] bArr) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } finally {
        }
    }

    public static void v0(File file, String str) {
        Charset charset = kotlin.text.a.f19924a;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            w0(fileOutputStream, str, charset);
            fileOutputStream.close();
        } finally {
        }
    }

    public static final void w0(FileOutputStream fileOutputStream, String str, Charset charset) throws IOException {
        if (str.length() < 16384) {
            fileOutputStream.write(str.getBytes(charset));
            return;
        }
        CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
        CharsetEncoder charsetEncoderOnUnmappableCharacter = charsetEncoderNewEncoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        CharBuffer charBufferAllocate = CharBuffer.allocate(8192);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8192 * ((int) Math.ceil(charsetEncoderOnUnmappableCharacter.maxBytesPerChar())));
        int i10 = 0;
        int i11 = 0;
        while (i10 < str.length()) {
            int iMin = Math.min(8192 - i11, str.length() - i10);
            int i12 = i10 + iMin;
            str.getChars(i10, i12, charBufferAllocate.array(), i11);
            charBufferAllocate.limit(iMin + i11);
            i11 = 1;
            if (!charsetEncoderOnUnmappableCharacter.encode(charBufferAllocate, byteBufferAllocate, i12 == str.length()).isUnderflow()) {
                throw new IllegalStateException("Check failed.");
            }
            fileOutputStream.write(byteBufferAllocate.array(), 0, byteBufferAllocate.position());
            if (charBufferAllocate.position() != charBufferAllocate.limit()) {
                charBufferAllocate.put(0, charBufferAllocate.get());
            } else {
                i11 = 0;
            }
            charBufferAllocate.clear();
            byteBufferAllocate.clear();
            i10 = i12;
        }
    }
}
