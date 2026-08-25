package androidx.media3.extractor.metadata.id3;

import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.collect.a0;
import com.google.common.collect.f1;
import com.google.common.collect.h1;

/* JADX INFO: loaded from: classes3.dex */
public final class Id3Util {
    private static final h1 STANDARD_GENRES;

    static {
        f1 f1Var = h1.f14020l;
        Object[] objArr = new Object[PsExtractor.AUDIO_STREAM];
        objArr[0] = "Blues";
        objArr[1] = "Classic Rock";
        objArr[2] = "Country";
        objArr[3] = "Dance";
        objArr[4] = "Disco";
        objArr[5] = "Funk";
        objArr[6] = "Grunge";
        objArr[7] = "Hip-Hop";
        objArr[8] = "Jazz";
        objArr[9] = "Metal";
        objArr[10] = "New Age";
        objArr[11] = "Oldies";
        System.arraycopy(new String[]{"Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Afro-Punk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop", "Abstract", "Art Rock", "Baroque", "Bhangra", "Big beat", "Breakbeat", "Chillout", "Downtempo", "Dub", "EBM", "Eclectic", "Electro", "Electroclash", "Emo", "Experimental", "Garage", "Global", "IDM", "Illbient", "Industro-Goth", "Jam Band", "Krautrock", "Leftfield", "Lounge", "Math Rock", "New Romantic", "Nu-Breakz", "Post-Punk", "Post-Rock", "Psytrance", "Shoegaze", "Space Rock", "Trop Rock", "World Music", "Neoclassical", "Audiobook", "Audio theatre", "Neue Deutsche Welle", "Podcast", "Indie-Rock", "G-Funk", "Dubstep", "Garage Rock", "Psybient"}, 0, objArr, 12, 180);
        a0.a(PsExtractor.AUDIO_STREAM, objArr);
        STANDARD_GENRES = h1.h(PsExtractor.AUDIO_STREAM, objArr);
    }

    private Id3Util() {
    }

    public static String resolveV1Genre(int i10) {
        if (i10 < 0) {
            return null;
        }
        h1 h1Var = STANDARD_GENRES;
        if (i10 < h1Var.size()) {
            return (String) h1Var.get(i10);
        }
        return null;
    }
}
