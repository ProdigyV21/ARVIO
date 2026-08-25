package androidx.media3.extractor.metadata.flac;

import androidx.compose.foundation.c;
import androidx.media3.common.Format;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.g;
import m2.f0;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class VorbisComment implements Metadata.Entry {
    public final String key;
    public final String value;

    public VorbisComment(String str, String str2) {
        this.key = a.T(str);
        this.value = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            VorbisComment vorbisComment = (VorbisComment) obj;
            if (this.key.equals(vorbisComment.key) && this.value.equals(vorbisComment.value)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ byte[] getWrappedMetadataBytes() {
        return g.a(this);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ Format getWrappedMetadataFormat() {
        return g.b(this);
    }

    public int hashCode() {
        return this.value.hashCode() + c.c(527, 31, this.key);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.key;
        str.getClass();
        switch (str) {
            case "TOTALTRACKS":
                Integer numT = f0.T(this.value);
                if (numT != null) {
                    builder.setTotalTrackCount(numT);
                    break;
                }
                break;
            case "TOTALDISCS":
                Integer numT2 = f0.T(this.value);
                if (numT2 != null) {
                    builder.setTotalDiscCount(numT2);
                    break;
                }
                break;
            case "TRACKNUMBER":
                Integer numT3 = f0.T(this.value);
                if (numT3 != null) {
                    builder.setTrackNumber(numT3);
                    break;
                }
                break;
            case "ALBUM":
                builder.setAlbumTitle(this.value);
                break;
            case "GENRE":
                builder.setGenre(this.value);
                break;
            case "TITLE":
                builder.setTitle(this.value);
                break;
            case "DESCRIPTION":
                builder.setDescription(this.value);
                break;
            case "DISCNUMBER":
                Integer numT4 = f0.T(this.value);
                if (numT4 != null) {
                    builder.setDiscNumber(numT4);
                    break;
                }
                break;
            case "ALBUMARTIST":
                builder.setAlbumArtist(this.value);
                break;
            case "ARTIST":
                builder.setArtist(this.value);
                break;
        }
    }

    public String toString() {
        return "VC: " + this.key + "=" + this.value;
    }
}
