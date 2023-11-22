package configuration;

import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.littleshoot.proxy.*;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ProxyClient {
//    public static void main(String[] args) throws InterruptedException {
//        // Start the proxy server
//        HttpProxyServer proxyServer = DefaultHttpProxyServer.bootstrap()
//                .withPort(8085) // Set the port for your proxy
//                .withFiltersSource(new HttpFiltersSourceAdapter() {
//                    @Override
//                    public HttpFilters filterRequest(HttpRequest originalRequest) {
//                        return new HttpFiltersAdapter(originalRequest) {
//                            @Override
//                            public HttpResponse clientToProxyRequest(HttpObject httpObject) {
//                                // Modify the request if needed
//                                return null;
//                            }
//
//                            @Override
//                            public HttpResponse proxyToServerRequest(HttpObject httpObject) {
//                                // Modify the request if needed
//                                return null;
//                            }
//
//
//                            public void proxyToServerResponse(HttpObject httpObject) {
//                                // Modify the response if needed
//                            }
//
//                            @Override
//                            public HttpObject serverToProxyResponse(HttpObject httpObject) {
//                                // Modify the response if needed
//                                return httpObject;
//                            }
//                        };
//                    }
//                })
//                .start();
//
//        // Set up ChromeDriver with the proxy
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--proxy-server=http://127.0.0.1:8085");
//        WebDriver driver = new ChromeDriver(options);
//        driver.get("https://www.myip.com/");
//        Thread.sleep(5000);
//        // Use 'driver' for your automation with the configured proxy
//
//        // Close the browser and stop the proxy
//        driver.quit();
//        proxyServer.stop();
//    }
}
