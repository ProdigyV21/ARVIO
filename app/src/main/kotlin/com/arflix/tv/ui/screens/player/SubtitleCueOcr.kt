package com.arflix.tv.ui.screens.player

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * OCR fallback for AI subtitle auto-sync: when the only built-in reference track is image-based
 * (PGS/DVB — its cues carry a [Bitmap] and no text), the rendered cue bitmap is OCR'd with
 * ML Kit's bundled on-device Latin recognizer so it can still serve as a timing reference.
 *
 * Notes (mirrors the NuvioTV implementation this is ported from):
 * - Latin-script only: `TextRecognizerOptions.DEFAULT_OPTIONS` covers English & most European
 *   languages. Non-Latin bitmap tracks (Hebrew/Arabic/Cyrillic/CJK) produce garbage or nothing —
 *   the caller prefers an English bitmap track for exactly this reason, and noisy output is
 *   still tolerated downstream (LLM semantic matching + robust-mean outlier rejection).
 * - A recognizer instance per call, closed on completion — no persistent state to leak.
 * - Failures resolve to null; the auto-sync gather loop simply sees one fewer reference line.
 */
object SubtitleCueOcr {

    suspend fun recognizeSubtitleBitmapText(bitmap: Bitmap): String? =
        suspendCancellableCoroutine { cont ->
            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            val image = InputImage.fromBitmap(bitmap, 0)
            recognizer.process(image)
                .addOnSuccessListener { visionText -> if (cont.isActive) cont.resume(visionText.text) }
                .addOnFailureListener { if (cont.isActive) cont.resume(null) }
                .addOnCompleteListener { recognizer.close() }
        }
}
