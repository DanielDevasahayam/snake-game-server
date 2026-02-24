package objects.objects;

public class Snake {
    private char direction ;
    private int bodyParts ;

    private int[] x;

    private int[] y;

    public Snake() {
    }

    public Snake(char direction, int bodyParts) {
        this.direction = direction;
        this.bodyParts = bodyParts;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(int bodyParts) {
        this.bodyParts = bodyParts;
    }
}
