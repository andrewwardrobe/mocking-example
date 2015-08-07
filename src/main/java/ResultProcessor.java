/**
 * Created by andrew.g.wardrobe on 22/07/2015.
 */
public interface ResultProcessor {
    int failure(String err);
    int success(String data);
}
