package com.google.firebase.crashlytics.internal.metadata;

import androidx.compose.material3.d;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
class QueueFile implements Closeable {
    static final int HEADER_LENGTH = 16;
    private static final int INITIAL_LENGTH = 4096;
    private static final Logger LOGGER = Logger.getLogger(QueueFile.class.getName());
    private final byte[] buffer;
    private int elementCount;
    int fileLength;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    public static class Element {
        static final int HEADER_LENGTH = 4;
        static final Element NULL = new Element(0, 0);
        final int length;
        final int position;

        public Element(int i10, int i11) {
            this.position = i10;
            this.length = i11;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getClass().getSimpleName());
            sb2.append("[position = ");
            sb2.append(this.position);
            sb2.append(", length = ");
            return d.j(this.length, "]", sb2);
        }
    }

    public final class ElementInputStream extends InputStream {
        private int position;
        private int remaining;

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i10, int i11) throws IOException {
            QueueFile.nonNull(bArr, "buffer");
            if ((i10 | i11) < 0 || i11 > bArr.length - i10) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i12 = this.remaining;
            if (i12 <= 0) {
                return -1;
            }
            if (i11 > i12) {
                i11 = i12;
            }
            QueueFile.this.ringRead(this.position, bArr, i10, i11);
            this.position = QueueFile.this.wrapPosition(this.position + i11);
            this.remaining -= i11;
            return i11;
        }

        private ElementInputStream(Element element) {
            this.position = QueueFile.this.wrapPosition(element.position + 4);
            this.remaining = element.length;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.remaining == 0) {
                return -1;
            }
            QueueFile.this.raf.seek(this.position);
            int i10 = QueueFile.this.raf.read();
            this.position = QueueFile.this.wrapPosition(this.position + 1);
            this.remaining--;
            return i10;
        }
    }

    public interface ElementReader {
        void read(InputStream inputStream, int i10) throws IOException;
    }

    public QueueFile(File file) throws IOException {
        this.buffer = new byte[16];
        if (!file.exists()) {
            initialize(file);
        }
        this.raf = open(file);
        readHeader();
    }

    private void expandIfNecessary(int i10) throws IOException {
        int i11 = i10 + 4;
        int iRemainingBytes = remainingBytes();
        if (iRemainingBytes >= i11) {
            return;
        }
        int i12 = this.fileLength;
        do {
            iRemainingBytes += i12;
            i12 <<= 1;
        } while (iRemainingBytes < i11);
        setLength(i12);
        Element element = this.last;
        int iWrapPosition = wrapPosition(element.position + 4 + element.length);
        if (iWrapPosition < this.first.position) {
            FileChannel channel = this.raf.getChannel();
            channel.position(this.fileLength);
            long j10 = iWrapPosition - 4;
            if (channel.transferTo(16L, j10, channel) != j10) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        int i13 = this.last.position;
        int i14 = this.first.position;
        if (i13 < i14) {
            int i15 = (this.fileLength + i13) - 16;
            writeHeader(i12, this.elementCount, i14, i15);
            this.last = new Element(i15, this.last.length);
        } else {
            writeHeader(i12, this.elementCount, i14, i13);
        }
        this.fileLength = i12;
    }

    private static void initialize(File file) throws IOException {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile randomAccessFileOpen = open(file2);
        try {
            randomAccessFileOpen.setLength(4096L);
            randomAccessFileOpen.seek(0L);
            byte[] bArr = new byte[16];
            writeInts(bArr, 4096, 0, 0, 0);
            randomAccessFileOpen.write(bArr);
            randomAccessFileOpen.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            randomAccessFileOpen.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T nonNull(T t2, String str) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(str);
    }

    private static RandomAccessFile open(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    private Element readElement(int i10) throws IOException {
        if (i10 == 0) {
            return Element.NULL;
        }
        this.raf.seek(i10);
        return new Element(i10, this.raf.readInt());
    }

    private void readHeader() throws IOException {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        int i10 = readInt(this.buffer, 0);
        this.fileLength = i10;
        if (i10 > this.raf.length()) {
            throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
        }
        this.elementCount = readInt(this.buffer, 4);
        int i11 = readInt(this.buffer, 8);
        int i12 = readInt(this.buffer, 12);
        this.first = readElement(i11);
        this.last = readElement(i12);
    }

    private static int readInt(byte[] bArr, int i10) {
        return ((bArr[i10] & 255) << 24) + ((bArr[i10 + 1] & 255) << 16) + ((bArr[i10 + 2] & 255) << 8) + (bArr[i10 + 3] & 255);
    }

    private int remainingBytes() {
        return this.fileLength - usedBytes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ringRead(int i10, byte[] bArr, int i11, int i12) throws IOException {
        int iWrapPosition = wrapPosition(i10);
        int i13 = iWrapPosition + i12;
        int i14 = this.fileLength;
        if (i13 <= i14) {
            this.raf.seek(iWrapPosition);
            this.raf.readFully(bArr, i11, i12);
            return;
        }
        int i15 = i14 - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.readFully(bArr, i11, i15);
        this.raf.seek(16L);
        this.raf.readFully(bArr, i11 + i15, i12 - i15);
    }

    private void ringWrite(int i10, byte[] bArr, int i11, int i12) throws IOException {
        int iWrapPosition = wrapPosition(i10);
        int i13 = iWrapPosition + i12;
        int i14 = this.fileLength;
        if (i13 <= i14) {
            this.raf.seek(iWrapPosition);
            this.raf.write(bArr, i11, i12);
            return;
        }
        int i15 = i14 - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.write(bArr, i11, i15);
        this.raf.seek(16L);
        this.raf.write(bArr, i11 + i15, i12 - i15);
    }

    private void setLength(int i10) throws IOException {
        this.raf.setLength(i10);
        this.raf.getChannel().force(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int wrapPosition(int i10) {
        int i11 = this.fileLength;
        return i10 < i11 ? i10 : (i10 + 16) - i11;
    }

    private void writeHeader(int i10, int i11, int i12, int i13) throws IOException {
        writeInts(this.buffer, i10, i11, i12, i13);
        this.raf.seek(0L);
        this.raf.write(this.buffer);
    }

    private static void writeInt(byte[] bArr, int i10, int i11) {
        bArr[i10] = (byte) (i11 >> 24);
        bArr[i10 + 1] = (byte) (i11 >> 16);
        bArr[i10 + 2] = (byte) (i11 >> 8);
        bArr[i10 + 3] = (byte) i11;
    }

    private static void writeInts(byte[] bArr, int... iArr) {
        int i10 = 0;
        for (int i11 : iArr) {
            writeInt(bArr, i10, i11);
            i10 += 4;
        }
    }

    public void add(byte[] bArr) throws IOException {
        add(bArr, 0, bArr.length);
    }

    public synchronized void clear() throws IOException {
        try {
            writeHeader(4096, 0, 0, 0);
            this.elementCount = 0;
            Element element = Element.NULL;
            this.first = element;
            this.last = element;
            if (this.fileLength > 4096) {
                setLength(4096);
            }
            this.fileLength = 4096;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.raf.close();
    }

    public synchronized void forEach(ElementReader elementReader) throws IOException {
        int iWrapPosition = this.first.position;
        for (int i10 = 0; i10 < this.elementCount; i10++) {
            Element element = readElement(iWrapPosition);
            elementReader.read(new ElementInputStream(element), element.length);
            iWrapPosition = wrapPosition(element.position + 4 + element.length);
        }
    }

    public boolean hasSpaceFor(int i10, int i11) {
        return (usedBytes() + 4) + i10 <= i11;
    }

    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    public synchronized byte[] peek() throws IOException {
        if (isEmpty()) {
            return null;
        }
        Element element = this.first;
        int i10 = element.length;
        byte[] bArr = new byte[i10];
        ringRead(element.position + 4, bArr, 0, i10);
        return bArr;
    }

    public synchronized void remove() throws IOException {
        try {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            if (this.elementCount == 1) {
                clear();
            } else {
                Element element = this.first;
                int iWrapPosition = wrapPosition(element.position + 4 + element.length);
                ringRead(iWrapPosition, this.buffer, 0, 4);
                int i10 = readInt(this.buffer, 0);
                writeHeader(this.fileLength, this.elementCount - 1, iWrapPosition, this.last.position);
                this.elementCount--;
                this.first = new Element(iWrapPosition, i10);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized int size() {
        return this.elementCount;
    }

    public String toString() {
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(getClass().getSimpleName());
        sb2.append("[fileLength=");
        sb2.append(this.fileLength);
        sb2.append(", size=");
        sb2.append(this.elementCount);
        sb2.append(", first=");
        sb2.append(this.first);
        sb2.append(", last=");
        sb2.append(this.last);
        sb2.append(", element lengths=[");
        try {
            forEach(new ElementReader() { // from class: com.google.firebase.crashlytics.internal.metadata.QueueFile.1
                boolean first = true;

                @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
                public void read(InputStream inputStream, int i10) throws IOException {
                    if (this.first) {
                        this.first = false;
                    } else {
                        sb2.append(", ");
                    }
                    sb2.append(i10);
                }
            });
        } catch (IOException e5) {
            LOGGER.log(Level.WARNING, "read error", (Throwable) e5);
        }
        sb2.append("]]");
        return sb2.toString();
    }

    public int usedBytes() {
        if (this.elementCount == 0) {
            return 16;
        }
        Element element = this.last;
        int i10 = element.position;
        int i11 = this.first.position;
        return i10 >= i11 ? (i10 - i11) + 4 + element.length + 16 : (((i10 + 4) + element.length) + this.fileLength) - i11;
    }

    public synchronized void add(byte[] bArr, int i10, int i11) throws IOException {
        int iWrapPosition;
        try {
            nonNull(bArr, "buffer");
            if ((i10 | i11) < 0 || i11 > bArr.length - i10) {
                throw new IndexOutOfBoundsException();
            }
            expandIfNecessary(i11);
            boolean zIsEmpty = isEmpty();
            if (zIsEmpty) {
                iWrapPosition = 16;
            } else {
                Element element = this.last;
                iWrapPosition = wrapPosition(element.position + 4 + element.length);
            }
            Element element2 = new Element(iWrapPosition, i11);
            writeInt(this.buffer, 0, i11);
            ringWrite(element2.position, this.buffer, 0, 4);
            ringWrite(element2.position + 4, bArr, i10, i11);
            writeHeader(this.fileLength, this.elementCount + 1, zIsEmpty ? element2.position : this.first.position, element2.position);
            this.last = element2;
            this.elementCount++;
            if (zIsEmpty) {
                this.first = element2;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public QueueFile(RandomAccessFile randomAccessFile) throws IOException {
        this.buffer = new byte[16];
        this.raf = randomAccessFile;
        readHeader();
    }

    public synchronized void peek(ElementReader elementReader) throws IOException {
        if (this.elementCount > 0) {
            elementReader.read(new ElementInputStream(this.first), this.first.length);
        }
    }
}
