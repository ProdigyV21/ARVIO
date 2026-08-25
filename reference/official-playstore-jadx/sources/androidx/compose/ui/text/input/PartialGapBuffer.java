package androidx.compose.ui.text.input;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011\"\u0004\b\u0014\u0010\u0005R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0011\u0010\u001d\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/text/input/PartialGapBuffer;", "", "", "text", "<init>", "(Ljava/lang/String;)V", "", TtmlNode.START, TtmlNode.END, "Lx6/t0;", "replace", "(IILjava/lang/String;)V", "index", "", "get", "(I)C", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getText", "setText", "Landroidx/compose/ui/text/input/GapBuffer;", "buffer", "Landroidx/compose/ui/text/input/GapBuffer;", "bufStart", "I", "bufEnd", "getLength", "()I", "length", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PartialGapBuffer {
    public static final int BUF_SIZE = 255;
    public static final int NOWHERE = -1;
    public static final int SURROUNDING_SIZE = 64;
    private GapBuffer buffer;
    private String text;
    public static final int $stable = 8;
    private int bufStart = -1;
    private int bufEnd = -1;

    public PartialGapBuffer(String str) {
        this.text = str;
    }

    public final char get(int index) {
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer == null) {
            return this.text.charAt(index);
        }
        if (index < this.bufStart) {
            return this.text.charAt(index);
        }
        int length = gapBuffer.length();
        int i10 = this.bufStart;
        return index < length + i10 ? gapBuffer.get(index - i10) : this.text.charAt(index - ((length - this.bufEnd) + i10));
    }

    public final int getLength() {
        GapBuffer gapBuffer = this.buffer;
        return gapBuffer == null ? this.text.length() : (this.text.length() - (this.bufEnd - this.bufStart)) + gapBuffer.length();
    }

    public final String getText() {
        return this.text;
    }

    public final void replace(int start, int end, String text) {
        if (start > end) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(start, end, "start index must be less than or equal to end index: ", " > ").toString());
        }
        if (start < 0) {
            throw new IllegalArgumentException(a0.c.i(start, "start must be non-negative, but was ").toString());
        }
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer != null) {
            int i10 = this.bufStart;
            int i11 = start - i10;
            int i12 = end - i10;
            if (i11 >= 0 && i12 <= gapBuffer.length()) {
                gapBuffer.replace(i11, i12, text);
                return;
            }
            this.text = toString();
            this.buffer = null;
            this.bufStart = -1;
            this.bufEnd = -1;
            replace(start, end, text);
            return;
        }
        int iMax = Math.max(255, text.length() + 128);
        char[] cArr = new char[iMax];
        int iMin = Math.min(start, 64);
        int iMin2 = Math.min(this.text.length() - end, 64);
        int i13 = start - iMin;
        GapBuffer_jvmKt.toCharArray(this.text, cArr, 0, i13, start);
        int i14 = iMax - iMin2;
        int i15 = iMin2 + end;
        GapBuffer_jvmKt.toCharArray(this.text, cArr, i14, end, i15);
        GapBufferKt.toCharArray(text, cArr, iMin);
        this.buffer = new GapBuffer(cArr, text.length() + iMin, i14);
        this.bufStart = i13;
        this.bufEnd = i15;
    }

    public final void setText(String str) {
        this.text = str;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public String toString() {
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer == null) {
            return this.text;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) this.text, 0, this.bufStart);
        gapBuffer.append(sb2);
        String str = this.text;
        sb2.append((CharSequence) str, this.bufEnd, str.length());
        return sb2.toString();
    }
}
