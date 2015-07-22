import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by andrew.g.wardrobe on 20/07/2015.
 */
public class ServiceConsumer {
    ResultProcessor processor;

    public ServiceConsumer(ResultProcessor log) {
        processor = log;
    }

    void doThing(String id) throws Exception{
        String serviceUrl = System.getProperty("service.url","http://realservice.com:8080");
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<leek>\n" +
                "\t<user>"+id+"</user>\n" +
                "</leek>";

        System.out.println("Sending : "+xml);
        HttpURLConnection result = Helpers.postXml(serviceUrl+"/service",xml);
        if(result.getResponseCode() == HttpURLConnection.HTTP_OK){
            processor.success(Helpers.contentAsString(result));
        }else{
            processor.failure(Helpers.errAsString(result));
        }
    }
}
