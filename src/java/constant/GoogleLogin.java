package constant;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.ClientProtocolException;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import entity.GoogleAccount;
import java.io.IOException;


/**
 *
 * @author SUPPER LOQ
 */
public class GoogleLogin {
    public static String getToken(String code) throws IOException {
        // Gửi yêu cầu POST tới Google để lấy access token
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();

        // Parse JSON response để lấy access token
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").getAsString();

        return accessToken;
    }
    
    // truyen vao accessToken để lấy về thông tin user
    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleAccount googlePojo = new Gson().fromJson(response, GoogleAccount.class);
        return googlePojo;

    }
}
