package audio.speaker;

import audio.entity.AudioDocument;
import com.sun.speech.freetts.Voice;

import java.util.List;

public class SequenceSpeaker {

    // Replays the audio sequence that is stored in the memory.
    public void replaySequence(final Voice voice, final List<AudioDocument> audioDocumentList) {
        new Thread(() -> audioDocumentList.forEach(audioDoc -> voice.speak(audioDoc.getText()))).start();
    }
}
