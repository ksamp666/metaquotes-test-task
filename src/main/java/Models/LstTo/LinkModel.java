package Models.LstTo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkModel {
    @JsonProperty("url")
    private String url;

    @JsonProperty("short")
    private String shortUrl;

    @JsonProperty("qr")
    private String qr;

    @JsonProperty("type")
    private String type;

    @JsonProperty("created")
    private String created;

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    public String getQr() {
        return qr;
    }

    public String getType() {
        return type;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public void setShort_url(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
