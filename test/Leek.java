/**
 * Created by andrew.g.wardrobe on 20/07/2015.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import static org.mockito.Mockito.*;

import java.net.HttpURLConnection;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.XPathBody.xpath;


public class Leek {

    @Rule
    public MockServerRule mockServer = new MockServerRule(this);

    private MockServerClient mockServerClient;

    private String oldUrl = "";



    @Test
    public void passMock()throws Exception{
        ResultProcessor mockProc = mock(ResultProcessor.class);
        ServiceConsumer consumer = new ServiceConsumer(mockProc);

        consumer.doThing("Andrew");
        verify(mockProc).success(anyString());
    }


    @Test
    public void failMock()throws Exception{
        ResultProcessor mockProc = mock(ResultProcessor.class);
        ServiceConsumer consumer = new ServiceConsumer(mockProc);

        consumer.doThing("David");
        verify(mockProc).failure(anyString());
    }


    //Test fixture

    @Before
    public  void setUp(){

        mockServerClient = new MockServerClient("localhost",mockServer.getHttpPort());

        //Existing user
        mockServerClient.when(request()
                        .withMethod("POST")
                        .withPath("/service")
                        .withBody(xpath("/leek/user[.='Andrew']"))
        ).respond(response()
                        .withStatusCode(200)
                        .withBody("{\"name\":\"wardrobe\"}")
        );

        //User does not exist
        mockServerClient.when(request()
                        .withMethod("POST")
                        .withPath("/service")
                        .withBody(xpath("/leek/user[.='David']"))
        ).respond(response()
                        .withStatusCode(400)
                        .withBody("{\"errorResponse\":\"ID Not found\"}")
        );

        oldUrl = System.setProperty("service.url","http://localhost:"+mockServer.getHttpPort());


    }

    @After
    public void tearDown(){
        if(oldUrl != null)
            System.setProperty("service.url",oldUrl);
    }




}
