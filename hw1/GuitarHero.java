import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    public GuitarString[] guitar;

    public GuitarHero() {
        guitar = new GuitarString[37];

        for(int i = 0; i < 37; i++) {
            guitar[i] = new GuitarString(440 * Math.pow(2, (i - 24.0) / 12.0));
        }
    }

    public void guitarPluck(int noteIndex) {
        if(noteIndex < 0 || noteIndex >= 37) {
            return;
        }
        guitar[noteIndex].pluck();
    }
    public double guitarSample() {
        double sample = 0;
        for (int i = 0; i < guitar.length; i++) {
            sample += guitar[i].sample();
        }
        return sample;
    }

    public void guitarTic() {
        for(int i = 0; i < guitar.length; i++) {
            guitar[i].tic();
        }
    }

    public static void main(String[] args) {

        GuitarHero guitarPlayer = new GuitarHero();


        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int stringIndex;

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                stringIndex = keyboard.indexOf(key);

                if (stringIndex < 0) {
                    continue;
                }

                guitarPlayer.guitarPluck(stringIndex);
            }

            /* compute the superposition of samples */
            double sample = guitarPlayer.guitarSample();

            /* play the sample on standard audio */
            StdAudio.play(sample);


            /* advance the simulation of each guitar string by one step */
            guitarPlayer.guitarTic();



        }
    }
}
