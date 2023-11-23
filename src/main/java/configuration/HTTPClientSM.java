package configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.io.IOException;

public class HTTPClientSM {

    private static final String restUrl = "https://shopmanager.staging.dermpro.com/v1";
    private static final HttpClient httpClient = HttpClients.createDefault();
    private static final String AUTHORIZATION_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvbmJvYXJkaW5nIjp7InN0b3JlSW5mb3JtYXRpb24iOnsiZmlyc3ROYW1lIjoiIiwibGFzdE5hbWUiOiIiLCJzdG9yZU5hbWUiOiIiLCJ3ZWJzaXRlIjoiIiwic3RyZWV0QWRkcmVzczEiOiIiLCJzdHJlZXRBZGRyZXNzMiI6IiIsImNpdHkiOiIiLCJzdGF0ZSI6IiIsInN0YXRlSWQiOiIiLCJ6aXBDb2RlIjoiIiwiY291bnRyeSI6IiIsInRpbWV6b25lIjoiQW1lcmljYS9DaGljYWdvIiwicGhvbmUiOiIifSwiYmlsbGluZ0luZm9ybWF0aW9uIjp7ImZpcnN0TmFtZSI6IiIsImxhc3ROYW1lIjoiIiwic3RyZWV0QWRkcmVzczEiOiIiLCJzdHJlZXRBZGRyZXNzMiI6IiIsImNpdHkiOiIiLCJ6aXBDb2RlIjoiIiwiY291bnRyeSI6IiIsInN0YXRlIjoiIiwicGhvbmUiOiIiLCJjb3Vwb25Db2RlIjoiIn0sInNlbGVjdGVkRGlzY291bnRzSWRzIjpbXSwidG90YWxPbmVUaW1lUHJpY2UiOjAsInRvdGFsTW9udGhseVByaWNlIjowLCJpc1Rlcm1zQWNjZXB0ZWQiOmZhbHNlLCJhY3RpdmVTdGVwIjoxfSwibWFya2V0aW5nIjp7ImZlYXR1cmVJZHMiOltdLCJza3VJZHMiOltdLCJzcGVjaWFsSWRzIjpbXSwicGxhbiI6ImFubnVhbGx5IiwidG90YWxPbmVUaW1lUHJpY2UiOjAsInRvdGFsTW9udGhseVByaWNlIjowLCJzZWxlY3RlZEJlbmVmaXQiOiIifSwiZGVmYXVsdENyZWRpdCI6eyJ0b2tlbiI6bnVsbCwiYWRkcmVzc0lkIjpudWxsfSwic3RvcmVzIjpbeyJsaW1pdCI6eyJtYXhQcm9tb1NrdXMiOjEwMCwibWF4QWN0aXZlUHJvbW9zIjozMH0sInF1b3RlUHJvU3RvcmVJZCI6bnVsbCwicXVvdGVQcm9TdG9yZUNvZGUiOm51bGwsInF1b3RlUHJvQ3VzdG9tZXJVcmwiOm51bGwsImNoZWNrb3V0VXJsIjpudWxsLCJzdG9yZVR5cGUiOiJwcmVtaXVtIiwiaXNPbGRNaWdyYXRlZCI6ZmFsc2UsInN1YnNjcmlwdGlvblRpZXIiOm51bGwsInN1YnNjcmlwdGlvbklkIjpudWxsLCJpc0NyZWF0ZWQiOnRydWUsImlzTGl2ZSI6ZmFsc2UsIl9pZCI6IjY0Zjg1MmE4NjJjZGU4MjFmMzM3NTc2OSIsImFwaVVybCI6Imh0dHBzOi8vbW9kbWVkZm9ydGVzdGluZy5kZXJtcHJvLmNvbSIsInN0b3JlSWQiOjQ1OSwic3RvcmVDb2RlIjoibW9kbWVkZm9ydGVzdGluZyIsInN0b3JlVXJsIjoiaHR0cHM6Ly9tb2RtZWRmb3J0ZXN0aW5nLmRlcm1wcm8uY29tIn1dLCJpc0Rlcm1wcm9TdGFmZiI6ZmFsc2UsIl9pZCI6IjYyYjU3ZmRlZDgzYzkzNzg2Yzk5ZDZjYyIsImZpcnN0TmFtZSI6IkF1dG8iLCJsYXN0TmFtZSI6IlRlc3QiLCJlbWFpbCI6ImdhbHlhLmZAdGhlc2NpbXVzLmNvbSIsImN1c3RvbWVyMklkIjoiIiwiY29uZmlnIjoiNjI0MTg2OGI5MTg4ZDgwMzU5M2NhYTgzIiwiY3JlYXRlZEF0IjoiMjAyMi0wNi0yNFQwOToxMTo1OC42OTBaIiwidXBkYXRlZEF0IjoiMjAyMi0wNi0yNFQwOToxMTo1OC42OTBaIiwiX192IjozNDIsImlhdCI6MTY5NDE5NzEzMSwiZXhwIjoxNzk0MjgzNTMxfQ.vclS2ojU1NqDOc8eYaLgfzs25kLtjB9MLVDGEquv77o";
    private static final String CONNECT_SID = "s%3A-FQv0pBNm2zkM_6ajQdPkf_uY4gLmovE.wUZ2f0EhxNr5JcYMzz6GbU5ndIPuVroKEcE4R3vPwrw";


    public static void createCardCustomer2() {
        String payload = "{\"firstName\": \"Delete\",\"lastName\": \"Card\",\n" +
                "  \"regionCode\": \"MD\",\"regionName\": \"Maryland\",\n" +
                "  \"regionId\": 31,\"countryId\": \"US\",\"street\": [\"57079 Shaun Isle\"],\"telephone\": \"1502636083\",\n" +
                "  \"postcode\": \"77777\",\"city\": \"Kingmouth\",\"company\": \"DermTestCompany\",\n" +
                "  \"ccNum\": \"4242424242424242\",\"ccExpYear\": \"52\",\"ccExpMonth\": \"10\",\n" +
                "  \"cvv\": \"123\",\"replaceForSubscriptions\": false}";
        try {
            BasicCookieStore cookieStore = getBasicCookieStore(CONNECT_SID, AUTHORIZATION_TOKEN);
            // Create an HttpClient with the custom cookie store
            HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            // Create an HttpClient with the custom cookie store
            HttpPost httpPost = new HttpPost("https://shopmanager.staging.dermpro.com/v1/customer/credit-card");
            httpPost.setHeader("Authorization", AUTHORIZATION_TOKEN);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(payload));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            EntityUtils.consume(responseEntity);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            Assert.assertTrue(jsonNode.get("card_holder_name").toString().contains("Delete Card"), "Card is not created");
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    private static BasicCookieStore getBasicCookieStore(String connectSid, String authorizationToken) {
        BasicCookieStore cookieStore = new BasicCookieStore();
        // Create the first cookie
        BasicClientCookie cookie1 = new BasicClientCookie("connect.sid", connectSid);
        cookie1.setDomain("shopmanager.staging.dermpro.com");
        cookie1.setPath("/");
        BasicClientCookie cookie2 = new BasicClientCookie("token", authorizationToken);
        cookie2.setDomain("shopmanager.staging.dermpro.com");
        cookie2.setPath("/");
        // Add cookies to the cookie store
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);
        return cookieStore;
    }

    public static String getStoreId() { // hardcoded for regressionshop.dermpro.com
        String patchResponse = sendGetRequest(restUrl + "/stores?storeUrl=regressionshopsm.dermpro.com&limit=1&page=1");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(patchResponse);
            return jsonNode.get("stores").get(0).get("_id").asText();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

    public static String sendGetRequest(String url) {
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Authorization", AUTHORIZATION_TOKEN);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            EntityUtils.consume(responseEntity);
            return responseBody;
        } catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public static void createCustomer(String email, String firstName, String lastName) throws JsonProcessingException, InterruptedException {
        String payload = "{\"customer\": {\"group_id\": 1,\"dob\": \"1999-10-10\",\"email\": \"" + email + "\",\n" +
                "    \"firstname\": \"" + firstName + "\",\"lastname\": \"" + lastName + "\",\"gender\": 1}}";
        Thread.sleep(new Faker().random().nextInt(1000, 10000));
        String responseBody = sendPostRequest("https://shopmanager.staging.dermpro.com/v1/store/customer", payload);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        System.out.println("jsonNode = " + jsonNode);
        Assert.assertEquals(jsonNode.get("message").toString(), "\"Success\"", "Customer is not created");
    }


    public static String sendPostRequest(String url, String payload) {
        try {
            BasicCookieStore cookieStore = new BasicCookieStore();
            // Create the first cookie
            BasicClientCookie cookie1 = new BasicClientCookie("connect.sid", CONNECT_SID);
            cookie1.setDomain("shopmanager.staging.dermpro.com");
            cookie1.setPath("/");
            BasicClientCookie cookie2 = new BasicClientCookie("token", AUTHORIZATION_TOKEN);
            cookie2.setDomain("shopmanager.staging.dermpro.com");
            cookie2.setPath("/");
            // Add cookies to the cookie store
            cookieStore.addCookie(cookie1);
            cookieStore.addCookie(cookie2);

            // Create an HttpClient with the custom cookie store
            HttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Authorization", AUTHORIZATION_TOKEN);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(payload));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            EntityUtils.consume(responseEntity);
            return responseBody;
        } catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public static String sendPatchRequest(String url, String payload) {
        try {
            HttpPatch httpPatch = new HttpPatch(url);
            // Set request headers
            httpPatch.setHeader("accept", "application/json");
            httpPatch.setHeader("Authorization", AUTHORIZATION_TOKEN);
            httpPatch.setHeader("Content-Type", "application/json");
            // Set payload
            httpPatch.setEntity(new StringEntity(payload));

            HttpResponse response = httpClient.execute(httpPatch);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            EntityUtils.consume(responseEntity);
            return responseBody;
        } catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public static String sendDeleteRequest(String url) {
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setHeader("Authorization", AUTHORIZATION_TOKEN);
            HttpResponse response = httpClient.execute(httpDelete);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            EntityUtils.consume(responseEntity);
            return responseBody;
        } catch (IOException e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public static String removeUserByEmail(String email) {
        String patchResponse = sendDeleteRequest(restUrl + "/users/" + email);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(patchResponse);
            return jsonNode.get("message").asText();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

    public static String getFailedQueueId() {
        String patchResponse = sendGetRequest(restUrl + "/queue?storeViewCode=regressionshopsm");
        try {
            HttpGet httpGet = new HttpGet(restUrl + "/queue?storeViewCode=regressionshopsm");
            httpGet.setHeader("accept", "application/json");
            httpGet.setHeader("Authorization", AUTHORIZATION_TOKEN);

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            EntityUtils.consume(responseEntity);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return null;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

}
