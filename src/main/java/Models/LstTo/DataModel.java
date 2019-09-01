package Models.LstTo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataModel {
    @JsonProperty("data")
    private LinkModel data;

    public LinkModel getData() {
        return data;
    }

    public void setData(LinkModel data) {
        this.data = data;
    }
}
