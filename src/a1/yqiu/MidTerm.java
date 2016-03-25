package a1.yqiu;

/**
 * Created by Qiu on 3/7/16.
 */
public class MidTerm {

    public int findLast(int[] x, int y) {
        for (int i = x.length - 1; i > 0; i--) {
            if (x[i] == y) {
                return i;
            }
        }
        return -1;
    }
}
