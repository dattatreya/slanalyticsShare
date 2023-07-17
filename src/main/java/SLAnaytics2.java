
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class SLAnaytics2 {

    public static void main(String [] args) {
        String payload ="{\"usage\":[{\"measures\":[{\"id\":\"TASK_INFO\",\"value\":1}],\"customDimensions\":{\"dimension6\":\"2023-04-27 06:45:01.663\",\"dimension1\":\"A0000000001\",\"dimension4\":\"Disclaimer\",\"dimension5\":\"IN PROCESS\",\"dimension2\":\"com.sap.op.bp.2TY.2022\",\"dimension3\":\"A00001\"},\"service\":{\"id\":\"cias\",\"plan\":\"standard\"},\"id\":\"3996b107-a699-4173-94d3-891fb45d8397\",\"consumer\":{\"environment\":\"CF\",\"globalAccount\":\"c2a8d870-0379-4cb8-9f7a-1fd635588906\",\"region\":\"cf-jp10\",\"subAccount\":\"b8875269-095f-4fd2-8aee-f25a97d477de\"},\"timestamp\":\"2023-07-11T06:00:00.091\"},{\"measures\":[{\"id\":\"TASK_INFO\",\"value\":1}],\"customDimensions\":{\"dimension6\":\"2023-04-27 06:45:06.759\",\"dimension1\":\"A0000000001\",\"dimension4\":\"Confirm System Components\",\"dimension5\":\"IN PROCESS\",\"dimension2\":\"com.sap.op.bp.2TY.2022\",\"dimension3\":\"A00002\"},\"service\":{\"id\":\"cias\",\"plan\":\"standard\"},\"id\":\"701ea92b-5064-42c0-b970-5112c8d0523e\",\"consumer\":{\"environment\":\"CF\",\"globalAccount\":\"c2a8d870-0379-4cb8-9f7a-1fd635588906\",\"region\":\"cf-jp10\",\"subAccount\":\"b8875269-095f-4fd2-8aee-f25a97d477de\"},\"timestamp\":\"2023-07-11T06:00:00.091\"}]}";
        CustomMultipartFile customMultipartFile = new CustomMultipartFile(payload.getBytes());
        WebClient webClient = WebClient.create();

        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-key","dummy_key");
        headers.put("Content-Type","multipart/form-data");
        headers.put("accept","application/json");


        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", customMultipartFile.getResource());

        Mono<Object> httpStatusMono = webClient.post()
                .uri("https://submit.slanalytics.net.sap/analysis/sl_tool?sender_id=sl_tool&tooltype=TEST1")
                .headers(httpHeaders -> {
                    httpHeaders.set("accept","application/json");
                    httpHeaders.set("x-api-key","dummy_key");
              //      httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                })
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(HttpStatus.class).thenReturn(response.statusCode());
                    }
                    return response.bodyToMono(String.class).thenReturn(response.statusCode());
                });


        HttpStatus value = (HttpStatus) httpStatusMono.block();
    }
}
