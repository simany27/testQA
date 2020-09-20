package telran.stock.service;

import java.util.List;

import telran.stock.dto.ItemDto;

public interface StockService {

	ItemDto readItemDetails(String itemNo);
	ItemDto withdrawalQuantity(String itemNo, int quantity);
	ItemDto depositQuantity(String itemNo, int quantity);
	boolean addItem(ItemDto itemDto);
	ItemDto deleteItem(String itemNo);
	List<ItemDto> inventoryItems(); //List of the inventory items list (item no, name, amount, inventory code)
}
