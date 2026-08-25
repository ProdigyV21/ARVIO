package androidx.media3.extractor.mkv;

import androidx.media3.common.ParserException;
import androidx.media3.extractor.ExtractorInput;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public interface EbmlProcessor {
    public static final int ELEMENT_TYPE_BINARY = 4;
    public static final int ELEMENT_TYPE_FLOAT = 5;
    public static final int ELEMENT_TYPE_MASTER = 1;
    public static final int ELEMENT_TYPE_STRING = 3;
    public static final int ELEMENT_TYPE_UNKNOWN = 0;
    public static final int ELEMENT_TYPE_UNSIGNED_INT = 2;

    @Target({java.lang.annotation.ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ElementType {
    }

    void binaryElement(int i10, int i11, ExtractorInput extractorInput) throws IOException;

    void endMasterElement(int i10) throws ParserException;

    void floatElement(int i10, double d4) throws ParserException;

    int getElementType(int i10);

    void integerElement(int i10, long j10) throws ParserException;

    boolean isLevel1Element(int i10);

    void startMasterElement(int i10, long j10, long j11) throws ParserException;

    void stringElement(int i10, String str) throws ParserException;
}
