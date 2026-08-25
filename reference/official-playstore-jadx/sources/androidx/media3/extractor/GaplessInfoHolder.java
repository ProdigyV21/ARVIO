package androidx.media3.extractor;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.id3.CommentFrame;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import com.google.common.collect.f1;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class GaplessInfoHolder {
    private static final Pattern GAPLESS_COMMENT_PATTERN = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    private static final String GAPLESS_DESCRIPTION = "iTunSMPB";
    private static final String GAPLESS_DOMAIN = "com.apple.iTunes";
    public int encoderDelay = -1;
    public int encoderPadding = -1;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setFromMetadata$0(CommentFrame commentFrame) {
        return commentFrame.description.equals(GAPLESS_DESCRIPTION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setFromMetadata$1(InternalFrame internalFrame) {
        return internalFrame.domain.equals(GAPLESS_DOMAIN) && internalFrame.description.equals(GAPLESS_DESCRIPTION);
    }

    private boolean setFromComment(String str) {
        Matcher matcher = GAPLESS_COMMENT_PATTERN.matcher(str);
        if (!matcher.find()) {
            return false;
        }
        try {
            int i10 = Integer.parseInt((String) Util.castNonNull(matcher.group(1)), 16);
            int i11 = Integer.parseInt((String) Util.castNonNull(matcher.group(2)), 16);
            if (i10 <= 0 && i11 <= 0) {
                return false;
            }
            this.encoderDelay = i10;
            this.encoderPadding = i11;
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public boolean hasGaplessInfo() {
        return (this.encoderDelay == -1 || this.encoderPadding == -1) ? false : true;
    }

    public boolean setFromMetadata(Metadata metadata) {
        f1 f1VarListIterator = metadata.getMatchingEntries(CommentFrame.class, new d(0)).listIterator(0);
        while (f1VarListIterator.hasNext()) {
            if (setFromComment(((CommentFrame) f1VarListIterator.next()).text)) {
                return true;
            }
        }
        f1 f1VarListIterator2 = metadata.getMatchingEntries(InternalFrame.class, new d(1)).listIterator(0);
        while (f1VarListIterator2.hasNext()) {
            if (setFromComment(((InternalFrame) f1VarListIterator2.next()).text)) {
                return true;
            }
        }
        return false;
    }
}
