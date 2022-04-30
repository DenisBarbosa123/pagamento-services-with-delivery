package br.inatel.dm112.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.Order;
import reactor.core.publisher.Mono;

@Service
public class OrderClient {

	@Value("${order.rest.url}")
	private String restURL;
	
	private final String endpoint = "/orders";

	/**
	 * getItems
	 * @return List of orders
	 */
	public List<Order> getAllOrders() 
	{
		String url = restURL + endpoint;
		System.out.println("URL: " + url);
		
		return WebClient.create(url)
		        .get()
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .retrieve()
		        .bodyToFlux(Order.class)
		        .collectList()
		        .log()
		        .block();
	}
	
	/**
	 * retrieveOrder
	 * @param orderNumber
	 * @return
	 */
	public Order retrieveOrder(int orderNumber) {
		String url = restURL + endpoint + "/" + orderNumber;
		System.out.println("URL: " + url);
		
		return WebClient.create(url)
		        .get()
		        .retrieve()
		        .bodyToMono(Order.class)
		        .block();
	}
	
	/**
	 * updateOrder
	 * @param order
	 * @return
	 */
	public void updateOrder(Order order) 
	{
		String url = restURL + endpoint + "/" + order.getNumber();
		System.out.println("URL: " + url);
		
		Mono<Void> putStream = WebClient.create(url)
		        .put()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(order), Order.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToMono(Void.class);
		
		putStream.subscribe();
		System.out.println("Sucesso no updateOrder para o pedido: " + order.getNumber());
	}
	
	public String getEndpoint() 
	{
		return endpoint;
	}
	
	public void setRestURL(String restURL) 
	{
		this.restURL = restURL;
	}
}