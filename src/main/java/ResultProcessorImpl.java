/**
 * Created by andrew.g.wardrobe on 22/07/2015.
 */
public class ResultProcessorImpl implements ResultProcessor {

    public int failure(String err) {

        return 1;
    }


    public int success(String data) {
        return 0;
    }
}
