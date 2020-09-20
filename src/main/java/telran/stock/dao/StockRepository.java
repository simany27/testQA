package telran.stock.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.stock.model.Item;

public interface StockRepository extends JpaRepository<Item, String> {

}
