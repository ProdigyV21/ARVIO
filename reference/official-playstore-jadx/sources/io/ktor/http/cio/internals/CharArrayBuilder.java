package io.ktor.http.cio.internals;

import a0.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.utils.io.pool.ObjectPool;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001GB\u0017\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0019\u0010\u0018J/\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010\"\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010!\u001a\u00020\tH\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\tH\u0002¢\u0006\u0004\b$\u0010%J\u0018\u0010&\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b&\u0010\rJ\u001f\u0010'\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0016¢\u0006\u0004\b'\u0010\u0011J\u000f\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*J\u001a\u0010,\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010+H\u0096\u0002¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\tH\u0016¢\u0006\u0004\b.\u0010%J\u001b\u00100\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010/\u001a\u00020\u000bH\u0016¢\u0006\u0004\b0\u00101J-\u00100\u001a\u00060\u0002j\u0002`\u00032\b\u0010/\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0016¢\u0006\u0004\b0\u00102J\u001d\u00100\u001a\u00060\u0002j\u0002`\u00032\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u0016¢\u0006\u0004\b0\u00103J\r\u00105\u001a\u000204¢\u0006\u0004\b5\u00106R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u00107\u001a\u0004\b8\u00109R\u001e\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010:8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010?\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010A\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010DR$\u0010\u001d\u001a\u00020\t2\u0006\u0010E\u001a\u00020\t8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\u001d\u0010D\u001a\u0004\bF\u0010%¨\u0006H"}, d2 = {"Lio/ktor/http/cio/internals/CharArrayBuilder;", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Lio/ktor/utils/io/pool/ObjectPool;", "", "pool", "<init>", "(Lio/ktor/utils/io/pool/ObjectPool;)V", "", "index", "", "getImpl", "(I)C", "startIndex", "endIndex", "copy", "(II)Ljava/lang/CharSequence;", "bufferForIndex", "(I)[C", "", "throwSingleBuffer", "(I)Ljava/lang/Void;", "nonFullBuffer", "()[C", "appendNewArray", TtmlNode.START, "other", "otherStart", "length", "", "rangeEqualsImpl", "(ILjava/lang/CharSequence;II)Z", TtmlNode.END, "hashCodeImpl", "(II)I", "currentPosition", "()I", "get", "subSequence", "", "toString", "()Ljava/lang/String;", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "value", "append", "(C)Ljava/lang/Appendable;", "(Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "(Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "Lx6/t0;", "release", "()V", "Lio/ktor/utils/io/pool/ObjectPool;", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "", "buffers", "Ljava/util/List;", "current", "[C", "stringified", "Ljava/lang/String;", "released", "Z", "remaining", "I", "<set-?>", "getLength", "SubSequenceImpl", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CharArrayBuilder implements CharSequence, Appendable {
    private List<char[]> buffers;
    private char[] current;
    private int length;
    private final ObjectPool<char[]> pool;
    private boolean released;
    private int remaining;
    private String stringified;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\f\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\u0011\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\u0018\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lio/ktor/http/cio/internals/CharArrayBuilder$SubSequenceImpl;", "", TtmlNode.START, "", TtmlNode.END, "(Lio/ktor/http/cio/internals/CharArrayBuilder;II)V", "getEnd", "()I", "length", "getLength", "getStart", "stringified", "", "equals", "", "other", "", "get", "", "index", "hashCode", "subSequence", "startIndex", "endIndex", "toString", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class SubSequenceImpl implements CharSequence {
        private final int end;
        private final int start;
        private String stringified;

        public SubSequenceImpl(int i10, int i11) {
            this.start = i10;
            this.end = i11;
        }

        @Override // java.lang.CharSequence
        public final /* bridge */ char charAt(int i10) {
            return get(i10);
        }

        public boolean equals(Object other) {
            if (!(other instanceof CharSequence)) {
                return false;
            }
            CharSequence charSequence = (CharSequence) other;
            if (charSequence.length() != length()) {
                return false;
            }
            return CharArrayBuilder.this.rangeEqualsImpl(this.start, charSequence, 0, length());
        }

        public char get(int index) {
            int i10 = this.start + index;
            if (index < 0) {
                throw new IllegalArgumentException(c.i(index, "index is negative: ").toString());
            }
            if (i10 < this.end) {
                return CharArrayBuilder.this.getImpl(i10);
            }
            throw new IllegalArgumentException(c.o(c.s(index, "index (", ") should be less than length ("), length(), ')').toString());
        }

        public final int getEnd() {
            return this.end;
        }

        public int getLength() {
            return this.end - this.start;
        }

        public final int getStart() {
            return this.start;
        }

        public int hashCode() {
            String str = this.stringified;
            return str != null ? str.hashCode() : CharArrayBuilder.this.hashCodeImpl(this.start, this.end);
        }

        @Override // java.lang.CharSequence
        public final /* bridge */ int length() {
            return getLength();
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int startIndex, int endIndex) {
            if (startIndex < 0) {
                throw new IllegalArgumentException(c.i(startIndex, "start is negative: ").toString());
            }
            if (startIndex > endIndex) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.r("start (", startIndex, ") should be less or equal to end (", endIndex, ')').toString());
            }
            int i10 = this.end;
            int i11 = this.start;
            if (endIndex <= i10 - i11) {
                return startIndex == endIndex ? "" : CharArrayBuilder.this.new SubSequenceImpl(startIndex + i11, i11 + endIndex);
            }
            throw new IllegalArgumentException(c.o(new StringBuilder("end should be less than length ("), length(), ')').toString());
        }

        @Override // java.lang.CharSequence
        public String toString() {
            String str = this.stringified;
            if (str != null) {
                return str;
            }
            String string = CharArrayBuilder.this.copy(this.start, this.end).toString();
            this.stringified = string;
            return string;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CharArrayBuilder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private final char[] appendNewArray() {
        char[] cArrBorrow = this.pool.borrow();
        char[] cArr = this.current;
        this.current = cArrBorrow;
        this.remaining = cArrBorrow.length;
        this.released = false;
        if (cArr != null) {
            List<char[]> list = this.buffers;
            List list2 = list;
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                this.buffers = arrayList;
                arrayList.add(cArr);
                list2 = arrayList;
            }
            list2.add(cArrBorrow);
        }
        return cArrBorrow;
    }

    private final char[] bufferForIndex(int index) {
        List<char[]> list = this.buffers;
        if (list != null) {
            return list.get(index / this.current.length);
        }
        if (index >= 2048) {
            throwSingleBuffer(index);
            throw new KotlinNothingValueException();
        }
        char[] cArr = this.current;
        if (cArr != null) {
            return cArr;
        }
        throwSingleBuffer(index);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CharSequence copy(int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder(endIndex - startIndex);
        for (int i10 = startIndex - (startIndex % 2048); i10 < endIndex; i10 += 2048) {
            char[] cArrBufferForIndex = bufferForIndex(i10);
            int iMin = Math.min(endIndex - i10, 2048);
            for (int iMax = Math.max(0, startIndex - i10); iMax < iMin; iMax++) {
                sb2.append(cArrBufferForIndex[iMax]);
            }
        }
        return sb2;
    }

    private final int currentPosition() {
        return this.current.length - this.remaining;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final char getImpl(int index) {
        return bufferForIndex(index)[index % this.current.length];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int hashCodeImpl(int start, int end) {
        int impl = 0;
        while (start < end) {
            impl = (impl * 31) + getImpl(start);
            start++;
        }
        return impl;
    }

    private final char[] nonFullBuffer() {
        return this.remaining == 0 ? appendNewArray() : this.current;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean rangeEqualsImpl(int start, CharSequence other, int otherStart, int length) {
        for (int i10 = 0; i10 < length; i10++) {
            if (getImpl(start + i10) != other.charAt(otherStart + i10)) {
                return false;
            }
        }
        return true;
    }

    private final Void throwSingleBuffer(int index) {
        if (this.released) {
            throw new IllegalStateException("Buffer is already released");
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(index);
        sb2.append(" is not in range [0; ");
        throw new IndexOutOfBoundsException(c.o(sb2, currentPosition(), ')'));
    }

    @Override // java.lang.Appendable
    public Appendable append(char value) {
        char[] cArrNonFullBuffer = nonFullBuffer();
        int length = this.current.length;
        int i10 = this.remaining;
        cArrNonFullBuffer[length - i10] = value;
        this.stringified = null;
        this.remaining = i10 - 1;
        this.length = length() + 1;
        return this;
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i10) {
        return get(i10);
    }

    public boolean equals(Object other) {
        if (!(other instanceof CharSequence)) {
            return false;
        }
        CharSequence charSequence = (CharSequence) other;
        if (length() != charSequence.length()) {
            return false;
        }
        return rangeEqualsImpl(0, charSequence, 0, length());
    }

    public char get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException(c.i(index, "index is negative: ").toString());
        }
        if (index < length()) {
            return getImpl(index);
        }
        StringBuilder sbS = c.s(index, "index ", " is not in range [0, ");
        sbS.append(length());
        sbS.append(')');
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    public int getLength() {
        return this.length;
    }

    public final ObjectPool<char[]> getPool() {
        return this.pool;
    }

    public int hashCode() {
        String str = this.stringified;
        return str != null ? str.hashCode() : hashCodeImpl(0, length());
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    public final void release() {
        List<char[]> list = this.buffers;
        if (list != null) {
            this.current = null;
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                this.pool.recycle(list.get(i10));
            }
        } else {
            char[] cArr = this.current;
            if (cArr != null) {
                this.pool.recycle(cArr);
            }
            this.current = null;
        }
        this.released = true;
        this.buffers = null;
        this.stringified = null;
        this.length = 0;
        this.remaining = 0;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.r("startIndex (", startIndex, ") should be less or equal to endIndex (", endIndex, ')').toString());
        }
        if (startIndex < 0) {
            throw new IllegalArgumentException(c.i(startIndex, "startIndex is negative: ").toString());
        }
        if (endIndex <= length()) {
            return new SubSequenceImpl(startIndex, endIndex);
        }
        StringBuilder sbS = c.s(endIndex, "endIndex (", ") is greater than length (");
        sbS.append(length());
        sbS.append(')');
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    @Override // java.lang.CharSequence
    public String toString() {
        String str = this.stringified;
        if (str != null) {
            return str;
        }
        String string = copy(0, length()).toString();
        this.stringified = string;
        return string;
    }

    public CharArrayBuilder(ObjectPool<char[]> objectPool) {
        this.pool = objectPool;
    }

    public /* synthetic */ CharArrayBuilder(ObjectPool objectPool, int i10, h hVar) {
        this((i10 & 1) != 0 ? CharArrayPoolKt.getCharArrayPool() : objectPool);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence value, int startIndex, int endIndex) {
        if (value == null) {
            return this;
        }
        int i10 = startIndex;
        while (i10 < endIndex) {
            char[] cArrNonFullBuffer = nonFullBuffer();
            int length = cArrNonFullBuffer.length;
            int i11 = this.remaining;
            int i12 = length - i11;
            int iMin = Math.min(endIndex - i10, i11);
            for (int i13 = 0; i13 < iMin; i13++) {
                cArrNonFullBuffer[i12 + i13] = value.charAt(i10 + i13);
            }
            i10 += iMin;
            this.remaining -= iMin;
        }
        this.stringified = null;
        this.length = (endIndex - startIndex) + length();
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence value) {
        return value == null ? this : append(value, 0, value.length());
    }
}
