import com.google.gson.Gson;

public interface JsonSupport {
    public String toJson();
    public Object fromJson(String a);
}
