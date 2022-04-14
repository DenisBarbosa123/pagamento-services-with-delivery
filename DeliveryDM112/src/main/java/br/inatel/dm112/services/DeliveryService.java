package br.inatel.dm112.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.exception.OrderNotFoundException;
import br.inatel.dm112.exception.StatusNotFoundException;
import br.inatel.dm112.model.DeliveryDAO;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.Record;

@Service
public class DeliveryService {
	
	@Autowired
	private OrderClient orderClient;

	public List<Order> getAllOrdersByStatus(int status) throws StatusNotFoundException 
	{
		if(status < 0)
		{
			throw new StatusNotFoundException("Status with value " + status +  " not found");
		}
		
		return orderClient
				.getAllOrders()
				.stream()
				.filter(order -> status == order.getStatus())
				.collect(Collectors.toList());
	}

	public DeliveryDAO recordDelivery(Record record) throws OrderNotFoundException 
	{
		Order order = orderClient.retrieveOrder(record.getOrderId());
		
		if(order == null)
		{
			throw new OrderNotFoundException("Order with value " + record.getOrderId() + "not found");
		}
		
		
		order.setStatus(3); // update status
		orderClient.updateOrder(order);// update do pedido para novo status DELIVERED
		
		// manda email
		// salva o Delivery
		// retorna Delivery DAO
		return null;
	}
}
