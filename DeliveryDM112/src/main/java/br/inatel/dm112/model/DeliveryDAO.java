package br.inatel.dm112.model;

import java.sql.Date;

public class DeliveryDAO {

	private int deliveryId;
	
	private int pedido;
	
	private String cpf;
	
	private Date dataEntrega;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) 
	{
		this.cpf = cpf;
	}

	public Date getDataEntrega() 
	{
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) 
	{
		this.dataEntrega = dataEntrega;
	}
}
