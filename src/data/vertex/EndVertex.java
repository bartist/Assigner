package data.vertex;

import data.Vertex;

public class EndVertex extends Vertex {
    public EndVertex() {
        super(false, null);
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
