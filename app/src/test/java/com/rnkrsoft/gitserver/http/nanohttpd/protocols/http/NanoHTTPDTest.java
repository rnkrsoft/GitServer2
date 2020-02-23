package com.rnkrsoft.gitserver.http.nanohttpd.protocols.http;

import org.junit.Test;
import com.rnkrsoft.gitserver.http.nanohttpd.protocols.http.request.Method;
import com.rnkrsoft.gitserver.http.nanohttpd.protocols.http.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.rnkrsoft.gitserver.http.nanohttpd.protocols.http.response.Response.newFixedLengthResponse;

public class NanoHTTPDTest{

    @Test
    public void start() throws IOException, InterruptedException {
        new NanoHTTPD(8080){
            @Override
            public Response serve(IHTTPSession session) {

                try {
                    // 这一句话必须要写，否则在获取数据时，获取不到数据
                    session.parseBody(new HashMap<String, String>());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ResponseException e) {
                    e.printStackTrace();
                }

                StringBuilder builder = new StringBuilder();
                Method method = session.getMethod();
                String uri = session.getUri();
                Map<String, String> parms = session.getParms();
                String data = parms.get("data");//这里的data是POST提交表单时key
                System.out.println("uri: "+uri);//如果有uri,会打印出uri
                System.out.println("data: "+data);
                builder.append("任意内容");// 反馈给调用者的数据
                return newFixedLengthResponse(builder.toString());
            }
        }.start();
        Thread.sleep(600000);
    }
}