package bookserver.message;

import java.util.List;

public class DataMessage {

    private List<Object> data;

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<Object> getData() {
        return data;
    }
}
