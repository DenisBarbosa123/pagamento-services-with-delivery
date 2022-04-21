package br.inatel.dm112.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.model.Delivery;
import br.inatel.dm112.repository.DeliveryRepository;
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

	@Autowired
	private EmailClient emailClient;

	@Autowired
	private DeliveryRepository deliveryRepository;

	public List<Order> getAllOrdersByStatus(int status) throws StatusNotFoundException 
	{
		if(status < 0 || status > 3)
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
		emailClient.callSendMailService(record.getEmail()); // manda email
		Delivery delivery = deliveryRepository.save(buildDelivery(order));// salva o Delivery

		return translateDeliveryFrom(delivery);// retorna Delivery DAO
	}

	public List<Delivery> getAllDeliveries()
	{
		return deliveryRepository.findAll();
	}

	public static DeliveryDAO translateDeliveryFrom(Delivery delivery)
	{
		return DeliveryDAO
				.builder()
				.deliveryId(delivery.getDeliveryId())
				.pedido(delivery.getPedido())
				.cpf(delivery.getCpf())
				.dataEntrega(delivery.getDataEntrega())
				.build();
	}

	private Delivery buildDelivery(Order order)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Delivery delivery = new Delivery();
		delivery.setCpf(order.getCpf());
		delivery.setPedido(order.getNumber());
		delivery.setDataEntrega(formatter.format(new Date()));

		return delivery;
	}
}
