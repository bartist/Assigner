package data.vertex;

import data.Vertex;

public class StartVertex extends Vertex {
    public StartVertex() {
        super(true, null);
    }

    @Override
    public boolean isStart() {
        return true;
    }
}
