package androidx.media3.extractor.metadata.icy;

import androidx.media3.common.Metadata;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
public final class IcyDecoder extends SimpleMetadataDecoder {
    private static final Pattern METADATA_ELEMENT = Pattern.compile("(.+?)='(.*?)';", 32);
    private static final String STREAM_KEY_NAME = "streamtitle";
    private static final String STREAM_KEY_URL = "streamurl";
    private final CharsetDecoder utf8Decoder = StandardCharsets.UTF_8.newDecoder();
    private final CharsetDecoder iso88591Decoder = StandardCharsets.ISO_8859_1.newDecoder();

    private String decodeToString(ByteBuffer byteBuffer) {
        try {
            return this.utf8Decoder.decode(byteBuffer).toString();
        } catch (CharacterCodingException unused) {
            try {
                String string = this.iso88591Decoder.decode(byteBuffer).toString();
                this.iso88591Decoder.reset();
                byteBuffer.rewind();
                return string;
            } catch (CharacterCodingException unused2) {
                this.iso88591Decoder.reset();
                byteBuffer.rewind();
                return null;
            } catch (Throwable th) {
                this.iso88591Decoder.reset();
                byteBuffer.rewind();
                throw th;
            }
        } finally {
            this.utf8Decoder.reset();
            byteBuffer.rewind();
        }
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        String strDecodeToString = decodeToString(byteBuffer);
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr);
        String str = null;
        if (strDecodeToString == null) {
            return new Metadata(new IcyInfo(bArr, null, null));
        }
        Matcher matcher = METADATA_ELEMENT.matcher(strDecodeToString);
        String str2 = null;
        for (int iEnd = 0; matcher.find(iEnd); iEnd = matcher.end()) {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            if (strGroup != null) {
                String strS = a.S(strGroup);
                strS.getClass();
                if (strS.equals(STREAM_KEY_URL)) {
                    str2 = strGroup2;
                } else if (strS.equals(STREAM_KEY_NAME)) {
                    str = strGroup2;
                }
            }
        }
        return new Metadata(new IcyInfo(bArr, str, str2));
    }
}
