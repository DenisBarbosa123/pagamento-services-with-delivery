package br.inatel.dm112.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.exception.StatusNotFoundException;
import br.inatel.dm112.model.DeliveryDAO;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.Record;
import br.inatel.dm112.services.DeliveryService;

@RestController
@RequestMapping("/api")
public class DeliveryRest {

	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping(value = "/orders/{status}")
	@ResponseStatus(HttpStatus.OK)
	public List<Order> getAllOrdersByStatus(@PathVariable("status") int status) throws StatusNotFoundException
	{
		return deliveryService.getAllOrdersByStatus(status);
	}
	
	@PostMapping(value = "/recordDelivery")
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryDAO recordDelivery(@RequestBody Record record)
	{
		return deliveryService.recordDelivery(record);
	}
}
