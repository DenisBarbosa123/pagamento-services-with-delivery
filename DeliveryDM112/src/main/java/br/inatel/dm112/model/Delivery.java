package br.inatel.dm112.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Delivery")
public class Delivery {
	
	@Id
	@Column(name = "deliveryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deliveryId;
	
	private int pedido;
	
	private String cpf;
	
	private String dataEntrega;
	
	public int getDeliveryId() 
	{
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) 
	{
		this.deliveryId = deliveryId;
	}
	
	public int getPedido() 
	{
		return pedido;
	}
	
	public void setPedido(int pedido) 
	{
		this.pedido = pedido;
	}
	
	public String getCpf() 
	{
		return cpf;
	}
	
	public void setCpf(String cpf) 
	{
		this.cpf = cpf;
	}
	
	public String getDataEntrega()
	{
		return dataEntrega;
	}
	
	public void setDataEntrega(String dataEntrega)
	{
		this.dataEntrega = dataEntrega;
	}
}
