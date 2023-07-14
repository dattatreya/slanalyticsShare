import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class SLAnalytics1 {

    public static void main(String [] args){
        String payload ="{\"usage\":[{\"measures\":[{\"id\":\"TASK_INFO\",\"value\":1}],\"customDimensions\":{\"dimension6\":\"2023-04-27 06:45:01.663\",\"dimension1\":\"A0000000001\",\"dimension4\":\"Disclaimer\",\"dimension5\":\"IN PROCESS\",\"dimension2\":\"com.sap.op.bp.2TY.2022\",\"dimension3\":\"A00001\"},\"service\":{\"id\":\"cias\",\"plan\":\"standard\"},\"id\":\"3996b107-a699-4173-94d3-891fb45d8397\",\"consumer\":{\"environment\":\"CF\",\"globalAccount\":\"c2a8d870-0379-4cb8-9f7a-1fd635588906\",\"region\":\"cf-jp10\",\"subAccount\":\"b8875269-095f-4fd2-8aee-f25a97d477de\"},\"timestamp\":\"2023-07-11T06:00:00.091\"},{\"measures\":[{\"id\":\"TASK_INFO\",\"value\":1}],\"customDimensions\":{\"dimension6\":\"2023-04-27 06:45:06.759\",\"dimension1\":\"A0000000001\",\"dimension4\":\"Confirm System Components\",\"dimension5\":\"IN PROCESS\",\"dimension2\":\"com.sap.op.bp.2TY.2022\",\"dimension3\":\"A00002\"},\"service\":{\"id\":\"cias\",\"plan\":\"standard\"},\"id\":\"701ea92b-5064-42c0-b970-5112c8d0523e\",\"consumer\":{\"environment\":\"CF\",\"globalAccount\":\"c2a8d870-0379-4cb8-9f7a-1fd635588906\",\"region\":\"cf-jp10\",\"subAccount\":\"b8875269-095f-4fd2-8aee-f25a97d477de\"},\"timestamp\":\"2023-07-11T06:00:00.091\"}]}";
        String url = "https://submit.slanalytics.net.sap/analysis/sl_tool?sender_id=sl_tool&tooltype=TEST1";

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("name", "filename");
        map.add("filename", "filename");

        ByteArrayResource contentsAsResource = new ByteArrayResource(payload.getBytes()) {
            @Override
            public String getFilename() {
                return "filename"; // Filename has to be returned in order to be able to post.
            }
        };
        map.add("file", contentsAsResource);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key","dummy_key");
        headers.add("Content-Type","multipart/form-data");
        headers.add("accept","application/json");

        HttpEntity entity = new HttpEntity<>(map,headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            String result = restTemplate.postForObject(url, entity, String.class);
            System.out.println(result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
