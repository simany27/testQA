package telran.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.stock.dto.ItemDto;
import telran.stock.service.StockService;

@RestController
public class StockController {

	@Autowired
	StockService stockService;
	
	@GetMapping("/item/{itemNo}")
	public ItemDto readItemDetails(@PathVariable String itemNo) {
		return stockService.readItemDetails(itemNo);
	}
	
	@PutMapping("/item/with/{itemNo}/{quantity}")
	public ItemDto withdrawalQuantity(@PathVariable String itemNo, @PathVariable int quantity) {
		return stockService.withdrawalQuantity(itemNo, quantity);
	}
	
	@PutMapping("/item/dep/{itemNo}/{quantity}")
	public ItemDto depositQuantity(@PathVariable String itemNo, @PathVariable int quantity) {
		return stockService.depositQuantity(itemNo, quantity);
	}
	
	@PostMapping("/item")
	public boolean addItem(@RequestBody ItemDto itemDto) {
		return stockService.addItem(itemDto);
	}
	
	@DeleteMapping("/item/{itemNo}")
	public ItemDto deleteItem(String itemNo) {
		return stockService.deleteItem(itemNo);
	}
	
	@GetMapping("/items")
	public List<ItemDto> inventoryItems(){
		return stockService.inventoryItems();
	}
	
}
