import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class SLA4 {


    public static void main(String args[]) throws IOException {
        String payloadJp10 ="{\"usage\":[{\"measures\":[{\"id\":\"TASK_INFO\",\"value\":1}],\"customDimensions\":{\"dimension6\":\"2023-04-27 06:45:01.663\",\"dimension1\":\"A0000000001\",\"dimension4\":\"Disclaimer\",\"dimension5\":\"IN PROCESS\",\"dimension2\":\"com.sap.op.bp.2TY.2022\",\"dimension3\":\"A00001\"},\"service\":{\"id\":\"cias\",\"plan\":\"standard\"},\"id\":\"3996b107-a699-4173-94d3-891fb45d8397\",\"consumer\":{\"environment\":\"CF\",\"globalAccount\":\"c2a8d870-0379-4cb8-9f7a-1fd635588906\",\"region\":\"cf-jp10\",\"subAccount\":\"b8875269-095f-4fd2-8aee-f25a97d477de\"},\"timestamp\":\"2023-07-11T06:00:00.091\"},{\"measures\":[{\"id\":\"TASK_INFO\",\"value\":1}],\"customDimensions\":{\"dimension6\":\"2023-04-27 06:45:06.759\",\"dimension1\":\"A0000000001\",\"dimension4\":\"Confirm System Components\",\"dimension5\":\"IN PROCESS\",\"dimension2\":\"com.sap.op.bp.2TY.2022\",\"dimension3\":\"A00002\"},\"service\":{\"id\":\"cias\",\"plan\":\"standard\"},\"id\":\"701ea92b-5064-42c0-b970-5112c8d0523e\",\"consumer\":{\"environment\":\"CF\",\"globalAccount\":\"c2a8d870-0379-4cb8-9f7a-1fd635588906\",\"region\":\"cf-jp10\",\"subAccount\":\"b8875269-095f-4fd2-8aee-f25a97d477de\"},\"timestamp\":\"2023-07-11T06:00:00.091\"}]}";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("multipart/form-data");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("analysis","dummyfile",
                        RequestBody.create(MediaType.parse("application/json"),
                                payloadJp10.getBytes()))
                .build();
        Request request = new Request.Builder()
                .url("https://submit.slanalytics.net.sap/analysis/sl_tool?sender_id=sl_tool&tooltype=TEST1")
                .method("POST", body)
                .addHeader("accept", "application/json")
                .addHeader("x-api-key", "dummy_key")
                .addHeader("Content-Type", "multipart/form-data")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.code() + " "+response.body().string());
    }
}
