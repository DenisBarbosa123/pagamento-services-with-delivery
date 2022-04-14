package br.inatel.dm112.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryDAO {
	private int deliveryId;
	private int pedido;
	private String cpf;
	private String dataEntrega;
}
