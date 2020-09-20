package telran.stock.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.stock.dto.EntityNotFoundException;
import telran.stock.dao.StockRepository;
import telran.stock.dto.ItemDto;
import telran.stock.model.Item;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ItemDto readItemDetails(String itemNo) {
		Item item = stockRepository.findById(itemNo).orElseThrow(()-> new EntityNotFoundException());
		return modelMapper.map(item, ItemDto.class);
	}

	@Override
	public ItemDto withdrawalQuantity(String itemNo, int quantity) {
		Item item = stockRepository.findById(itemNo).orElseThrow(()-> new EntityNotFoundException());
		item.addQuantity(quantity);
		stockRepository.save(item);
		return modelMapper.map(item, ItemDto.class);
	}

	@Override
	public ItemDto depositQuantity(String itemNo, int quantity) {
		Item item = stockRepository.findById(itemNo).orElseThrow(()-> new EntityNotFoundException());
		item.deleteQuantity(quantity);
		stockRepository.save(item);
		return modelMapper.map(item, ItemDto.class);
	}

	@Override
	@Transactional
	public boolean addItem(ItemDto itemDto) {
		if(stockRepository.existsById(itemDto.getItemNo())) {
			Item item = stockRepository.findById(itemDto.getItemNo()).orElseThrow(()-> new EntityNotFoundException());
			item.addQuantity(itemDto.getAmount());
			return false;
		}
		Item item = new Item(itemDto.getItemNo(), itemDto.getName(), itemDto.getAmount(), itemDto.getInventoryCode());
		stockRepository.save(item);
		return true;
	}

	@Override
	@Transactional
	public ItemDto deleteItem(String itemNo) {
		Item item = stockRepository.findById(itemNo).orElseThrow(()-> new EntityNotFoundException());
		stockRepository.delete(item);
		return modelMapper.map(item, ItemDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemDto> inventoryItems() {
		return stockRepository.findAll().stream().map(i -> modelMapper.map(i, ItemDto.class)).collect(Collectors.toList());
	}

}
