package aghr.olhaobra.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("cod")
    @Expose
    private Integer cod;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     * The cod
     */
    public Integer getCod() {
        return cod;
    }

    /**
     *
     * @param cod
     * The cod
     */
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}