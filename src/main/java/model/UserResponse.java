package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "meta",
        "data"
})

public class UserResponse {

    private Object meta;
    public Data data;

    public UserResponse(Object meta, Data data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public UserResponse() {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserResponse that = (UserResponse) o;

        if (getMeta() != null ? !getMeta().equals(that.getMeta()) : that.getMeta() != null) return false;
        return getData() != null ? getData().equals(that.getData()) : that.getData() == null;
    }

    public int hashCode() {
        int result = getMeta() != null ? getMeta().hashCode() : 0;
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "model.UserResponse{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}

